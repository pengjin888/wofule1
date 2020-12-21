package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.Orderinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderinfoMapper extends BaseMapper<Orderinfo> {
    int deleteByPrimaryKey(Integer orderid);

    int insert(Orderinfo record);

    int insertSelective(Orderinfo record);

    Orderinfo selectByPrimaryKey(Integer orderid);

    int updateByPrimaryKeySelective(Orderinfo record);

    int updateByPrimaryKey(Orderinfo record);
}