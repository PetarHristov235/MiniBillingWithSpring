package com.example.demo.service;

import com.example.demo.dao.InvoiceRepository;
import com.example.demo.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InvoiceServiceImpl implements InvoiceService {

    InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(final InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice findById(final int id) {
        final Optional<Invoice> result = invoiceRepository.findById(id);
        final Invoice invoice;
        if (result.isPresent()) {
            invoice = result.get();
        } else {
            throw new RuntimeException("Invoice with id: " + id + "does not exist");
        }
        return invoice;
    }

    @Override
    public void save(final Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    public void deleteById(final int id) {
        invoiceRepository.deleteById(id);
    }
}
