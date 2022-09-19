package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name="readings")
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

    @Column (name = "value")
    BigDecimal value;

    public Reading(){}
    public Reading(String refNumber, String product, ZonedDateTime date, BigDecimal value) {
        this.refNumber = refNumber;
        this.product = product;
        this.date = date;
        this.value = value;
    }
}
