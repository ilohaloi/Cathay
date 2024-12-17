package com.cathay.dao;

import com.cathay.model.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 新增商品
    @Override
    public void insertProduct(ProductVO product) {
        String sql = "INSERT INTO product_table (product_id, product_name, product_short_name, product_group) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
                product.getProdId(), 
                product.getProdName(), 
                product.getProdShortName(), 
                product.getProdGroup());
    }

    // 根據商品ID查詢
    @Override
    public ProductVO findProductById(int productId) {
        String sql = "SELECT product_id, product_name, product_short_name, product_group FROM product_table WHERE product_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{productId}, new ProductRowMapper());
    }

    // 查詢所有商品
    @Override
    public List<ProductVO> getAllProducts() {
        String sql = "SELECT product_id, product_name, product_short_name, product_group FROM product_table";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    // 更新商品
    @Override
    public void updateProduct(ProductVO product) {
        String sql = "UPDATE product_table SET product_name = ?, product_short_name = ?, product_group = ? WHERE product_id = ?";
        jdbcTemplate.update(sql, 
                product.getProdName(), 
                product.getProdShortName(), 
                product.getProdGroup(), 
                product.getProdId());
    }

    // 刪除商品
    @Override
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM product_table WHERE product_id = ?";
        jdbcTemplate.update(sql, productId);
    }

    // RowMapper 實現，將 ResultSet 映射到 ProductVO
    private static class ProductRowMapper implements RowMapper<ProductVO> {
        @Override
        public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProductVO product = new ProductVO();
            product.setProdId(rs.getInt("product_id"));
            product.setProdName(rs.getString("product_name"));
            product.setProdShortName(rs.getString("product_short_name"));
            product.setProdGroup(rs.getBoolean("product_group"));
            return product;
        }
    }
}