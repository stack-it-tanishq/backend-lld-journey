package com.practice.model;

import com.practice.enums.PaymentStatus;
import com.practice.strategy.pricing.PricingStrategy;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {

    private final UUID ticketId;

    private final Vehicle vehicle;

    private final ParkingSlot slot;

    private final LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    public UUID getTicketId() {
        return ticketId;
    }

    public Ticket(UUID ticketId, Vehicle vehicle, ParkingSlot slot, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = entryTime;
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

    public void close() { //close
        this.exitTime = LocalDateTime.now();
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
