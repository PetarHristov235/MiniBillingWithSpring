package com.example.demo;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
@Table(name = "price_lists")
public class PriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "price_list_id",
            nullable = false)
    private Long id;

    @OneToMany
    @JoinColumn(name = "price_list_id",insertable = false,updatable = false)
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Price> priceList;

    public PriceList(){};
}
