package com.practice.strategy.pricing;

import com.practice.enums.VehicleCategory;

import java.time.LocalDateTime;

public class WeekdayPricing implements PricingStrategy{

    @Override
    public double calculateFee(LocalDateTime entry, LocalDateTime exit, VehicleCategory vehicleCategory) {
        return 0;
    }
}
