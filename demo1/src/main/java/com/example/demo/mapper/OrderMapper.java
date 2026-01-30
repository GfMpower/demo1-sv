package com.example.demo.mapper;

import com.example.demo.domian.Order;
import com.example.demo.domian.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OrderMapper {
    //查询所有订单
    List<Order> findAll();
    //根据ID查询订单
    Order findById(Long id);
    //根据订单编号查询订单
    Order findByOrderNo(String orderNo);
    //根据用户ID查询订单
    List<Order> findByUserId(Long userId);
    //分页查询订单
    List<Order> findPage(@Param("orderNo") String orderNo, @Param("userId") Long userId, @Param("status") Integer status);
    //查询订单总数
    int count(@Param("orderNo") String orderNo, @Param("userId") Long userId, @Param("status") Integer status);
//    //根据订单总金额查询订单
//    List<Order> findByTotalAmount(BigDecimal totalAmount);
//    //根据订单状态查询订单
//    List<Order> findByStatus(Integer status);
//    //根据收货人姓名查询订单
//    List<Order> findByReceiverName(String receiverName);
//    //根据收货人电话查询订单
//    List<Order> findByReceiverPhone(String receiverPhone);
//    //根据收货地址查询订单
//    List<Order> findByReceiverAddress(String receiverAddress);
    //插入订单
    int insert(Order order);
    //更新订单
    int update(Order order);
    //删除订单
    int deleteById(Long id);
    //根据订单ID查询订单项
    List<OrderItem> findOrderItemsByOrderId(Long orderId);
    //批量插入订单项
    int insertOrderItems(List<OrderItem> orderItems);
    //根据订单ID删除订单项
    int deleteOrderItemsByOrderId(Long orderId);
}
