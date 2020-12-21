package com.control;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.model.Cus;
import com.model.Killgoods;
import com.model.XmlUtilclass;
import com.service.GoodsService;
import com.service.KillGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.Jedis;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Package com.control
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020/9/8 19:27
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
@Api(value = "信息",tags = "用于相关接口")
@RestController
@Controller
@RequestMapping("/com/goods")
public class GoodController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    KillGoodsService killGoodsService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    AmqpAdmin amqpAdmin;

    @ApiOperation(value="aaa",notes = "aaa",httpMethod = "POST")
    @RequestMapping("/page")
    public String page(int i, String info, Model model, HttpServletRequest request, HttpServletResponse response) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String home = null;
        home = ops.get("caches:goodspage" + i);
        if (StringUtils.isBlank(home)||!info.equals(model.getAttribute(info))) {//如果缓存里啥也没有或者搜索条件变化，从数据库查询
            System.out.println(info + "getinfo");
            Map map = new HashMap();
            Page page = goodsService.getGoods(i, info == null || info.equals("") ? null : info);
            List start = new ArrayList();
            List startif = new ArrayList();
            for (int j = 0 ;j<=22;j+=2){
                List<Killgoods> getmiao = getmiao(j, j + 2);
                System.out.println(getmiao);
                if (getmiao==null||getmiao.size()==0){
                    System.out.println(j+"点场"+"啥也没有");
                }else {
                    start.add(getmiao);
                    startif.add(j);
                    for (Killgoods miao:getmiao){
                        ops.set("killcaches:killgoods" + miao.getKillid(), JSON.toJSONString(miao),720, TimeUnit.MINUTES);
                        ops.set("killcaches:stock:" + miao.getKillid(), JSON.toJSONString(miao.getStockcount()),720, TimeUnit.MINUTES);
                    }
                }
            }
            map.put("startif",startif);
            map.put("start",start);
            map.put("goods", page.getRecords());
            map.put("cur", page.getCurrent());
            map.put("size", page.getSize());
            map.put("total", page.getPages());
            map.put("next", page.hasNext() ? page.getCurrent() + 1 : 1);
            map.put("fore", page.hasPrevious() ? page.getCurrent() - 1 : page.getPages());
            map.put("info", info);

            Cus cus = LoginController.cus;
            map.put("cus", cus);
            model.addAttribute("map", map);
            WebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
            home = thymeleafViewResolver.getTemplateEngine().process("home", ctx);
            if (home != null && home != "") {
                ops.set("caches:goodspage" + i, home,90, TimeUnit.SECONDS);
            }
        }
        return home;
    }

    @ApiOperation(value="bbb",notes = "bbb",httpMethod = "POST")
    @RequestMapping("/miaoOrder")
    public String miaoOrder(int kiid){
        System.out.println(kiid);
        Cus sb = LoginController.cus;
        if (sb==null){return "请先登录，而且我寻思你咋点进来呢？";}//登录验证；其实在前端页面已经认证，
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        SetOperations<String, String> setops = stringRedisTemplate.opsForSet();
        String killinf = null;
        Killgoods one;
        killinf = ops.get("killcaches:killgoods" + kiid);
        if (StringUtils.isBlank(killinf)){
            QueryWrapper sql = new QueryWrapper<Killgoods>();
            sql.eq("killid", kiid);
            one = killGoodsService.getOne(sql);
        } else {
            one = JSON.parseObject(killinf, Killgoods.class);
        }
        Long curennt = System.currentTimeMillis();
        System.out.println(one+"oneone");
        Long startat = one.getStartdate().getTime();
        Long endat = one.getEnddate().getTime();
        if (curennt<startat){
            return "活动没开始";
        } else if (curennt>endat){
            return "活动结束了";
        }
        Boolean member = setops.isMember("killcaches:order", sb.getCusid() + ":" + kiid);
        if (member){
            return "这个你买过啦，请关注下一波军火特惠 ฅ^>ﻌ<^ฅ";
        }

        Jedis j = new Jedis("localhost");
        System.out.println("/?????");
        String script = "if tostring(redis.call('get',KEYS[1]))>'0' then redis.call('decr',KEYS[1]) end";
//        String script = "if redis.call('get','killcaches:stock:7')>'0' then redis.call('decr','killcaches:stock:7') end";
        ops.decrement("killcaches:stock:"+kiid);
//        if ( Integer.parseInt(s) <=0){
//            return "没了";
//        }
//        System.out.println(ops.get("killcaches:stock:"+kiid)+"yyye");

        j.eval(script,1,"killcaches:stock:"+kiid);
//        j.eval(s);
        //减一 原子操作
        setops.add("killcaches:orders",sb.getCusid() + ":" + kiid);
        DirectExchange directExchange = new DirectExchange("kill-exchange", true, false, null);
        //rabbitTemplate.decl
        amqpAdmin.declareExchange(directExchange);
        org.springframework.amqp.core.Queue queue = new org.springframework.amqp.core.Queue("queue02",true,false,false,null);
        amqpAdmin.declareQueue(queue);
        Binding binding = new Binding("queue02", Binding.DestinationType.QUEUE, "kill-exchange", "queue02", null);
        amqpAdmin.declareBinding(binding);
        Map mmp = new HashMap();
        mmp.put("userid",sb.getCusid());
        System.out.println("ssssss+kkkkk");
        mmp.put("killid",kiid);
        mmp.put("goodsid",one.getGoodsid());
        mmp.put("goodsname",one.getGoodsname());
        rabbitTemplate.convertAndSend("kill-exchange","queue02",JSON.toJSONString(mmp));
        System.out.println("ssssss+SSS");
        return "秒杀成功正在处理";
    }

    @ApiOperation(value="",notes = "",httpMethod = "POST")
    @RequestMapping("/killGoodsById")
    public Killgoods killGoodsById(@RequestBody Integer killid){
        return killGoodsService.killGoodsById(killid);
    }


    @ApiOperation(value="",notes = "",httpMethod = "POST")
    @RequestMapping("/subKillGoodsById")
    public Integer subKillGoodsById(@RequestBody Integer killid){
        return killGoodsService.subKillGoodsById(killid);
    }

    public List<Killgoods> getmiao(int starhour,int endhour){
        LocalDate now = LocalDate.now();//今天
        String nowstr = now.toString()+" 00:00:00";
        LocalDate tomoraw = now.plusDays(1);//明天
        String tomorawstr = tomoraw.toString()+" 00:00:00";
        List<Killgoods> killgoods = killGoodsService.getKillgoods(new XmlUtilclass(starhour,endhour,nowstr, tomorawstr));
        return killgoods;
    }
}
