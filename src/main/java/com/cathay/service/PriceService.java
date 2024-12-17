package com.cathay.service;

import com.cathay.model.PriceVO;
import java.sql.Date;
import java.util.List;

public interface PriceService {
    // 新增某日價格
    void insertPriceForDate(int productId, Date priceDate, PriceVO priceVO);

    // 查詢某日價格
    PriceVO getPriceByProductIdAndDate(int productId, Date priceDate);

    // 查詢所有價格記錄
    List<PriceVO> getAllPrices();

    // 更新某日價格
    void updatePrice(int productId, Date priceDate, PriceVO priceVO);

    // 刪除某日價格
    void deletePrice(int productId, Date priceDate);
    
    //新增價格
	void insertPrice(PriceVO price);
}