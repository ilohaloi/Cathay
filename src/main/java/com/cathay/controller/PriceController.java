package com.cathay.controller;

import com.cathay.model.PriceVO;
import com.cathay.service.CathayAPIService;
import com.cathay.service.PriceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController // 標記此類為 RESTful API 控制器
@RequestMapping("/api/prices") // 基本路徑
public class PriceController {
	private static final Logger logger = LoggerFactory.getLogger(PriceController.class);

	
	@Autowired
    private PriceService priceService;
	
	@Autowired
	private CathayAPIService cathayApiService;

	// 1. 呼叫外部 API 並存入價格表
    @PostMapping("/fetch")
    public ResponseEntity<String> fetchAndSavePrices() {
        List<PriceVO> prices = cathayApiService.fetchAndSavePrices();
        for (PriceVO price : prices) {
            priceService.insertPrice(price);
        }
        return ResponseEntity.ok("價格資料已成功同步！");
    }
	
    // 新增某日價格
    @PostMapping("/{productId}/{priceDate}")
    public ResponseEntity<String> addPriceForDate(
            @PathVariable int productId, 
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate priceDate, 
            @RequestBody PriceVO priceVO) {
    	
    	 // 使用 Logger 輸出日誌
        logger.info("Product ID: {}", productId);
        logger.info("Price Date: {}", priceDate);
        logger.info("Product Price: {}", priceVO.getProduct_price());
        
        priceVO.setProduct_id(productId);
        priceVO.setPrice_date(java.sql.Date.valueOf(priceDate)); // 轉換 LocalDate 為 SQL Date
        priceService.insertPrice(priceVO);

        return ResponseEntity.ok("新增價格成功");
    }

    // 根據商品 ID 和日期查詢價格
    @GetMapping("/{productId}/{priceDate}")
    public ResponseEntity<PriceVO> getPriceByProductIdAndDate(
            @PathVariable int productId, 
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate priceDate) {
        
        PriceVO priceVO = priceService.getPriceByProductIdAndDate(productId, java.sql.Date.valueOf(priceDate));
        return ResponseEntity.ok(priceVO);
    }

    // 查詢所有價格記錄
    @GetMapping
    public ResponseEntity<List<PriceVO>> getAllPrices() {
        List<PriceVO> prices = priceService.getAllPrices();
        return ResponseEntity.ok(prices);
    }

    // 修改某日價格
    @PutMapping("/{productId}/{priceDate}")
    public ResponseEntity<String> updatePrice(
            @PathVariable int productId, 
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate priceDate, 
            @RequestBody PriceVO priceVO) {
        
        priceService.updatePrice(productId, java.sql.Date.valueOf(priceDate), priceVO);
        return ResponseEntity.ok("價格已更新");
    }

    // 刪除某日價格
    @DeleteMapping("/{productId}/{priceDate}")
    public ResponseEntity<String> deletePrice(
            @PathVariable int productId, 
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate priceDate) {
        
        priceService.deletePrice(productId, java.sql.Date.valueOf(priceDate));
        return ResponseEntity.ok("價格已刪除");
    }
}