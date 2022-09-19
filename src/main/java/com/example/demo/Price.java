package com.example.demo;

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
    public Price(int priceListId, String product, LocalDate startDate, LocalDate endDate, BigDecimal price) {
        this.priceListId = priceListId;
        this.product = product;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }
}
