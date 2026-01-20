package com.example.demo.domian;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderItem extends BaseEntity{
    private Long orderId; // 订单ID
    private Long productId; // 商品ID
    private String productName; // 商品名称
    private BigDecimal price; // 商品价格
    private Integer quantity; // 购买数量
    private BigDecimal subtotal; // 小计
}
