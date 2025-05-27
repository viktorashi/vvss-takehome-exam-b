package com.example;

public class PaymentProcessor {

    public enum PaymentMethod {
        CREDIT_CARD, PAYPAL, CASH
    }

    /**
     * Processes a payment with discounts based on the order type and payment method.
     * This method applies a 10% discount for first orders,
     * 5% discount for credit card payments,
     * 2% discount for PayPal payments,
     * and no discount for cash payments.
     * It also applies a tax of 15% on the total amount after discounts.
     * @param amount
     * @param isFirstOrder
     * @param method
     * @return The final amount after applying discounts and tax.
     */
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