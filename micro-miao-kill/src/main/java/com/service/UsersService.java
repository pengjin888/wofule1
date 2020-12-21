package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.Cus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Package com.service
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020/9/8 15:39
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
@Mapper
public interface UsersService extends IService<Cus> {
    Cus exi(String name);
    List getUsers(int cpage,Cus cus);

}
