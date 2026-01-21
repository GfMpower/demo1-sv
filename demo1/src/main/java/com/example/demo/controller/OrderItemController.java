package com.example.demo.controller;

import com.example.demo.domian.OrderItem;
import com.example.demo.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orderItem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;
    //查询所有订单项
    @GetMapping("/all")
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.findAll();
    }
    //根据ID查询订单项
    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable Long id) {
        return orderItemService.findById(id);
    }
    //根据订单ID查询订单项
    @GetMapping("/orderId/{orderId}")
    public List<OrderItem> getOrderItemsByOrderId(@PathVariable Long orderId) {
        return orderItemService.findByOrderId(orderId);
    }
    //根据商品ID查询订单项
    @GetMapping("/productId/{productId}")
    public List<OrderItem> getOrderItemsByProductId(@PathVariable Long productId) {
        return orderItemService.findByProductId(productId);
    }
    //根据商品名称查询订单项
    @GetMapping("/productName/{productName}")
    public List<OrderItem> getOrderItemsByProductName(@PathVariable String productName) {
        return orderItemService.findByProductName(productName);
    }
    //根据价格查询订单项
    @GetMapping("/price/{price}")
    public List<OrderItem> getOrderItemsByPrice(@PathVariable BigDecimal price) {
        return orderItemService.findByPrice(price);
    }
    //根据数量查询订单项
    @GetMapping("/quantity/{quantity}")
    public List<OrderItem> getOrderItemsByQuantity(@PathVariable Integer quantity) {
        return orderItemService.findByQuantity(quantity);
    }
    //根据小计查询订单项
    @GetMapping("/subtotal/{subtotal}")
    public List<OrderItem> getOrderItemsBySubtotal(@PathVariable BigDecimal subtotal) {
        return orderItemService.findBySubtotal(subtotal);
    }

}
