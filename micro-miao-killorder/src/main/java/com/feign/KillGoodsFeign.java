package com.feign;

import com.model.Killgoods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Package com.feign
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020/10/13 10:41
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
@FeignClient("micro-miao-kill")
public interface KillGoodsFeign {
    @RequestMapping("/com/goods/killGoodsById")
    public Killgoods killGoodsById(@RequestBody Integer id);
    @RequestMapping("/com/goods/subKillGoodsById")
    public Integer subKillGoodsById(@RequestBody Integer id);
}
