package com.practice.model;

import com.practice.enums.PaymentStatus;
import com.practice.strategy.pricing.PricingStrategy;

import java.time.LocalDateTime;

public class Ticket {

    private final Vehicle vehicle;

    private final ParkingSlot slot;

    private final LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private final PricingStrategy pricingStrategy;

    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    public Ticket(Vehicle vehicle, ParkingSlot slot, LocalDateTime entryTime, PricingStrategy pricingStrategy) {
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = entryTime;
        this.pricingStrategy = pricingStrategy;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
