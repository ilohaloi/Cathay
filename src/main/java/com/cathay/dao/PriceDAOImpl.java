package com.cathay.dao;

import com.cathay.model.PriceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PriceDAOImpl implements PriceDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    // 新增某日價格
    @Override
    public void insertPriceForDate(int productId, Date priceDate, PriceVO priceVO) {
        String sql = "INSERT INTO price_table (product_id, price_date, product_price) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, productId, priceDate, priceVO.getProduct_price());
    }

    // 修改某日價格
    @Override
    public void updatePriceForDate(int productId, Date priceDate, PriceVO priceVO) {
        String sql = "UPDATE price_table SET product_price = ? WHERE product_id = ? AND price_date = ?";
        jdbcTemplate.update(sql, priceVO.getProduct_price(), productId, priceDate);
    }

    // 刪除某日價格
    @Override
    public void deletePriceForDate(int productId, Date priceDate) {
        String sql = "DELETE FROM price_table WHERE product_id = ? AND price_date = ?";
        jdbcTemplate.update(sql, productId, priceDate);
    }

    // 根據商品 ID 和日期查詢價格
    @Override
    public PriceVO findPriceByProductIdAndDate(int productId, Date priceDate) {
        String sql = "SELECT product_id, price_date, product_price FROM price_table WHERE product_id = ? AND price_date = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{productId, priceDate}, new PriceRowMapper());
    }

    // 根據商品 ID 查詢所有價格記錄
    @Override
    public List<PriceVO> findPricesByProductId(int productId) {
        String sql = "SELECT product_id, price_date, product_price FROM price_table WHERE product_id = ?";
        return jdbcTemplate.query(sql, new Object[]{productId}, new PriceRowMapper());
    }

    // 查詢所有價格記錄
    @Override
    public List<PriceVO> getAllPrices() {
        String sql = "SELECT product_id, price_date, product_price FROM price_table";
        return jdbcTemplate.query(sql, new PriceRowMapper());
    }

    // RowMapper 實現，將 ResultSet 映射為 PriceVO
    private static class PriceRowMapper implements RowMapper<PriceVO> {
        @Override
        public PriceVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            PriceVO priceVO = new PriceVO();
            priceVO.setProduct_id(rs.getInt("product_id"));
            priceVO.setPrice_date(rs.getDate("price_date"));
            priceVO.setProduct_price(rs.getBigDecimal("product_price"));
            return priceVO;
        }
    }
}