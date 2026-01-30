package com.example.demo.domian.dto;

import com.example.demo.domian.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
/**
 * 订单创建DTO
 */
@Data
public class OrderCreateDTO {
    private Long userId; //用户ID
    private BigDecimal totalAmount; // 总金额
    private Integer status; // 订单状态 0-已取消 1-待支付 2-已支付 3-已发货 4-已完成
    private String receiverName; // 收货人人姓名
    private String receiverPhone; // 收货人电话
    private String receiverAddress; // 收货地址
    private List<OrderItem> orderItems;//订单项列表

    /**
     * 订单项创建DTO
     */
    @Data
    private  static class OrderItemDTO{
        private Long productId; // 商品ID
        private String productName; // 商品名称
        private BigDecimal price; // 商品价格
        private Integer quantity; // 购买数量
        private BigDecimal subtotal; // 小计
    }
}
