package com.service.impls;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dao.CusMapper;
import com.model.Cus;
import com.service.UsersService;
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
 * @Date 2020/9/8 15:40
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {

    @Autowired
    CusMapper cusMapper;
    @Override
    public Cus exi(String name) {
        QueryWrapper sql = new QueryWrapper<Cus>();
        sql.eq("name", name);
        List list = cusMapper.selectList(sql);
        return (Cus) list.get(0);
    }
    @Override
    public List getUsers( int cpage,Cus cus) {
        QueryWrapper sql = new QueryWrapper<Cus>();
        Page<Cus> page = new Page<>();
        page.setCurrent(cpage);
        page.setSize(5);
        List<Cus> cuses = (List<Cus>) cusMapper.selectPage(page,sql);
        System.out.println(page);
        return cuses;
    }

    @Override
    public boolean saveBatch(Collection<Cus> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Cus> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Cus> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Cus entity) {
        return false;
    }

    @Override
    public Cus getOne(Wrapper<Cus> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Cus> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Cus> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Cus> getBaseMapper() {
        return null;
    }
}
