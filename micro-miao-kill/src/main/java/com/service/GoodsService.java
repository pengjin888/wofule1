package com.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.model.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Package com.service
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020/9/8 19:27
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
@Mapper
public interface GoodsService extends IService<Goods> {
    Page getGoods(int i, String str);
}
