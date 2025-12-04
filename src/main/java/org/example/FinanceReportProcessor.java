package org.example;
    public class FinanceReportProcessor {

        // 1) Получение платежей всех людей, чья фамилия начинается на указанный символ
        public static FinanceReport getPaymentsBySurname(FinanceReport report, char firstLetter) {
            if (report == null) {
                // Возвращаем пустой отчет если входной отчет null
                return new FinanceReport(new Payment[0], "", 1, 1, 2000);
            }

            // Подсчитываем сколько платежей подходят под условие
            int count = 0;
            for (int i = 0; i < report.getPaymentCount(); i++) {
                Payment payment = report.getPayment(i);
                if (isSurnameStartsWith(payment, firstLetter)) {
                    count++;
                }
            }

            // Создаем массив для подходящих платежей
            Payment[] filteredPayments = new Payment[count];
            int index = 0;

            // Заполняем массив подходящими платежами
            for (int i = 0; i < report.getPaymentCount(); i++) {
                Payment payment = report.getPayment(i);
                if (isSurnameStartsWith(payment, firstLetter)) {
                    // Создаем копию платежа чтобы избежать ссылочной зависимости
                    filteredPayments[index] = new Payment(
                            payment.getFullName(),
                            payment.getDay(),
                            payment.getMonth(),
                            payment.getYear(),
                            payment.getAmount()
                    );
                    index++;
                }
            }

            // Создаем и возвращаем новый отчет
            return new FinanceReport(filteredPayments,
                    report.getAuthor(),
                    report.getReportDay(),
                    report.getReportMonth(),
                    report.getReportYear());
        }

        // 2) Получение всех платежей, размер которых меньше заданной величины
        public static FinanceReport getPaymentsLessThan(FinanceReport report, int maxAmount) {
            if (report == null) {
                // Возвращаем пустой отчет если входной отчет null
                return new FinanceReport(new Payment[0], "", 1, 1, 2000);
            }

            // Подсчитываем сколько платежей подходят под условие
            int count = 0;
            for (int i = 0; i < report.getPaymentCount(); i++) {
                Payment payment = report.getPayment(i);
                if (payment.getAmount() < maxAmount) {
                    count++;
                }
            }

            // Создаем массив для подходящих платежей
            Payment[] filteredPayments = new Payment[count];
            int index = 0;

            // Заполняем массив подходящими платежами
            for (int i = 0; i < report.getPaymentCount(); i++) {
                Payment payment = report.getPayment(i);
                if (payment.getAmount() < maxAmount) {
                    // Создаем копию платежа чтобы избежать ссылочной зависимости
                    filteredPayments[index] = new Payment(
                            payment.getFullName(),
                            payment.getDay(),
                            payment.getMonth(),
                            payment.getYear(),
                            payment.getAmount()
                    );
                    index++;
                }
            }

            // Создаем и возвращаем новый отчет
            return new FinanceReport(filteredPayments,
                    report.getAuthor(),
                    report.getReportDay(),
                    report.getReportMonth(),
                    report.getReportYear());
        }

        // Вспомогательный метод для проверки фамилии
        private static boolean isSurnameStartsWith(Payment payment, char firstLetter) {
            if (payment == null || payment.getFullName() == null || payment.getFullName().isEmpty()) {
                return false;
            }

            String fullName = payment.getFullName().trim();
            // Предполагаем, что фамилия - первое слово в ФИО
            String[] nameParts = fullName.split("\\s+");
            if (nameParts.length == 0) {
                return false;
            }

            String surname = nameParts[0];
            return !surname.isEmpty() &&
                    Character.toLowerCase(surname.charAt(0)) == Character.toLowerCase(firstLetter);
        }
    }
