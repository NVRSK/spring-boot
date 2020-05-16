package com.nvrsk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
public class PriceHistory {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Stock stock;

    private double price;

    private Instant startDate;


    public Long getId() {
        return id;
    }

    public Stock getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public Instant getStartDate() {
        return startDate;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }


    @Override
    public String toString() {
        return "PriceHistory{" +
                "id=" + id +
                ", stock=" + stock +
                ", price=" + price +
                ", startDate=" + startDate +
                '}';
    }
}
