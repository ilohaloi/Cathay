package com.cathay.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cathay.model.PriceVO;
import com.cathay.service.CathayAPIService;

import java.util.List;

@RestController
@RequestMapping("/api/fund")
public class CathayFundController {

    @Autowired
    private CathayAPIService cathayAPIService;

    @PostMapping("/fetch")
    public ResponseEntity<?> fetchAndSavePrices() {
        try {
            List<PriceVO> prices = cathayAPIService.fetchAndSavePrices();
            if (prices.isEmpty()) {
                return ResponseEntity.status(404).body("未能獲取到任何價格資料");
            }
            return ResponseEntity.ok(prices);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("發生錯誤：" + e.getMessage());
        }
    }

    @GetMapping("/prices")
    public ResponseEntity<?> getPrices() {
        try {
            List<PriceVO> prices = cathayAPIService.getPrices();
            if (prices.isEmpty()) {
                return ResponseEntity.status(404).body("目前沒有價格資料");
            }
            return ResponseEntity.ok(prices);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("查詢價格資料時發生錯誤：" + e.getMessage());
        }
    }
}