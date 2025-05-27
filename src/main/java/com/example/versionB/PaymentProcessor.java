package com.example.versionB;
import com.example.interfaces.IPaymentProcessor;
import com.example.interfaces.PaymentMethod;

public class PaymentProcessor implements IPaymentProcessor {

    public double processPayment(double amount, boolean isFirstOrder, PaymentMethod method) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        double discount = 0.0;

        if (isFirstOrder) {
            discount += 0.1; // 10% discount for first order
        }

        switch (method) {
            case CREDIT_CARD:
                discount += 0.05;
                break;
            case PAYPAL:
                discount += 0.02;
                break;
        }

        double amountAfterDiscount = amount * (1 - discount);
        final double tax = amountAfterDiscount * 0.15;  // 15%, dupa discounturi aparent
        double finalAmount = amountAfterDiscount + tax;

//        creca ia round pana la 2 zecimale
        return Math.round(finalAmount * 100.0) / 100.0;
    }

    public double calculateDeliveryFee(double amount) {
        return amount < 50.0 ? 5.0 : 0.0;
    }
}