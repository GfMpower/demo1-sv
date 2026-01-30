package com.example.demo.service;

import com.example.demo.domian.Order;
import com.example.demo.domian.dto.OrderCreateDTO;
import com.example.demo.domian.dto.OrderUpdateDTO;

import java.math.BigDecimal;
import java.util.List;
/**
 * 订单Service接口
 */
public interface OrderService {
    //查询所有订单
    List<Order> findAll();
    //根据ID查询订单
    Order findById(Long id);
    //根据订单编号查询订单
    Order findByOrderNo(String orderNo);
    //根据用户ID查询订单
    List<Order> findByUserId(Long userId);
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
    //分页查询订单
    List<Order> findPage(int pageNum, int pageSize, String orderNo, Long userId, Integer status);
    //查询订单总数
    int count(String orderNo,Long userId, Integer status);
    //插入订单
    Boolean insert(OrderCreateDTO orderCreateDTO);
    //更新订单
    Boolean update(OrderUpdateDTO orderUpdateDTO);
    //删除订单
    Boolean deleteById(Long id);
    //批量删除订单
    Boolean deleteBatch(List<Long> ids);
    //更新订单状态
    Boolean updateStatus(Long id, Integer status);
}
