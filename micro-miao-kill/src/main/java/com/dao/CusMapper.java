package com.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.Cus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CusMapper extends BaseMapper<Cus> {
    int deleteByPrimaryKey(Integer cusid);

    @Override
    int insert(Cus record);

    int insertSelective(Cus record);

    Cus selectByPrimaryKey(Integer cusid);

    int updateByPrimaryKeySelective(Cus record);

    int updateByPrimaryKey(Cus record);
}