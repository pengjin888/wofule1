package com.service.impls;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dao.GoodsMapper;
import com.model.Goods;
import com.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @Description
 * @Package com.service
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020/9/8 19:29
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService  {
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public Page getGoods(int i ,String str) {
        QueryWrapper sql = new QueryWrapper<Goods>();
        if (str!=null){sql.like("goodsname",str);}
        Page<Goods> page = new Page<>();
        page.setCurrent(i);
        page.setSize(17);
        Page page1 = goodsMapper.selectPage(page, sql);
        return page1;
    }

    @Override
    public boolean saveBatch(Collection<Goods> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Goods> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Goods> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Goods entity) {
        return false;
    }

    @Override
    public Goods getOne(Wrapper<Goods> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Goods> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Goods> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Goods> getBaseMapper() {
        return null;
    }
}
