package com.example.demo.mapper;

import com.example.demo.domian.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem>{
    //查询所有订单项
    List<OrderItem> findAll();
    //根据ID查询订单项
    OrderItem findById(Long id);
    //根据订单ID查询订单项
    List<OrderItem> findByOrderId(Long orderId);
    //根据商品ID查询订单项
    List<OrderItem> findByProductId(Long productId);
    //根据商品名称查询订单项
    List<OrderItem> findByProductName(String productName);
    //根据价格查询订单项
    List<OrderItem> findByPrice(BigDecimal price);
    //根据数量查询订单项
    List<OrderItem> findByQuantity(Integer quantity);
    //根据小计查询订单项
    List<OrderItem> findBySubtotal(BigDecimal subtotal);
    //插入订单项
    Boolean insert(OrderItem orderItem);
    //更新订单项
    Boolean update(OrderItem orderItem);
    //删除订单项
    Boolean deleteById(Long id);
    //根据订单ID删除订单项
    Boolean deleteByOrderId(Long orderId);
    //根据商品ID删除订单项
    Boolean deleteByProductId(Long productId);
    //根据商品名称删除订单项
    Boolean deleteByProductName(String productName);
}
