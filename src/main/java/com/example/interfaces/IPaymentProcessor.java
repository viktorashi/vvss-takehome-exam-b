package com.example.interfaces;

public interface IPaymentProcessor {

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
    double processPayment(double amount, boolean isFirstOrder, PaymentMethod method);

    double calculateDeliveryFee(double amount);
}
