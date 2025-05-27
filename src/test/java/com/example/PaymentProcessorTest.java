package com.example;

import com.example.interfaces.IPaymentProcessor;
import com.example.interfaces.PaymentMethod;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentProcessorTest {

    static Stream<IPaymentProcessor> paymentProcessorProvider() {
        return Stream.of(
                new com.example.versionA.PaymentProcessor(),
                new com.example.versionB.PaymentProcessor()
        );
    }

    @ParameterizedTest
    @MethodSource("paymentProcessorProvider")
    void testProcessPayment_FirstOrderWithCreditCard(IPaymentProcessor processor) {
        double result = processor.processPayment(100.0, true, PaymentMethod.CREDIT_CARD);
        assertEquals(97.75, result, 0.01); // Expected value after 10% + 5% discount
    }

    @ParameterizedTest
    @MethodSource("paymentProcessorProvider")
    void testProcessPayment_FirstOrderWithPayPal(IPaymentProcessor processor) {
        double result = processor.processPayment(100.0, true, PaymentMethod.PAYPAL);
        assertEquals(101.2, result, 0.01); // Expected value after 10% + 2% discount
    }

    @ParameterizedTest
    @MethodSource("paymentProcessorProvider")
    void testProcessPayment_FirstOrderWithCash(IPaymentProcessor processor) {
        double result = processor.processPayment(100.0, true, PaymentMethod.CASH);
        assertEquals(103.5, result, 0.01); // Expected value after 10% discount
    }

    @ParameterizedTest
    @MethodSource("paymentProcessorProvider")
    void testProcessPayment_NonFirstOrderWithCreditCard(IPaymentProcessor processor) {
        double result = processor.processPayment(100.0, false, PaymentMethod.CREDIT_CARD);
        assertEquals(109.25, result, 0.01); // Expected value after 5% discount
    }

    @ParameterizedTest
    @MethodSource("paymentProcessorProvider")
    void testProcessPayment_InvalidAmount(IPaymentProcessor processor) {
        assertThrows(IllegalArgumentException.class, () -> processor.processPayment(-10.0, true, PaymentMethod.CREDIT_CARD));
    }

    @ParameterizedTest
    @MethodSource("paymentProcessorProvider")
    void testCalculateDeliveryFee_LowAmount(IPaymentProcessor processor) {
        double result = processor.calculateDeliveryFee(30.0);
        assertEquals(5.0, result, 0.01); // Delivery fee for amounts < 50
    }

    @ParameterizedTest
    @MethodSource("paymentProcessorProvider")
    void testCalculateDeliveryFee_HighAmount(IPaymentProcessor processor) {
        double result = processor.calculateDeliveryFee(60.0);
        assertEquals(0.0, result, 0.01); // No delivery fee for amounts >= 50
    }
}