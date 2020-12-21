package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.Killorderinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface KillorderinfoMapper extends BaseMapper<Killorderinfo> {
    int deleteByPrimaryKey(Integer killorderid);

    int insert(Killorderinfo record);

    int insertSelective(Killorderinfo record);

    Killorderinfo selectByPrimaryKey(Integer killorderid);

    int updateByPrimaryKeySelective(Killorderinfo record);

    int updateByPrimaryKey(Killorderinfo record);
}