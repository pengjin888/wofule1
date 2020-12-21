package com.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Killorderinfo {
    @TableId(type= IdType.AUTO)
    private Integer killorderid;

    private Integer userid;

    private Integer goodsid;

    private Integer orderid;

    public Integer getKillorderid() {
        return killorderid;
    }

    public void setKillorderid(Integer killorderid) {
        this.killorderid = killorderid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Killorderinfo(Integer userid, Integer goodsid, Integer orderid) {
        this.userid = userid;
        this.goodsid = goodsid;
        this.orderid = orderid;
    }
}