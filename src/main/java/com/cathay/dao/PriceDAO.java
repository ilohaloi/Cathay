package com.cathay.dao;

import com.cathay.model.PriceVO;

import java.sql.Date;
import java.util.List;

public interface PriceDAO {


    // 新增某日價格
    void insertPriceForDate(int productId, Date priceDate, PriceVO priceVO);

    // 修改某日價格
    void updatePriceForDate(int productId, Date priceDate, PriceVO priceVO);

    // 刪除某日價格
    void deletePriceForDate(int productId, Date priceDate);

    // 查詢某日價格
    PriceVO findPriceByProductIdAndDate(int productId, Date priceDate);

    // 根據商品 ID 查詢所有價格記錄
    List<PriceVO> findPricesByProductId(int productId);

    // 查詢所有價格記錄
    List<PriceVO> getAllPrices();
}