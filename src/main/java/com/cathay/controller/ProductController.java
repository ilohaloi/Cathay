package com.cathay.controller;

import com.cathay.model.ProductVO;
import com.cathay.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 標記此類為 RESTful API 控制器
@RequestMapping("/api/products") // 基本路徑
public class ProductController {

    @Autowired
    private ProductService productService;

    // 1. 查詢所有商品
    @GetMapping
    public ResponseEntity<List<ProductVO>> getAllProducts() {
        List<ProductVO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // 2. 根據 ID 查詢單個商品
    @GetMapping("/{id}")
    public ResponseEntity<ProductVO> getProductById(@PathVariable int id) {
        ProductVO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // 3. 新增商品
    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductVO productVO) {
        productService.insertProduct(productVO);
        return ResponseEntity.ok("商品新增成功");
    }

    // 4. 更新商品
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody ProductVO productVO) {
        productVO.setProdId(id);
        productService.updateProduct(productVO);
        return ResponseEntity.ok("商品更新成功");
    }

    // 5. 刪除商品
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("商品刪除成功");
    }
}