package com.nvrsk.service;

import com.nvrsk.exception.IncorrectRequestException;
import com.nvrsk.exception.StockAlreadyExistsException;
import com.nvrsk.exception.StockNotFoundException;
import com.nvrsk.model.Stock;
import com.nvrsk.repository.StockRepository;
import com.nvrsk.request.NewStockRequest;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

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

    /**
     * Add a new stock by request.
     *
     * @param newStockRequest - {@link NewStockRequest} of new stock to add.
     * @return added stock.
     * @throws IncorrectRequestException when price is 0 or below or stock name is empty.
     * @throws StockAlreadyExistsException when stock with such name already exists.
     */
    @Transactional
    @NonNull
    public Stock addNewStock(@NonNull NewStockRequest newStockRequest) {
        Stock newStock = new Stock();
        newStock.setCurrentPrice(validatePrice(newStockRequest.getPrice()));
        newStock.setName(validateName(newStockRequest.getName()));
        return saveStock(newStock);
    }

    /**
     * Saves a {@link Stock}.
     *
     * @param stock - {@link Stock} to save.
     * @return saved Stock.
     */
    @Transactional
    @NonNull
    private Stock saveStock(@NonNull Stock stock) {
        Instant lastUpdate = Instant.now();
        stock.setLastUpdate(lastUpdate);
        Stock savedStock = stockRepository.save(stock);
        return savedStock;
    }

    /**
     * Validates that price is greater than 0.
     *
     * @param price - price to validate.
     * @return input price.
     * @throws IncorrectRequestException when price is 0 or less.
     */
    private static double validatePrice(@Nullable Double price) {
        if (price == null || price <= 0) {
            throw new IncorrectRequestException("Stock price should be greater than 0");
        }
        return price;
    }

    /**
     * Validates that name is:
     * - not null
     * - not empty
     * - unique among all Stocks.
     *
     * @param name - name to validate.
     * @return input name without leading and trailing whitespaces.
     */
    @NonNull
    private String validateName(@Nullable String name) {
        if (name == null || name.isEmpty() || name.trim().isEmpty()) {
            throw new IncorrectRequestException("Stock name can't be empty");
        }
        String cleanedName = name.trim();
        Optional<Stock> actualStock = stockRepository.findByName(cleanedName);
        if (actualStock.isPresent()) {
            throw new StockAlreadyExistsException("Stock already exists with name: " + cleanedName);
        }
        return cleanedName;
    }
}

