package com.example.demo.controller;

import com.example.demo.domian.Product;
import com.example.demo.domian.dto.ProductCreateDTO;
import com.example.demo.domian.dto.ProductUpdateDTO;
import com.example.demo.service.ProductService;
import com.example.demo.utils.PageUtils;
import com.example.demo.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 商品Controller
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    /**
     * 查询所有商品
     */
    @GetMapping
    public Result<List<Product>>findAll() {
        List<Product> products = productService.findAll();
        return Result.success(products);
    }

    /**
     * 分页查询
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param name 商品名称（可选）
     * @param categoryId 分类ID（可选）
     * @return 商品分页数据
     */
    @GetMapping("/page")
    public Result<Map<String, Object>> getUserPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long categoryId
    ){
        List<Product> products = productService.findPage(pageNum, pageSize, name, categoryId);
        int total = productService.count(name, categoryId);

        PageInfo<Product> pageInfo =new PageInfo<>(products);
        pageInfo.setTotal(total);

        Map<String, Object> PageResult = PageUtils.toPageResult(pageInfo);
        return Result.success(PageResult);
    }

    /**
     * 根据ID查询商品
     */
    @GetMapping("/{id}")
    public Result<Product> getProductById(@PathVariable Long id){
        Product product = productService.findById(id);
        if (product != null ){
            return Result.success(product);
        } else {
            return Result.error("商品不存在");
        }
    }
    /**
     * 创建商品
     */
    @PostMapping
    public Result<Void> createProduct(@RequestBody ProductCreateDTO productCreateDTO){
        try{
            Boolean success = productService.insert(productCreateDTO);
            if (success){
                return Result.success("创建商品成功");
            } else {
                return Result.error("创建商品失败");
            }
        } catch(Exception e){
            return Result.error(e.getMessage());
        }
    }
    /**
     * 更新商品
     */
    @PutMapping
    public Result<Void> updateProduct(@RequestBody ProductUpdateDTO productUpdateDTO) {
        try {
            boolean success = productService.update(productUpdateDTO);
            if (success) {
                return Result.success("更新成功");
            } else {
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 删除商品
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteProduct(@PathVariable Long id) {
        try {
            boolean success = productService.deleteById(id);
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
     * 批量删除商品
     */
    @DeleteMapping("/batch")
    public Result<Void> deleteProduct(@RequestBody List<Long> ids) {
        try {
            boolean success = productService.deleteBatch(ids);
            if (success) {
                return Result.success("删除成功");
            } else {
                return Result.error("删除失败");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

}
