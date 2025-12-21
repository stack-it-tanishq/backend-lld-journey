package com.practice.strategy.pricing;

import com.practice.enums.VehicleCategory;

import java.time.LocalDateTime;

public interface PricingStrategy {

    double calculateFee(LocalDateTime entry, LocalDateTime exit, VehicleCategory vehicleCategory);

}
