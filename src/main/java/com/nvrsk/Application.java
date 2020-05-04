package com.nvrsk;

import com.nvrsk.model.Stock;
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

    @PostConstruct
    void prepareStocks() {
        Instant stock1Update1 = LocalDateTime.of(2020, Month.MAY, 1, 22, 56, 4).toInstant(ZoneOffset.UTC);

        Stock stock1 = new Stock();
        stock1.setName("Stock1.L");
        stock1.setCurrentPrice(2d);
        stock1.setLastUpdate(stock1Update1);
        stockRepository.saveAndFlush(stock1);


        Instant stock2Update1 = LocalDateTime.of(2020, Month.MAY, 2, 21, 8, 47).toInstant(ZoneOffset.UTC);

        Stock stock2 = new Stock();
        stock2.setName("Stock2.N");
        stock2.setCurrentPrice(1.92);
        stock2.setLastUpdate(stock2Update1);
        stockRepository.saveAndFlush(stock2);

    }
}
