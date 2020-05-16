package com.nvrsk;

import com.nvrsk.model.PriceHistory;
import com.nvrsk.model.Stock;
import com.nvrsk.repository.PriceHistoryRepository;
import com.nvrsk.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private PriceHistoryRepository priceHistoryRepository;

    @PostConstruct
    void prepareStocks() {
        Instant stock1Update1 = LocalDateTime.of(2020, Month.MAY, 1, 21, 54, 3).toInstant(ZoneOffset.UTC);
        Instant stock1Update2 = LocalDateTime.of(2020, Month.MAY, 1, 22, 56, 4).toInstant(ZoneOffset.UTC);


        Stock stock1 = new Stock();
        stock1.setName("Stock1.L");
        stock1.setCurrentPrice(2d);
        stock1.setLastUpdate(stock1Update2);
        stockRepository.saveAndFlush(stock1);

        PriceHistory stock1History1 = new PriceHistory();
        stock1History1.setStartDate(stock1Update1);
        stock1History1.setPrice(1.99d);
        stock1History1.setStock(stock1);
        priceHistoryRepository.saveAndFlush(stock1History1);

        PriceHistory stock1History2 = new PriceHistory();
        stock1History2.setStartDate(stock1Update2);
        stock1History2.setPrice(2d);
        stock1History2.setStock(stock1);
        priceHistoryRepository.saveAndFlush(stock1History2);


        Instant stock2Update1 = LocalDateTime.of(2020, Month.MAY, 2, 21, 8, 47).toInstant(ZoneOffset.UTC);

        Stock stock2 = new Stock();
        stock2.setName("Stock2.N");
        stock2.setCurrentPrice(1.92);
        stock2.setLastUpdate(stock2Update1);
        stockRepository.saveAndFlush(stock2);

        PriceHistory stock2History1 = new PriceHistory();
        stock2History1.setStartDate(stock2Update1);
        stock2History1.setPrice(1.92);
        stock2History1.setStock(stock2);
        priceHistoryRepository.saveAndFlush(stock2History1);

    }
}
