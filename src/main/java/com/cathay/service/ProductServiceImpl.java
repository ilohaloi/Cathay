package com.cathay.service;

import com.cathay.dao.ProductDAO;
import com.cathay.model.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 標記為 Service 層
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    // 新增商品，使用 @Transactional 保證事務性
    @Override
    @Transactional
    public void insertProduct(ProductVO productVO) {
        productDAO.insertProduct(productVO);
    }

    // 根據商品 ID 查詢
    @Override
    public ProductVO getProductById(int productId) {
        return productDAO.findProductById(productId);
    }

    // 查詢所有商品
    @Override
    public List<ProductVO> getAllProducts() {
        return productDAO.getAllProducts();
    }

    // 更新商品
    @Override
    @Transactional
    public void updateProduct(ProductVO productVO) {
        productDAO.updateProduct(productVO);
    }

    // 刪除商品
    @Override
    @Transactional
    public void deleteProduct(int productId) {
        productDAO.deleteProduct(productId);
    }
}