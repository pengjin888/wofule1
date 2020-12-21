package com.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.model.Killgoods;
import com.model.XmlUtilclass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Package com.service
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020/9/11 11:21
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
@Mapper
public interface KillGoodsService  extends IService<Killgoods> {
    List getKillgoods(XmlUtilclass killgoods);

    int subKillGoodsById(int goodsid);

    Killgoods killGoodsById(int killid);
}

