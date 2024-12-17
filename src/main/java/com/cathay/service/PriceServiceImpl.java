package com.cathay.service;

import com.cathay.dao.PriceDAO;
import com.cathay.model.PriceVO;
import com.cathay.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceDAO priceDAO;
    
    @Override
    public void insertPriceForDate(int productId, Date priceDate, PriceVO priceVO) {
        priceDAO.insertPriceForDate(productId, priceDate, priceVO);
    }

    @Override
    public PriceVO getPriceByProductIdAndDate(int productId, Date priceDate) {
        return priceDAO.findPriceByProductIdAndDate(productId, priceDate);
    }

    @Override
    public List<PriceVO> getAllPrices() {
        return priceDAO.getAllPrices();
    }

    @Override
    public void updatePrice(int productId, Date priceDate, PriceVO priceVO) {
        priceDAO.updatePriceForDate(productId, priceDate, priceVO);
    }

    @Override
    public void deletePrice(int productId, Date priceDate) {
        priceDAO.deletePriceForDate(productId, priceDate);
    }

	@Override
	public void insertPrice(PriceVO price) {
		// TODO Auto-generated method stub
		
	}



}