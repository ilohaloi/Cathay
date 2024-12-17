package com.cathay.service;

import com.cathay.model.PriceVO;
import com.cathay.util.RequestDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.http.impl.client.HttpClients;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CathayAPIService {

    private static final String API_URL = "https://www.cathaybk.com.tw/cathaybk/service/newwealth/fund/charts/service.asmx/GetFundNavChart";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cathay";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public List<PriceVO> fetchAndSavePrices() {
        List<PriceVO> prices = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        try {
            // 1. 建立 RequestDTO
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setKeys(new String[]{"10480016"});
            requestDTO.setFrom("2023/03/10");
            requestDTO.setTo("2024/03/10");

            // 2. 轉換為 JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = objectMapper.writeValueAsString(requestDTO);

            // 3. 設定 HTTP Header 和 Body
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // headers.set("Authorization", "Bearer YOUR_API_KEY"); // 如果需要認證

            HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

            // 4. 發送請求
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, String.class);

            // 輸出請求和回應資訊
            System.out.println("Request Body: " + jsonBody);
            System.out.println("Response Status Code: " + response.getStatusCode());
            System.out.println("Response Body: " + response.getBody());

            // 5. 解析 JSON 回應
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode dataArray = rootNode.path("Data");

            if (dataArray.isArray() && dataArray.size() > 0) {
                JsonNode dataNode = dataArray.get(0).path("data");

                if (dataNode.isArray()) {
                    for (JsonNode node : dataNode) {
                        long timestamp = node.get(0).asLong();
                        BigDecimal priceValue = node.get(1).decimalValue();

                        PriceVO price = new PriceVO();
                        price.setProduct_id(10480016);
                        price.setPrice_date(new Date(timestamp));
                        price.setProduct_price(priceValue);

                        prices.add(price);
                    }
                } else {
                    System.out.println("'data' 屬性為空或非陣列。");
                }
            } else {
                System.out.println("'Data' 屬性不存在或為空陣列。API 回應：" + rootNode.toString());
            }

            // 6. 批量寫入資料庫
            if (!prices.isEmpty()) {
                savePricesToDatabase(prices);
                System.out.println("資料已成功存入資料庫！");
            } else {
                System.out.println("無有效數據可存入資料庫。");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("發生錯誤: " + e.getMessage());
        }

        return prices;
    }

    
    private void savePricesToDatabase(List<PriceVO> prices) {
        String sql = "INSERT INTO prices (product_id, date, price) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (PriceVO price : prices) {
                pstmt.setInt(1, price.getProduct_id());
                pstmt.setDate(2, price.getPrice_date());
                pstmt.setBigDecimal(3, price.getProduct_price());
                pstmt.addBatch();
            }

            pstmt.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("資料寫入失敗：" + e.getMessage());
        }
    }
    public List<PriceVO> getPrices() {
        List<PriceVO> prices = new ArrayList<>();
        String sql = "SELECT * FROM price_info";  // 假設資料庫中有一個名為 `prices` 的表格

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                PriceVO price = new PriceVO();
                price.setProduct_id(rs.getInt("product_id"));
                price.setPrice_date(rs.getDate("date"));
                price.setProduct_price(rs.getBigDecimal("price"));
                prices.add(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("資料庫查詢錯誤：" + e.getMessage());
        }

        return prices;
    }
    
}