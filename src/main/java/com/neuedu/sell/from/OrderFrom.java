package com.neuedu.sell.from;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class OrderFrom {
    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotEmpty(message = "手机号不能为空")
    private String phone;

    @NotEmpty(message = "地址不能为空")
    private String address;

    @NotEmpty(message = "openid不能为空")
    private String openid;

     @NotEmpty(message = "订单详情不能为空")
    private String items;
}
