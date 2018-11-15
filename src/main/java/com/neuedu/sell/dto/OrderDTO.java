package com.neuedu.sell.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.neuedu.sell.entity.OrderDetail;
import com.neuedu.sell.enums.OrderStatusEnum;
import com.neuedu.sell.enums.PayStatusEnum;
import com.neuedu.sell.utils.EnumUtils;
import com.neuedu.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    private String orderId;
    /* 买家名字 */
    private String buyerName;

    /* 买家电话 */
    private String buyerPhone;

    /* 买家地址 */
    private String buyerAddress;

    /* 用户openid */
    private String buyerOpenid;

    /* 订单总金额 */
    private BigDecimal orderAmount;

    /* 订单状态 */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /* 支付状态，0为未支付，1为已支付 */
    private Integer payStatus = PayStatusEnum.NOT_PAY.getCode();
    @JsonSerialize(using = Date2LongSerializer.class)
    /* 创建时间 */
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    /* 修改时间 */
    private Date updateTime;

    /* 订单详情的集合 */
    private List<OrderDetail> orderDetailList;

    //根据orderStatus的值来返回对应的OrderStatusEnum
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtils.getEnumByCode(orderStatus,OrderStatusEnum.class);
    }

    public PayStatusEnum getPayStatusEnum() {
        return EnumUtils.getEnumByCode(payStatus,PayStatusEnum.class);

    }

}
