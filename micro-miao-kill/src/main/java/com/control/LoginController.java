package com.control;

import com.model.Cus;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Package com.control
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020/9/8 15:35
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
@RequestMapping("/com/users")
@Controller
public class LoginController {

    @Autowired
    UsersService usersService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @ResponseBody
    @RequestMapping("/exi")
    public String exi(HttpServletRequest request,String name){
        Object exi = usersService.exi(name);
        if (exi==null){
            return "false";
        }
        request.setAttribute("something","傻逼");
        return "true";
    }
    public static Cus cus = null;
    @ResponseBody
    @RequestMapping("/login1")
    public Map login(@RequestParam String name, @RequestParam("password") String password,Model model){
        System.out.println("jin和后台");
        Cus exi = usersService.exi(name);
        if (exi!=null&&password.equals(exi.getPassword())){
            Map mpp = new HashMap();
            mpp.put("myif",exi);
            model.addAttribute("cus",exi);
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            String str = ops.get("LastTime:"+name);
            if (str==null||"".equals(str)){
                str="首次登录";
            }
            mpp.put("Lasttime",str);
            cus=exi;
            return mpp;
        }else {
            return null;
        }
    }
    //登出方法
    @GetMapping("/lgout")
    public String out(Model model){
        model.addAttribute("cus",null);
        SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy-MM-dd hh:mm:ss ");
        String date = dateFormat.format(new Date());
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("LastTime:"+"姓名",date);
        cus=null;
        return "redirect:../goods/index.html";
    }
    @RequestMapping("/sb")
    public String sb(HttpServletRequest model){
        model.setAttribute("something","傻逼");
        System.out.println("sb");
        return "htm.html";
    }
    @ResponseBody
    @RequestMapping("/exame")
    public Map exame(){
        Map mpp = new HashMap();
        mpp.put("myif",cus);
        return mpp;
    }

}
