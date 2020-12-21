package com.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Goods {
    @TableId
    private Integer goodsid;

    private String goodsname;

    private String goodsimg;

    private String goodscontent;

    private Integer goodsprice;

    private Integer goodsstock;

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

    public String getGoodsimg() {
        return goodsimg;
    }

    public void setGoodsimg(String goodsimg) {
        this.goodsimg = goodsimg == null ? null : goodsimg.trim();
    }

    public String getGoodscontent() {
        return goodscontent;
    }

    public void setGoodscontent(String goodscontent) {
        this.goodscontent = goodscontent == null ? null : goodscontent.trim();
    }

    public Integer getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(Integer goodsprice) {
        this.goodsprice = goodsprice;
    }

    public Integer getGoodsstock() {
        return goodsstock;
    }

    public void setGoodsstock(Integer goodsstock) {
        this.goodsstock = goodsstock;
    }
}