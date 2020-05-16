package com.nvrsk.service;

import com.nvrsk.model.PriceHistory;
import com.nvrsk.model.Stock;
import com.nvrsk.repository.PriceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

/**
 * Service to perform business logic for {@link PriceHistory} entities.
 */
@Service
public class PriceHistoryService {

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;

    /**
     * Updates price history for specified {@link Stock}.
     * Opens new price history record
     *
     * @param stock      - stock to update price for.
     * @param lastUpdate - timestamp which was taken for {@link Stock#getLastUpdate()}.
     */
    @Transactional
    public void updateStockPrice(@NonNull Stock stock, @NonNull Instant lastUpdate) {
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setPrice(stock.getCurrentPrice());
        priceHistory.setStock(stock);
        priceHistory.setStartDate(lastUpdate);
        priceHistoryRepository.saveAndFlush(priceHistory);
    }
}
