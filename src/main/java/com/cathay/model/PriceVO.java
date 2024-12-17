package com.cathay.model;

import java.math.BigDecimal;
import java.sql.Date;

public class PriceVO {
    private int product_id;
    private Date price_date;
    private BigDecimal product_price;

    // Getter 和 Setter for product_id
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    // Getter 和 Setter for price_date
    public Date getPrice_date() {
        return price_date;
    }

    public void setPrice_date(Date price_date) {
        this.price_date = price_date;
    }


    public BigDecimal getProduct_price() {
        return product_price;
    }

    public void setProduct_price(BigDecimal product_price) {
        this.product_price = product_price;
    }
}