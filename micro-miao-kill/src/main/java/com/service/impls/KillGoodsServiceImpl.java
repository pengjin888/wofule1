package com.service.impls;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dao.GoodsMapper;
import com.dao.KillgoodsMapper;
import com.model.Killgoods;
import com.model.XmlUtilclass;
import com.service.KillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Package com.service.impls
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020/9/11 11:22
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
@Service("killGoodsService")
public class KillGoodsServiceImpl extends ServiceImpl<KillgoodsMapper,Killgoods> implements KillGoodsService {

    @Autowired
    KillgoodsMapper killgoodsMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List getKillgoods(XmlUtilclass killgoods){
        return killgoodsMapper.getKillgoods(killgoods);
    }

//    @Transactional
    @Override
    public int subKillGoodsById(int goodsid) {
        int subnums = killgoodsMapper.subnums(goodsid);
        int i = goodsMapper.decGoodsstock(goodsid);
        return subnums+i;
    }

    @Override
    public Killgoods killGoodsById(int killid) {
        return killgoodsMapper.selectByPrimaryKey(killid);
    }

}
