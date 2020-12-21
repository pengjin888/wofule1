package com.model;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description= "客户")
public class Cus {
    @TableId
    @ApiModelProperty(name = "upgradeLev",value = "编号）")
    private Integer cusid;
    @ApiModelProperty(name = "upgradeLev",value = "姓名）")
    private String name;
    @ApiModelProperty(name = "upgradeLev",value = "密码）")
    private String password;
    @ApiModelProperty(name = "upgradeLev",value = "余额）")
    private Integer money;
    @Getter@Setter
    @ApiModelProperty(name = "upgradeLev",value = "头像地址）")
    private String headurl;
    public Integer getCusid() {
        return cusid;
    }
    public void setCusid(Integer cusid) {
        this.cusid = cusid;
    }
    @ApiModelProperty(name = "upgradeLev",value = "信息）")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
    public Integer getMoney() {
        return money;
    }
    public void setMoney(Integer money) {
        this.money = money;
    }
}