package com.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.model.Killgoods;
import com.model.XmlUtilclass;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface KillgoodsMapper extends BaseMapper<Killgoods> {
    int deleteByPrimaryKey(Integer killid);

    @Override
    int insert(Killgoods record);

    int insertSelective(Killgoods record);

    Killgoods selectByPrimaryKey(Integer killid);

    int updateByPrimaryKeySelective(Killgoods record);

    int updateByPrimaryKey(Killgoods record);

    List getKillgoods(XmlUtilclass record);

    int subnums(int goodsid);

    int upstock(int goodsid);
}