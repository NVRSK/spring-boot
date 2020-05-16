package com.nvrsk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Stock {

    @Id
    @SequenceGenerator( name = "jpaSequence", sequenceName = "JPA_SEQUENCE", allocationSize = 1, initialValue = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "jpaSequence")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    private String name;

    private double currentPrice;

    private Instant lastUpdate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stock")
    @OrderBy("startDate DESC")
    @JsonIgnore
    private List<PriceHistory> history = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    public List<PriceHistory> getHistory() {
        return history;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setLastUpdate(Instant lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setHistory(List<PriceHistory> history) {
        this.history = history;
    }


    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currentPrice=" + currentPrice +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}