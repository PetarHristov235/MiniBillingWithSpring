package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "price_list_id")
    int priceListId;

    @Column(name = "product")
    String product;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "end_date")
    LocalDate endDate;

    @Column(name = "price")
    BigDecimal price;

    public Price(){}
    public Price(final int priceListId, final String product, final LocalDate startDate, final LocalDate endDate, final BigDecimal price) {
        this.priceListId = priceListId;
        this.product = product;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public int getPriceListId() {
        return priceListId;
    }

    public String getProduct() {
        return product;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
