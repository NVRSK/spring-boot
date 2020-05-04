package com.nvrsk.service;

import com.nvrsk.exception.StockNotFoundException;
import com.nvrsk.model.Stock;
import com.nvrsk.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Service to perform business logic on {@link Stock} entities.
 */
@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;


    /**
     * Returns list of all {@link Stock}s.
     *
     * @return list of all {@link Stock}s.
     */
    @Transactional
    @NonNull
    public Collection<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    /**
     * Returns {@link Stock} by its id.
     *
     * @param id - id of stock to lookup.
     * @return {@link Stock} by its id.
     * @throws StockNotFoundException when there is no stock with such id.
     */
    @Transactional
    @NonNull
    public Stock lookupStock(long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new StockNotFoundException("Stock with id " + id + " not found"));
    }
}

