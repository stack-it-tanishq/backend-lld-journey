package com.practice.strategy.pricing;

import com.practice.enums.VehicleCategory;

import java.time.LocalDateTime;

public class WeekendPricing implements PricingStrategy{

    @Override
    public double calculateFee(LocalDateTime entry, LocalDateTime exit, VehicleCategory vehicleCategory) {
        return 0;
    }
}
