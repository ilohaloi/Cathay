package com.cathay.service;

import com.cathay.model.ProductVO;
import java.util.List;

public interface ProductService {
    
    void insertProduct(ProductVO productVO); // 新增商品

    ProductVO getProductById(int productId); // 根據商品 ID 查詢

    List<ProductVO> getAllProducts(); // 查詢所有商品

    void updateProduct(ProductVO productVO); // 更新商品

    void deleteProduct(int productId); // 刪除商品
}