package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "ref_number")
    private String refNumber;

    @Column(name = "name")
    String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ref_number",
            insertable = false,
            updatable = false)
    List<Reading> readingList;
    @ManyToOne
    @JoinColumn(name = "price_list_id")

    PriceList priceList;

    @Column(name = "currency")
    String currency;

    public User() {
    }

    public User(final String refNumber, final String name, final PriceList priceList, final String currency) {
        this.refNumber = refNumber;
        this.name = name;
        this.priceList = priceList;
        this.currency = currency;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public String getName() {
        return name;
    }

    public List<Reading> getReadingList() {
        return readingList;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public String getCurrency() {
        return currency;
    }
}
