package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FinanceReportTest {

    @Test
    public void testFinanceReportCreation() {
        Payment[] payments = {
                new Payment("Иванов Иван Иванович", 15, 5, 2023, 150000),
                new Payment("Петров Петр Петрович", 20, 6, 2023, 200000)
        };

        FinanceReport report = new FinanceReport(payments, "Сидоров Сидор Сидорович", 25, 7, 2023);

        assertEquals(2, report.getPaymentCount());
        assertEquals("Сидоров Сидор Сидорович", report.getAuthor());
        assertEquals(25, report.getReportDay());
        assertEquals(7, report.getReportMonth());
        assertEquals(2023, report.getReportYear());
    }

    @Test
    public void testGetAndSetPayment() {
        Payment[] payments = {
                new Payment("Иванов Иван", 1, 1, 2023, 100000),
                new Payment("Петров Петр", 2, 2, 2023, 200000)
        };

        FinanceReport report = new FinanceReport(payments, "Автор", 3, 3, 2023);

        // Проверяем получение платежа
        Payment payment = report.getPayment(0);
        assertEquals("Иванов Иван", payment.getFullName());
        assertEquals(100000, payment.getAmount());

        // Проверяем установку платежа
        Payment newPayment = new Payment("Сидоров Сидор", 4, 4, 2023, 300000);
        report.setPayment(1, newPayment);

        assertEquals("Сидоров Сидор", report.getPayment(1).getFullName());
        assertEquals(300000, report.getPayment(1).getAmount());
    }

    @Test
    public void testGetPaymentInvalidIndex() {
        Payment[] payments = {
                new Payment("Иванов Иван", 1, 1, 2023, 100000)
        };

        FinanceReport report = new FinanceReport(payments, "Автор", 1, 1, 2023);

        assertThrows(IndexOutOfBoundsException.class, () -> report.getPayment(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> report.getPayment(1));
    }

    @Test
    public void testSetPaymentInvalidIndex() {
        Payment[] payments = {
                new Payment("Иванов Иван", 1, 1, 2023, 100000)
        };

        FinanceReport report = new FinanceReport(payments, "Автор", 1, 1, 2023);
        Payment newPayment = new Payment("Петров Петр", 2, 2, 2023, 200000);

        assertThrows(IndexOutOfBoundsException.class, () -> report.setPayment(-1, newPayment));
        assertThrows(IndexOutOfBoundsException.class, () -> report.setPayment(1, newPayment));
    }

    @Test
    public void testToStringFormat() {
        Payment[] payments = {
                new Payment("Иванов Иван Иванович", 15, 5, 2023, 150000),
                new Payment("Петров Петр Петрович", 20, 6, 2023, 200000)
        };

        FinanceReport report = new FinanceReport(payments, "Сидоров Сидор", 25, 7, 2023);
        String result = report.toString();

        // Проверяем формат вывода
        assertTrue(result.startsWith("[Автор: Сидоров Сидор, дата: 25.7.2023, Платежи: ["));
        assertTrue(result.contains("Плательщик: Иванов Иван Иванович, дата: 15.5.2023 сумма: 1500 руб. 00 коп."));
        assertTrue(result.contains("Плательщик: Петров Петр Петрович, дата: 20.6.2023 сумма: 2000 руб. 00 коп."));
        assertTrue(result.endsWith("]]"));
    }

    @Test
    public void testCopyConstructorDeepCopy() {
        Payment[] payments = {
                new Payment("Иванов Иван", 1, 1, 2023, 100000)
        };

        FinanceReport original = new FinanceReport(payments, "Автор", 1, 1, 2023);
        FinanceReport copy = new FinanceReport(original);

        // Изменяем платеж в оригинальном отчете
        original.getPayment(0).setAmount(999999);

        // Копия не должна измениться
        assertEquals(100000, copy.getPayment(0).getAmount());

        // Изменяем автора в копии
        original.setPayment(0, new Payment("Петров Петр", 2, 2, 2023, 200000));

        // Оригинал изменился, копия осталась прежней
        assertEquals("Иванов Иван", copy.getPayment(0).getFullName());
        assertEquals("Петров Петр", original.getPayment(0).getFullName());
    }

    @Test
    public void testGetPaymentsBySurname() {
        Payment[] payments = {
                new Payment("Иванов Иван", 1, 1, 2023, 100000),
                new Payment("Петров Петр", 2, 2, 2023, 200000),
                new Payment("Иванова Анна", 3, 3, 2023, 150000),
                new Payment("Сидоров Сидор", 4, 4, 2023, 120000)
        };

        FinanceReport report = new FinanceReport(payments, "Отчет", 5, 5, 2023);

        // Фильтруем по фамилии на 'И'
        FinanceReport filteredReport = FinanceReportProcessor.getPaymentsBySurname(report, 'И');

        assertEquals(2, filteredReport.getPaymentCount());
        assertEquals("Иванов Иван", filteredReport.getPayment(0).getFullName());
        assertEquals("Иванова Анна", filteredReport.getPayment(1).getFullName());

        // Фильтруем по фамилии на 'П'
        FinanceReport filteredReport2 = FinanceReportProcessor.getPaymentsBySurname(report, 'П');

        assertEquals(1, filteredReport2.getPaymentCount());
        assertEquals("Петров Петр", filteredReport2.getPayment(0).getFullName());
    }

    @Test
    public void testGetPaymentsLessThan() {
        Payment[] payments = {
                new Payment("Иванов Иван", 1, 1, 2023, 100000),   // 1000 руб
                new Payment("Петров Петр", 2, 2, 2023, 200000),   // 2000 руб
                new Payment("Сидоров Сидор", 3, 3, 2023, 150000), // 1500 руб
                new Payment("Кузнецов Алексей", 4, 4, 2023, 80000) // 800 руб
        };

        FinanceReport report = new FinanceReport(payments, "Отчет", 5, 5, 2023);

        // Фильтруем платежи меньше 1500 руб. (150000 копеек)
        FinanceReport filteredReport = FinanceReportProcessor.getPaymentsLessThan(report, 150000);

        assertEquals(2, filteredReport.getPaymentCount());
        assertEquals("Иванов Иван", filteredReport.getPayment(0).getFullName());
        assertEquals("Кузнецов Алексей", filteredReport.getPayment(1).getFullName());

        // Фильтруем платежи меньше 2000 руб. (200000 копеек)
        FinanceReport filteredReport2 = FinanceReportProcessor.getPaymentsLessThan(report, 200000);

        assertEquals(3, filteredReport2.getPaymentCount());
    }

    @Test
    public void testFinanceReportProcessorWithNullReport() {
        // Проверяем обработку null отчета
        FinanceReport filteredReport = FinanceReportProcessor.getPaymentsBySurname(null, 'И');

        assertNotNull(filteredReport);
        assertEquals(0, filteredReport.getPaymentCount());

        FinanceReport filteredReport2 = FinanceReportProcessor.getPaymentsLessThan(null, 100000);

        assertNotNull(filteredReport2);
        assertEquals(0, filteredReport2.getPaymentCount());
    }

    @Test
    public void testEmptyPaymentsArray() {
        // Проверяем работу с пустым массивом платежей
        FinanceReport report = new FinanceReport(new Payment[0], "Автор", 1, 1, 2023);

        assertEquals(0, report.getPaymentCount());
        assertEquals("Автор", report.getAuthor());

        // Проверяем обработку пустого отчета в процессоре
        FinanceReport filtered = FinanceReportProcessor.getPaymentsBySurname(report, 'И');
        assertEquals(0, filtered.getPaymentCount());
    }
}