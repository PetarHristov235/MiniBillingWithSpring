package com.example.demo;


import com.example.demo.entity.Invoice;
import com.example.demo.entity.InvoiceLine;
import com.example.demo.entity.Tax;
import com.example.demo.entity.User;
import com.example.demo.entity.Vat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class InvoiceGenerator {

    final private CurrencyConverter currencyConverter;

    public InvoiceGenerator(CurrencyConverter currencyConverter) {
        this.currencyConverter = currencyConverter;
    }

    /**
     * Looping through MMs and Prices and comparing the end date of measurements dates with pricing dates and if
     * its needed, dividing them into different lines,also getting the data(,quantity,price,start of the line,
     * end of the line etc.
     */
    public Invoice generate(User user, Collection<Measurement> measurements, LocalDateTime dateReportingTo,
                            List<VatPercentages> vatPercentages) {
        ProportionalMeasurementDistributor proportionalMeasurementDistributor =
                new ProportionalMeasurementDistributor(measurements, user.getPriceList().getPriceList());
        Collection<QuantityPricePeriod> quantityPricePeriods = proportionalMeasurementDistributor.distribute();

        List<InvoiceLine> invoiceLines = new ArrayList<>();
        List<Tax> taxList = new ArrayList<>();
        TaxGenerator taxGenerator = new TaxGenerator();
        BigDecimal currencyValue = currencyConverter.getCurrencyValue(user.getCurrency());
        for (QuantityPricePeriod qpp : quantityPricePeriods) {
            if (dateReportingTo.compareTo(qpp.end()) >= 0) {
                int lineIndex = invoiceLines.size() + 1;
                InvoiceLine invoiceLine = createInvoiceLine(lineIndex, qpp, user, currencyValue);
                invoiceLines.add(invoiceLine);
                taxList.add(taxGenerator.generate(invoiceLine, new BigDecimal("1.6"),
                        currencyValue, taxList.size()));
            } else {
                break;
            }
        }

        Integer documentNumber = Integer.valueOf(Invoice.getDocumentNumber());
        String userName = user.getName();
        List<Vat> vat = new VatGenerator().generate(vatPercentages, invoiceLines, taxList);

        BigDecimal taxAmount = taxList.stream().map(Tax::getAmount).reduce(new BigDecimal(0), BigDecimal::add);
        BigDecimal totalAmount = invoiceLines.stream().map(InvoiceLine::getAmount).reduce(taxAmount, BigDecimal::add);
        BigDecimal totalAmountWithVat = vat.stream().map(Vat::getAmount).reduce(totalAmount, BigDecimal::add);

        return new Invoice(documentNumber, userName, user.getRefNumber(), totalAmount, totalAmountWithVat, invoiceLines,
                vat, taxList);
    }

    private InvoiceLine createInvoiceLine(int lineIndex, QuantityPricePeriod qpp, User user, BigDecimal currencyValue) {
        BigDecimal quantity = qpp.quantity();
        LocalDateTime start = qpp.start();
        LocalDateTime end = qpp.end();
        BigDecimal price = qpp.price();

        BigDecimal amount = qpp.quantity().multiply(qpp.price())
                .multiply(currencyValue)
                .setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
        // add the following method in order to set up your currency converter

        String product = qpp.product();
        return new InvoiceLine(lineIndex, quantity, start, end,
                product, price, user.getPriceList().getId(), amount);
    }


}