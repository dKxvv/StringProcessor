package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class PaymentTest {
    @Test
    public void testPaymentCreation() {
        Payment payment = new Payment("Иванов Иван Иванович", 15, 5, 2023, 150000);

        assertEquals("Иванов Иван Иванович", payment.getFullName());
        assertEquals(15, payment.getDay());
        assertEquals(5, payment.getMonth());
        assertEquals(2023, payment.getYear());
        assertEquals(150000, payment.getAmount());
    }
    @Test
    public void testPaymentSetters() {
        Payment payment = new Payment("", 0, 0, 0, 0);
        payment.setFullName("Петров Петр");
        payment.setDay(10);
        payment.setMonth(12);
        payment.setYear(2024);
        payment.setAmount(75000);

        assertEquals("Петров Петр", payment.getFullName());
        assertEquals(10, payment.getDay());
        assertEquals(12, payment.getMonth());
        assertEquals(2024, payment.getYear());
        assertEquals(75000, payment.getAmount());
    }

    @Test
    public void testPaymentEqualsAndHashCode() {
        Payment payment1 = new Payment("Иванов Иван", 15, 5, 2023, 150000);
        Payment payment2 = new Payment("Иванов Иван", 15, 5, 2023, 150000);
        Payment payment3 = new Payment("Петров Петр", 15, 5, 2023, 150000);

        assertEquals(payment1, payment2);
        assertEquals(payment1.hashCode(), payment2.hashCode());
        assertNotEquals(payment1, payment3);
    }

    @Test
    public void testPaymentToString() {
        Payment payment = new Payment("Иванов Иван", 15, 5, 2023, 150000);
        String expected = "Плательщик: Иванов Иван, дата: 15.5.2023 сумма: 1500 руб. 00 коп.";
        assertEquals(expected, payment.toString());
    }
}