package com.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Orderinfo {
    @TableId(type= IdType.AUTO)
    private Integer orderid;

    private Integer userid;

    private Integer goodsid;

    private String goodsname;

    private Integer goodscount;

    private Integer goodsprice;

    private Integer orderstatus;

    private Date createdate;

    private Date paydate;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
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

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public Integer getGoodscount() {
        return goodscount;
    }

    public void setGoodscount(Integer goodscount) {
        this.goodscount = goodscount;
    }

    public Integer getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(Integer goodsprice) {
        this.goodsprice = goodsprice;
    }

    public Integer getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Integer orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public Orderinfo(Integer userid, Integer goodsid, String goodsname, Integer goodscount, Integer goodsprice, Integer orderstatus, Date createdate) {
        this.userid = userid;
        this.goodsid = goodsid;
        this.goodsname = goodsname;
        this.goodscount = goodscount;
        this.goodsprice = goodsprice;
        this.orderstatus = orderstatus;
        this.createdate = createdate;
    }
}