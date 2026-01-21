package com.example.demo.service.impl;

import com.example.demo.domian.OrderItem;
import com.example.demo.mapper.OrderItemMapper;
import com.example.demo.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    //查询所有订单项
    @Override
    public List<OrderItem> findAll() {
        return orderItemMapper.findAll();
    }
    //根据ID查询订单项
    @Override
    public OrderItem findById(Long id) {
        return orderItemMapper.findById(id);
    }
    //根据订单ID查询订单项
    @Override
    public List<OrderItem> findByOrderId(Long orderId) {
        return orderItemMapper.findByOrderId(orderId);
    }
    //根据商品ID查询订单项
    @Override
    public List<OrderItem> findByProductId(Long productId) {
        return orderItemMapper.findByProductId(productId);
    }
    //根据商品名称查询订单项
    @Override
    public List<OrderItem> findByProductName(String productName) {
        return orderItemMapper.findByProductName(productName);
    }
    //根据价格查询订单项
    @Override
    public List<OrderItem> findByPrice(BigDecimal price) {
        return orderItemMapper.findByPrice(price);
    }
    //根据数量查询订单项
    @Override
    public List<OrderItem> findByQuantity(Integer quantity) {
        return orderItemMapper.findByQuantity( quantity);
    }
    //根据小计查询
    @Override
    public List<OrderItem> findBySubtotal(BigDecimal subtotal) {
        return orderItemMapper.findBySubtotal(subtotal);
    }
    //插入订单项
    @Override
    public Boolean insert(OrderItem orderItem) {
        return orderItemMapper.insert(orderItem);
    }
    //更新订单项
    @Override
    public Boolean update(OrderItem orderItem) {
        return orderItemMapper.update(orderItem);
    }
    //删除订单
    @Override
    public Boolean deleteById(Long id) {
        return orderItemMapper.deleteById(id);
    }
    //根据订单ID删除订单项
    @Override
    public Boolean deleteByOrderId(Long orderId) {
        return orderItemMapper.deleteByOrderId(orderId);
    }
    //根据商品ID删除订单项
    @Override
    public Boolean deleteByProductId(Long productId) {
        return orderItemMapper.deleteByProductId(productId);
    }
    //根据商品名称删除订单项
    @Override
    public Boolean deleteByProductName(String productName) {
        return orderItemMapper.deleteByProductName(productName);
    }
}
