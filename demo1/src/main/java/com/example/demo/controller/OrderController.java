package com.example.demo.controller;

import com.example.demo.domian.Order;
import com.example.demo.domian.dto.OrderCreateDTO;
import com.example.demo.domian.dto.OrderUpdateDTO;
import com.example.demo.service.OrderService;
import com.example.demo.utils.PageUtils;
import com.example.demo.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 查询所有订单
    @GetMapping
    public Result<List<Order>> getAllOrders() {
        List<Order> orderList = orderService.findAll();
        return Result.success(orderList);
    }
    /**
     * 分页查询
     * @param pageNum  页码
     * @param pageSize 每页数量
     */
    @GetMapping("/page")
    public Result<Map<String, Object>> getOrderPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String orderNo,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Integer status) {

        List<Order> orders = orderService.findPage(pageNum, pageSize, orderNo, userId, status);
        int total = orderService.count(orderNo, userId, status);

        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        pageInfo.setTotal(total);

        Map<String, Object> pageResult = PageUtils.toPageResult(pageInfo);
        return Result.success(pageResult);
    }

    // 根据ID查询订单
    @GetMapping("/{id}")
    public Result<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        if (order != null) {
            return Result.success(order);
        } else {
            return Result.error("订单不存在");
        }
    }
    /**
     * 创建订单
     */
    @PostMapping
    public Result<Void> createOrder(@RequestBody OrderCreateDTO orderCreateDTO) {
        try {
            boolean success = orderService.insert(orderCreateDTO);
            if (success) {
                return Result.success("创建成功");
            } else {
                return Result.error("创建失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }


//    // 根据订单编号查询
//    @GetMapping("/no/{orderNo}")
//    public Order getOrderByOrderNo(@PathVariable String orderNo) {
//        return orderService.findByOrderNo(orderNo);
//    }
//
//    // 根据用户ID查询
//    @GetMapping("/user/{userId}")
//    public List<Order> getOrdersByUserId(@PathVariable Long userId) {
//        return orderService.findByUserId(userId);
//    }
//
//    // 根据状态查询
//    @GetMapping("/status/{status}")
//    public List<Order> getOrdersByStatus(@PathVariable Integer status) {
//        return orderService.findByStatus(status);
//    }
//    // 根据订单总金额查询
//    @GetMapping("/total/{totalAmount}")
//    public List<Order> getOrdersByTotalAmount(@PathVariable BigDecimal totalAmount) {
//        return orderService.findByTotalAmount(totalAmount);
//    }
//    // 根据收货人姓名查询
//    @GetMapping("/receiverName/{receiverName}")
//    public List<Order> getOrdersByReceiverName(@PathVariable String receiverName) {
//        return orderService.findByReceiverName(receiverName);
//    }
//    // 根据收货人电话查询
//    @GetMapping("/receiverPhone/{receiverPhone}")
//    public List<Order> getOrdersByReceiverPhone(@PathVariable String receiverPhone) {
//        return orderService.findByReceiverPhone(receiverPhone);
//    }
//    // 根据收货地址查询
//    @GetMapping("/receiverAddress/{receiverAddress}")
//    public List<Order> getOrdersByReceiverAddress(@PathVariable String receiverAddress) {
//        return orderService.findByReceiverAddress(receiverAddress);
//    }



    // 更新商品
    @PutMapping
    public Result<Void> updateOrder(@RequestBody OrderUpdateDTO orderUpdateDTO) {
        try {
            boolean success = orderService.update(orderUpdateDTO);
            if (success) {
                return Result.success("更新成功");
            } else {
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    // 删除订单
    @DeleteMapping("/{id}")
    public Result<Void> deleteOrder(@PathVariable Long id) {
        try {
            boolean success = orderService.deleteById(id);
            if (success) {
                return Result.success("删除成功");
            } else {
                return Result.error("删除失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    /**
     * 批量删除订单
     */
    @DeleteMapping("/batch")
    public Result<Void> deleteOrderBatch(@RequestBody List<Long> ids) {
        try {
            boolean success = orderService.deleteBatch(ids);
            if (success) {
                return Result.success("删除成功");
            } else {
                return Result.error("删除失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 更新订单状态
     */
    @PutMapping("/{id}/status")
    public Result<Void> updateOrderStatus(@PathVariable Long id, @RequestParam Integer status) {
        try {
            Boolean success = orderService.updateStatus(id, status);
            if (success) {
                return Result.success("状态更新成功");
            } else {
                return Result.error("状态更新失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}

