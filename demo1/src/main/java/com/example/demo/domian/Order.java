package com.example.demo.domian;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class Order extends BaseEntity{
    private static final long serialVersionUID = 1L;
    private String orderNo; // 订单编号
    private Long userId; // 用户ID
    private BigDecimal totalAmount; // 总金额
    private Integer status; // 订单状态 0-已取消 1-待支付 2-已支付 3-已发货 4-已完成
    private String receiverName; // 收货人人姓名
    private String receiverPhone; // 收货人电话
    private String receiverAddress; // 收货地址
}
