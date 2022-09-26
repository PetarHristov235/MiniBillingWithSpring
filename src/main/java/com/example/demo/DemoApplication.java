package com.example.demo;

import com.example.demo.entity.Invoice;
import com.example.demo.entity.Reading;
import com.example.demo.entity.User;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
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

    private static MeasurementGenerator mmGenerator;
    private static CurrencyRate currencyRate;
   private static InvoiceGenerator invoiceGenerator;

    public DemoApplication(final MeasurementGenerator mmGenerator, final CurrencyRate currencyRate,
                           final InvoiceGenerator invoiceGenerator) {
        DemoApplication.mmGenerator = mmGenerator;
        DemoApplication.currencyRate = currencyRate;
        DemoApplication.invoiceGenerator = invoiceGenerator;
    }


    public static void main(final String[] args) {


        final String dateReportingTo = "21-03";
        final LocalDateTime dateReportingToLDT = convertingBorderTimeIntoLDT(dateReportingTo);

//       SpringApplication.run(DemoApplication.class, args);
        final ConfigurableApplicationContext configurableAppContextUser = SpringApplication.run(DemoApplication.class,
                args);
        final UserRepository userRepository = configurableAppContextUser.getBean(UserRepository.class);
        final List<User> userList = userRepository.findAll();

        final MeasurementGenerator measurementGenerator =mmGenerator;
        final CurrencyConverter currencyConverter = currencyRate;

        final List<VatPercentages> vatPercentages = new ArrayList<>();
        vatPercentages.add(new VatPercentages(new BigDecimal("60"), new BigDecimal("20")));
        vatPercentages.add(new VatPercentages(new BigDecimal("40"), new BigDecimal("10")));

        final InvoiceRepository invoiceRepository = configurableAppContextUser.getBean(InvoiceRepository.class);

        userList.forEach(user -> {
            final List<Reading> userReadings = user.getReadingList();
            final Collection<Measurement> userMeasurements = measurementGenerator.generate(user, userReadings);
            final Invoice invoice = invoiceGenerator.generate(user, userMeasurements, dateReportingToLDT,
                    vatPercentages, currencyConverter);
            invoiceRepository.saveAndFlush(invoice);
        });

        //        for (User user : userList) {
        //            List<Reading> userReadings = user.getReadingList();
        //            Collection<Measurement> userMeasurements = measurementGenerator.generate(user, userReadings);
        //            Invoice invoice = invoiceGenerator.generate(user, userMeasurements, dateReportingToLDT,
        //                    vatPercentages);
        //            invoiceRepository.saveAndFlush(invoice);
        //        }
//        for (final String s:apc.getBeanDefinitionNames()) {
//            System.out.println(s);
//        }
    }



    private static LocalDateTime convertingBorderTimeIntoLDT(final String borderDateString) {
        final YearMonth yearMonth = YearMonth.parse(borderDateString, DateTimeFormatter.ofPattern("yy-MM"));
        final DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        final ZonedDateTime borderTimeZDT = yearMonth.atEndOfMonth().atTime(23, 59, 59).atZone(ZoneId.of("Z"));
        return LocalDateTime.parse(String.valueOf(borderTimeZDT), formatter);
    }

}




