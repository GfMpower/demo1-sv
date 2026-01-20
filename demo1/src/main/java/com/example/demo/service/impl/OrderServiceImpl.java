package com.example.demo.service.impl;

import com.example.demo.domian.Order;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    //查询所有订单
    @Override
    public List<Order> findAll() {
        return orderMapper.findAll();
    }
    //根据ID查询订单
    @Override
    public Order findById(Long id) {
        return orderMapper.findById(id);
    }
    //根据订单编号查询订单
    @Override
    public Order findByOrderNo(String orderNo) {
        return orderMapper.findByOrderNo(orderNo);
    }
    //根据用户ID查询订单
    @Override
    public List<Order> findByUserId(Long userId) {
        return orderMapper.findByUserId(userId);
    }
    //根据订单总金额查询订单
    @Override
    public List<Order> findByTotalAmount(BigDecimal totalAmount) {
        return orderMapper.findByTotalAmount(totalAmount);
    }
    //根据订单状态查询订单
    @Override
    public List<Order> findByStatus(Integer status) {
        return orderMapper.findByStatus(status);
    }
    //根据收货人姓名查询订单
    @Override
    public List<Order> findByReceiverName(String receiverName) {
        return orderMapper.findByReceiverName(receiverName);
    }
    //根据收货人电话查询订单
    @Override
    public List<Order> findByReceiverPhone(String receiverPhone) {
        return orderMapper.findByReceiverPhone(receiverPhone);
    }
    //根据收货地址查询订单
    @Override
    public List<Order> findByReceiverAddress(String receiverAddress) {
        return orderMapper.findByReceiverAddress(receiverAddress);
    }
    //插入订单
    @Override
    public Boolean insert(Order order) {
        return orderMapper.insert(order);
    }
    //更新订单
    @Override
    public Boolean update(Order order) {
        return orderMapper.update(order);
    }
    //删除订单
    @Override
    public Boolean deleteById(Long id) {
        return orderMapper.deleteById(id);
    }
}
