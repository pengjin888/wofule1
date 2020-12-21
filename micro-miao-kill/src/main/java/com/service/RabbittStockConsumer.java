package com.service;

import com.alibaba.fastjson.JSON;
import com.dao.KillgoodsMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Package com.service
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020/10/13 19:31
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
@Service
public class RabbittStockConsumer {
    @Autowired
    KillgoodsMapper killgoodsMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @RabbitListener(queues = "queue4")
    public void orderConsumer(int killid) {
        killgoodsMapper.upstock(killid);
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.increment("killcaches:stock:"+killid);
        System.out.println(killid);
    }
}

