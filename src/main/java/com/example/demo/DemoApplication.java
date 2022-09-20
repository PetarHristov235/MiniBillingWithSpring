package com.example.demo;

import com.example.demo.entity.Invoice;
import com.example.demo.entity.Reading;
import com.example.demo.entity.User;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@SpringBootApplication

public class DemoApplication {

    public static void main(String[] args) {


        String dateReportingTo = "21-03";
        LocalDateTime dateReportingToLDT = convertingBorderTimeIntoLDT(dateReportingTo);

        SpringApplication.run(DemoApplication.class, args);
        ConfigurableApplicationContext configurableApplicationContextUser = SpringApplication.run(DemoApplication.class,
                args);
        UserRepository userRepository = configurableApplicationContextUser.getBean(UserRepository.class);
        List<User> userList = userRepository.findAll();

        MeasurementGenerator measurementGenerator = new MeasurementGenerator();
        CurrencyConverter currencyConverter = new CurrencyRate();
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(currencyConverter);

        List<VatPercentages> vatPercentages = new ArrayList<>();
        vatPercentages.add(new VatPercentages(new BigDecimal("60"), new BigDecimal("20")));
        vatPercentages.add(new VatPercentages(new BigDecimal("40"), new BigDecimal("10")));

        InvoiceRepository invoiceRepository = configurableApplicationContextUser.getBean(InvoiceRepository.class);
        for (User user : userList) {
            List<Reading> userReadings = user.getReadingList();
            Collection<Measurement> userMeasurements = measurementGenerator.generate(user, userReadings);
            Invoice invoice = invoiceGenerator.generate(user, userMeasurements, dateReportingToLDT,
                    vatPercentages);
            invoiceRepository.saveAndFlush(invoice);
        }

    }



    private static LocalDateTime convertingBorderTimeIntoLDT(String borderDateString) {
        final YearMonth yearMonth = YearMonth.parse(borderDateString, DateTimeFormatter.ofPattern("yy-MM"));
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        ZonedDateTime borderTimeZDT = yearMonth.atEndOfMonth().atTime(23, 59, 59).atZone(ZoneId.of("Z"));
        return LocalDateTime.parse(String.valueOf(borderTimeZDT), formatter);
    }

}




