package com.cathay.dao;

import com.cathay.model.ProductVO;
import java.util.List;

public interface ProductDAO {
	
	// 新增
    void insertProduct(ProductVO product); 
    
    // 根據商品ID查詢
    ProductVO findProductById(int productId);
    
    // 查詢所有商品
    List<ProductVO> getAllProducts(); 
    
    // 更新商品
    void updateProduct(ProductVO product); 
    
    // 刪除商品
    void deleteProduct(int productId);              
}
