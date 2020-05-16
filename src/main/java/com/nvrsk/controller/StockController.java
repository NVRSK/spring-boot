package com.nvrsk.controller;

import com.nvrsk.model.PriceHistory;
import com.nvrsk.model.Stock;
import com.nvrsk.request.NewStockRequest;
import com.nvrsk.request.PriceUpdateRequest;
import com.nvrsk.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

/**
 * Main Stocks REST controller
 */
@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    @NonNull
    public Collection<Stock> getStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/{id}")
    @NonNull
    public Stock getStock(@PathVariable long id) {
        return stockService.lookupStock(id);
    }

    @GetMapping("/{id}/history")
    @NonNull
    public Collection<PriceHistory> getHistory(@PathVariable long id) {
        return getStock(id).getHistory();
    }

    @PutMapping("/{id}")
    @ResponseBody
    @NonNull
    public ResponseEntity<?> updatePrice(@RequestBody @NonNull PriceUpdateRequest priceUpdateRequest, @PathVariable long id) {
        stockService.updateStockPrice(id, priceUpdateRequest.getPrice());
        return ResponseEntity.ok("Stock price updated");
    }

    @PostMapping
    @ResponseBody
    @NonNull
    public ResponseEntity<?> addStock(@RequestBody @NonNull NewStockRequest newStockRequest) {
        Stock newStock = stockService.addNewStock(newStockRequest);
        URI newStockLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newStock.getId()).toUri();
        return ResponseEntity.created(newStockLocation).body("New stock was created");
    }

}
