package com.practice.strategy.payment;

public interface PaymentStrategy {

    void makePayment(double amount, String referenceId);

}
