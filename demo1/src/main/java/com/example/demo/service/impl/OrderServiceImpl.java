package com.example.demo.service.impl;

import com.example.demo.domian.Order;
import com.example.demo.domian.OrderItem;
import com.example.demo.domian.dto.OrderCreateDTO;
import com.example.demo.domian.dto.OrderUpdateDTO;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.OrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        Order order = orderMapper.findById(id);
        if(order!= null){
            //查询订单项
            List<OrderItem> orderItems = orderMapper.findOrderItemsByOrderId(id);
            order.setOrderItems(orderItems);
        }
        return order;
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
//    @Override
//    public List<Order> findByTotalAmount(BigDecimal totalAmount) {
//        return orderMapper.findByTotalAmount(totalAmount);
//    }
//    //根据订单状态查询订单
//    @Override
//    public List<Order> findByStatus(Integer status) {
//        return orderMapper.findByStatus(status);
//    }
//    //根据收货人姓名查询订单
//    @Override
//    public List<Order> findByReceiverName(String receiverName) {
//        return orderMapper.findByReceiverName(receiverName);
//    }
//    //根据收货人电话查询订单
//    @Override
//    public List<Order> findByReceiverPhone(String receiverPhone) {
//        return orderMapper.findByReceiverPhone(receiverPhone);
//    }
//    //根据收货地址查询订单
//    @Override
//    public List<Order> findByReceiverAddress(String receiverAddress) {
//        return orderMapper.findByReceiverAddress(receiverAddress);
//    }
    //分页查询订单
    @Override
    public List<Order> findPage(int pageNum, int pageSize, String orderNo, Long userId, Integer status){
        PageHelper.startPage(pageNum,pageSize);
        return orderMapper.findPage(orderNo,userId,status);
    }
    //查询订单总数
    @Override
    public int count(String orderNo, Long userId, Integer status) {
        return orderMapper.count(orderNo,userId,status);
    }
    //插入订单
    @Override
    @Transactional//事务管理, 当操作数据库次数达到两次或多次以上, 只有当所有操作全部执行完毕, 方法才会走完
    public Boolean insert(OrderCreateDTO orderCreateDTO) {
        //1.创建订单对象
        Order order = new Order();
        BeanUtils.copyProperties(orderCreateDTO,order);

        //2.生成订单编号
        String orderNo = generateOrderNo();
        order.setOrderNo(orderNo);

        //3.设置创建时间和更新时间
        Date now = new Date();
        order.setCreateTime(now);
        order.setUpdateTime(now);

        //4.插入订单
        int result = orderMapper.insert(order);
        if (result <= 0 ){
            return  false;
        }

        //5.创建订单项列表
        Long orderId = order.getId();
        List<OrderItem> orderItems = orderCreateDTO.getOrderItems().stream().map(dto -> {
            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setProductId(dto.getProductId());
            item.setProductName(dto.getProductName());
            item.setPrice(dto.getPrice());
            item.setQuantity(dto.getQuantity());
            item.setSubtotal(dto.getSubtotal());
            item.setCreateTime(now);
            item.setUpdateTime(now);
            return item;
        }).toList();

        //6.批量插入订单项
        if (!orderItems.isEmpty()) {
            orderMapper.insertOrderItems(orderItems);
        }

        return true;
    }
    //生成订单编号
    private String generateOrderNo() {
        return "ORD" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 8);
    }

    //更新订单
    @Override
    public Boolean update(OrderUpdateDTO orderUpdateDTO) {
        Order order = orderMapper.findById(orderUpdateDTO.getId());
        if (order == null) {
            return false;
        }

        BeanUtils.copyProperties(orderUpdateDTO, order);

        //设置更新时间
        order.setUpdateTime(new Date());

        return orderMapper.update(order) > 0;
    }

    //删除订单
    @Override
    public Boolean deleteById(Long id) {
        //先删除订单项
        orderMapper.deleteOrderItemsByOrderId(id);
        //再删除订单
        return orderMapper.deleteById(id) > 0;
    }

    //批量删除订单
    public Boolean deleteBatch(List<Long> ids) {
        boolean result = true;
        for (Long id : ids) {
            if (!deleteById(id)) {
                result = false;
            }
        }
        return result;
    }

    //更新订单状态
    @Override
    public Boolean updateStatus(Long id, Integer status) {
        Order order = new Order();
        order.setId(id);
        order.setStatus(status);
        order.setUpdateTime(new Date());
        return orderMapper.update(order) > 0;
    }
}
