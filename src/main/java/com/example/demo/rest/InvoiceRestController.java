package com.example.demo.rest;

import com.example.demo.entity.Invoice;
import com.example.demo.entity.InvoiceLine;
import com.example.demo.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceRestController {
        InvoiceService invoiceService;
    @Autowired
    public InvoiceRestController(final InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public List<Invoice> findAll(){return invoiceService.findAll();}

    @GetMapping("/invoices/{invoiceId}")
    public Invoice getInvoice(@PathVariable int invoiceId){
        Invoice invoice=invoiceService.findById(invoiceId);
        if(invoice==null) throw new RuntimeException("Invoice with Id" + invoiceId+" does not exist.");
  return invoice;  }

        @PostMapping("/invoices")
    public Invoice addInvoice(@RequestBody Invoice invoice){
        invoiceService.save(invoice);
        return invoice;
        }

        @PutMapping("/invoices")
    public Invoice updateInvoice(@RequestBody Invoice invoice){
        invoiceService.save(invoice);
        return invoice;
    }

    @DeleteMapping("/invoices/{id}")
    public String deleteEmployee(@PathVariable int id){
        Invoice tempInvoice= invoiceService.findById(id);
        if(tempInvoice==null){
            throw new RuntimeException("Invoice with id: "+id+ "does not exist." );
        }
        invoiceService.deleteById(id);
   return "Deleted Invoice with Id:"+id ;
    }
}
