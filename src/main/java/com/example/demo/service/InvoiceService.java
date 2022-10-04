package com.example.demo.service;

import com.example.demo.entity.Invoice;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface InvoiceService {
    public List<Invoice> findAll();
    public  Invoice findById(int id);
    public void save(Invoice invoice);
    public void deleteById(int id);
}
