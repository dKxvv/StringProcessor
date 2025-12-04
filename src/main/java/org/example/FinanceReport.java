package org.example;

import java.util.Arrays;

    public class FinanceReport {
        private Payment[] payments;        // массив платежей
        private String author;            // ФИО составителя отчета
        private int reportDay;            // день создания отчета
        private int reportMonth;          // месяц создания отчета
        private int reportYear;           // год создания отчета

        // Основной конструктор
        public FinanceReport(Payment[] payments, String author, int reportDay, int reportMonth, int reportYear) {
            // Копируем массив чтобы внешние изменения не влияли на внутренний массив
            this.payments = (payments != null) ? Arrays.copyOf(payments, payments.length) : new Payment[0];
            this.author = author;
            this.reportDay = reportDay;
            this.reportMonth = reportMonth;
            this.reportYear = reportYear;
        }

        // Конструктор копирования (глубокое копирование)
        public FinanceReport(FinanceReport other) {
            this.author = other.author;
            this.reportDay = other.reportDay;
            this.reportMonth = other.reportMonth;
            this.reportYear = other.reportYear;

            // Глубокое копирование массива платежей
            this.payments = new Payment[other.payments.length];
            for (int i = 0; i < other.payments.length; i++) {
                Payment original = other.payments[i];
                // Создаем новый объект Payment с теми же данными
                this.payments[i] = new Payment(
                        original.getFullName(),
                        original.getDay(),
                        original.getMonth(),
                        original.getYear(),
                        original.getAmount()
                );
            }
        }

        // 1. Получение количества платежей
        public int getPaymentCount() {
            return payments.length;
        }

        // 2. Доступ к i-му платежу (на чтение)
        public Payment getPayment(int index) {
            if (index < 0 || index >= payments.length) {
                throw new IndexOutOfBoundsException("Неверный индекс платежа: " + index);
            }
            return payments[index];
        }

        // 3. Доступ к i-му платежу (на запись)
        public void setPayment(int index, Payment payment) {
            if (index < 0 || index >= payments.length) {
                throw new IndexOutOfBoundsException("Неверный индекс платежа: " + index);
            }
            payments[index] = payment;
        }

        // Геттеры для полей отчета
        public String getAuthor() {
            return author;
        }

        public int getReportDay() {
            return reportDay;
        }

        public int getReportMonth() {
            return reportMonth;
        }

        public int getReportYear() {
            return reportYear;
        }

        // 4. Метод toString в требуемом формате
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            // Шапка отчета
            sb.append(String.format("[Автор: %s, дата: %d.%d.%d, Платежи: [\n",
                    author, reportDay, reportMonth, reportYear));

            // Список платежей
            for (int i = 0; i < payments.length; i++) {
                Payment payment = payments[i];
                int rubles = payment.getAmount() / 100;
                int kopecks = payment.getAmount() % 100;

                sb.append(String.format("Плательщик: %s, дата: %d.%d.%d сумма: %d руб. %02d коп.\n",
                        payment.getFullName(),
                        payment.getDay(), payment.getMonth(), payment.getYear(),
                        rubles, kopecks));
            }

            // Закрывающая часть
            sb.append("]]");
            return sb.toString();
        }
    }
