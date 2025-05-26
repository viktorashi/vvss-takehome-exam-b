package com.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentProcessorTest {

    @Test
    void testProcessPayment_FirstOrderWithCreditCard() {
        PaymentProcessor processor = new PaymentProcessor();
        double result = processor.processPayment(100.0, true, PaymentProcessor.PaymentMethod.CREDIT_CARD);
        assertEquals(85.5, result, 0.01); // Expected value after 10% + 5% discount
    }

    @Test
    void testProcessPayment_FirstOrderWithPayPal() {
        PaymentProcessor processor = new PaymentProcessor();
        double result = processor.processPayment(100.0, true, PaymentProcessor.PaymentMethod.PAYPAL);
        assertEquals(88.2, result, 0.01); // Expected value after 10% + 2% discount
    }

    @Test
    void testProcessPayment_FirstOrderWithCash() {
        PaymentProcessor processor = new PaymentProcessor();
        double result = processor.processPayment(100.0, true, PaymentProcessor.PaymentMethod.CASH);
        assertEquals(90.0, result, 0.01); // Expected value after 10% discount
    }

    @Test
    void testProcessPayment_NonFirstOrderWithCreditCard() {
        PaymentProcessor processor = new PaymentProcessor();
        double result = processor.processPayment(100.0, false, PaymentProcessor.PaymentMethod.CREDIT_CARD);
        assertEquals(95.0, result, 0.01); // Expected value after 5% discount
    }

    @Test
    void testProcessPayment_InvalidAmount() {
        PaymentProcessor processor = new PaymentProcessor();
        assertThrows(IllegalArgumentException.class, () -> processor.processPayment(-10.0, true, PaymentProcessor.PaymentMethod.CREDIT_CARD));
    }

    @Test
    void testCalculateDeliveryFee_LowAmount() {
        PaymentProcessor processor = new PaymentProcessor();
        double result = processor.calculateDeliveryFee(30.0);
        assertEquals(5.0, result, 0.01); // Delivery fee for amounts < 50
    }

    @Test
    void testCalculateDeliveryFee_HighAmount() {
        PaymentProcessor processor = new PaymentProcessor();
        double result = processor.calculateDeliveryFee(60.0);
        assertEquals(0.0, result, 0.01); // No delivery fee for amounts >= 50
    }
}