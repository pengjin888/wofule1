package com.service;

import com.alibaba.fastjson.JSON;
import com.dao.KillorderinfoMapper;
import com.dao.OrderinfoMapper;
import com.feign.KillGoodsFeign;
import com.model.Killgoods;
import com.model.Killorderinfo;
import com.model.Orderinfo;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Package com.mq
 * @ClassName c
 * @Copyright: Copyright (c) 2020
 * @Date 2020/9/3 19:04
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
@Service
public class RabbitOredrConsumer {
    //创建一个消费者
    @Autowired
    KillGoodsFeign killGoodsFeign;
    @Autowired
    OrderinfoMapper orderinfoMapper;
    @Autowired
    KillorderinfoMapper killorderinfoMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    AmqpAdmin amqpAdmin;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Bean
    public MessageConverter messageConverter(){
        return  new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queues = "queue02")
    public void orderConsumer(String mmp) {
        System.out.println(mmp);
        try{
            comsue(mmp);
        }catch (Exception e){
            DirectExchange directExchange = new DirectExchange("back-exchange", true, false, null);
            //rabbitTemplate.decl
            amqpAdmin.declareExchange(directExchange);
            org.springframework.amqp.core.Queue queue = new org.springframework.amqp.core.Queue("queue4",true,false,false,null);
            amqpAdmin.declareQueue(queue);
            Binding binding = new Binding("queue4", Binding.DestinationType.QUEUE, "back-exchange", "queue4", null);
            amqpAdmin.declareBinding(binding);
            rabbitTemplate.convertAndSend("back-exchange","queue4",JSON.parseObject(mmp,Map.class).get("killid"));
        }
    }
    @Transactional
    public void comsue(String mmp) {
        Map map = JSON.parseObject(mmp, Map.class);
        int userid = (int) map.get("userid");
        int killid = (int) map.get("killid");
        int gooodsid = (int) map.get("goodsid");
        String goodsname = (String) map.get("goodsname");
        System.out.println(map);
        killGoodsFeign.subKillGoodsById(gooodsid);
        Killgoods one = killGoodsFeign.killGoodsById(killid);
        Orderinfo orderinfo = new Orderinfo(userid,
                one.getGoodsid(),
                goodsname,
                1,
                one.getKillprice(),
                1,
                new Date());
        orderinfoMapper.insertSelective(orderinfo);
        System.out.println(orderinfo);
        Killorderinfo killorderinfo = new Killorderinfo(userid, killid, orderinfo.getOrderid());
        killorderinfoMapper.insertSelective(killorderinfo);
        System.out.println(killorderinfo);
        SetOperations<String, String> setops = stringRedisTemplate.opsForSet();
        setops.add("orderif:user"+userid+"killid"+orderinfo.getOrderid(),"成功抢到");
        //int a = 5/0;
        //插入一个oredr，返回主键创建新的KillGoodsInfo，打印订单号
    }

}
