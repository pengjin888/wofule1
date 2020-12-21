package com.model;

import lombok.*;

/**
 * @Description
 * @Package com.model
 * @ClassName
 * @Copyright: Copyright (c) 2020
 * @Date 2020/9/11 15:44
 * @Company www.peachfully.com.cn
 * @Author 桃我喜欢
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class XmlUtilclass {
    @Getter@Setter
    private Integer starthour;
    @Getter@Setter
    private Integer endhour;
    @Getter@Setter
    private String startdate;
    @Getter@Setter
    private String enddate;
}
