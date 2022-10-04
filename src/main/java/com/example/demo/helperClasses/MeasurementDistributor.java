package com.example.demo.helperClasses;

import java.util.List;

public interface MeasurementDistributor {
    /**
     * @return list of the quantity price periods ordered in chronological order
     */
    List<QuantityPricePeriod> distribute();

}
