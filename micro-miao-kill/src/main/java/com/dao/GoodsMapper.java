package com.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    int deleteByPrimaryKey(Integer goodsid);

    @Override
    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer goodsid);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    int decGoodsstock(int goodsid);

    int upGoodsstock(int goodsid);
}