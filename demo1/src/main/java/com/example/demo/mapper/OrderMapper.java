package com.example.demo.mapper;

import com.example.demo.domian.Order;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    //查询所有订单
    List<Order> findAll();
    //根据ID查询订单
    Order findById(Long id);
    //根据订单编号查询订单
    Order findByOrderNo(String orderNo);
    //根据用户ID查询订单
    List<Order> findByUserId(Long userId);
    //根据订单总金额查询订单
    List<Order> findByTotalAmount(BigDecimal totalAmount);
    //根据订单状态查询订单
    List<Order> findByStatus(Integer status);
    //根据收货人姓名查询订单
    List<Order> findByReceiverName(String receiverName);
    //根据收货人电话查询订单
    List<Order> findByReceiverPhone(String receiverPhone);
    //根据收货地址查询订单
    List<Order> findByReceiverAddress(String receiverAddress);
    //插入订单
    Boolean insert(Order order);
    //更新订单
    Boolean update(Order order);
    //删除订单
    Boolean deleteById(Long id);
}
