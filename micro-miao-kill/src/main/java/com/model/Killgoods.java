package com.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Killgoods {
    @TableId
    private Integer killid;

    private Integer goodsid;

    private Integer stockcount;

    private Date startdate;

    private Date enddate;

    private Integer killprice;
    @Getter@Setter
    private String goodsname;
    @Getter@Setter
    private String goodsimg;
    @Getter@Setter
    private int goodsprice;

    public Integer getKillid() {
        return killid;
    }

    public void setKillid(Integer killid) {
        this.killid = killid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getStockcount() {
        return stockcount;
    }

    public void setStockcount(Integer stockcount) {
        this.stockcount = stockcount;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getKillprice() {
        return killprice;
    }

    public void setKillprice(Integer killprice) {
        this.killprice = killprice;
    }

    public Killgoods(Date startdate, Date enddate) {
        this.startdate = startdate;
        this.enddate = enddate;
    }
}