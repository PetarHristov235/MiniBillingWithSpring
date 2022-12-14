package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "readings")
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",
            nullable = false)
    private int id;

    @Column(name = "ref_number")
    String refNumber;

    @Column(name = "product")
    String product;

    @Column(name = "date")
    ZonedDateTime date;

    @Column(name = "value")
    BigDecimal value;

    public Reading() {
    }

    public Reading(final String refNumber, final String product, final ZonedDateTime date, final BigDecimal value) {
        this.refNumber = refNumber;
        this.product = product;
        this.date = date;
        this.value = value;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public String getProduct() {
        return product;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public BigDecimal getValue() {
        return value;
    }
}
