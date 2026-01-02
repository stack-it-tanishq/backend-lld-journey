package com.practice.model;

import java.util.List;

public class Booking {

    private final int bookingId;
    private final Show show;
    private final List<Integer> seatIds;
    private PaymentStatus paymentStatus;

    public Booking(int bookingId, Show show, List<Integer> seatIds) {
        this.bookingId = bookingId;
        this.show = show;
        this.seatIds = seatIds;
        paymentStatus = PaymentStatus.PENDING_PAYMENT;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Show getShow() {
        return show;
    }

    public List<Integer> getSeatIds() {
        return seatIds;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void confirmBooking(){
        this.paymentStatus = PaymentStatus.CONFIRMED_PAYMENT;
    }

    public void cancelBooking(){
        this.paymentStatus = PaymentStatus.CANCELED;
    }


}
