package com.example.demo;

import javax.persistence.CascadeType;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ref_number")
    private String refNumber;

    @Column(name = "name")
    String name;

    @OneToMany(fetch = FetchType.EAGER)
            @JoinColumn(name = "ref_number",insertable = false,updatable = false)
    List<Reading>readingList;
    @ManyToOne
            @JoinColumn(name = "price_list_id")

    PriceList priceList;

    @Column(name = "currencies")
    String currency;

    public User(){}

    public User(String refNumber, String name, PriceList priceList, String currency) {
        this.refNumber = refNumber;
        this.name = name;
        this.priceList=priceList;
        this.currency = currency;
    }
}
