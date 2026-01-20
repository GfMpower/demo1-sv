package com.example.demo.controller;

import com.example.demo.domian.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 查询所有订单
    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    // 根据ID查询订单
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.findById(id);
    }

    // 根据订单编号查询
    @GetMapping("/no/{orderNo}")
    public Order getOrderByOrderNo(@PathVariable String orderNo) {
        return orderService.findByOrderNo(orderNo);
    }

    // 根据用户ID查询
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.findByUserId(userId);
    }

    // 根据状态查询
    @GetMapping("/status/{status}")
    public List<Order> getOrdersByStatus(@PathVariable Integer status) {
        return orderService.findByStatus(status);
    }
    // 根据订单总金额查询
    @GetMapping("/total/{totalAmount}")
    public List<Order> getOrdersByTotalAmount(@PathVariable BigDecimal totalAmount) {
        return orderService.findByTotalAmount(totalAmount);
    }
    // 根据收货人姓名查询
    @GetMapping("/receiverName/{receiverName}")
    public List<Order> getOrdersByReceiverName(@PathVariable String receiverName) {
        return orderService.findByReceiverName(receiverName);
    }
    // 根据收货人电话查询
    @GetMapping("/receiverPhone/{receiverPhone}")
    public List<Order> getOrdersByReceiverPhone(@PathVariable String receiverPhone) {
        return orderService.findByReceiverPhone(receiverPhone);
    }
    // 根据收货地址查询
    @GetMapping("/receiverAddress/{receiverAddress}")
    public List<Order> getOrdersByReceiverAddress(@PathVariable String receiverAddress) {
        return orderService.findByReceiverAddress(receiverAddress);
    }

    // 新增订单
    @PostMapping
    public Boolean insertOrder(@RequestBody Order order) {
        return orderService.insert(order);
    }

    // 更新订单
    @PutMapping
    public Boolean updateOrder(@RequestBody Order order) {
        return orderService.update(order);
    }

    // 删除订单
    @DeleteMapping("/{id}")
    public Boolean deleteOrder(@PathVariable Long id) {
        return orderService.deleteById(id);
    }
}

