package org.example;

public class StringProcessor {

    // 1. Копирование строки N раз
    public static String copyString(String s, int N) {
        if (N < 0) {
            throw new IllegalArgumentException("N must be non-negative");
        }
        if (N == 0 || s == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            result.append(s);
        }
        return result.toString();
    }
    // 2. количество вхождений второй строки
    public static int countOccurrences(String mainString, String subString) {

        if (subString == null || subString.isEmpty()) {
            throw new IllegalArgumentException("Substring cannot be null or empty");
        }

        // Если основная строка null или пустая
        if (mainString == null || mainString.isEmpty()) {
            return 0;
        }

        int count = 0;
        int index = 0;

        // Ищем все вхождения подстроки
        while ((index = mainString.indexOf(subString, index)) != -1) {
            count++;
            index += subString.length();
        }

        return count;
    }
    // 3. Замена цифр на слова
    public static String replaceNumbers(String input) {
        if (input == null) {
            return null;
        }

        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '1':
                    result.append("один");
                    break;
                case '2':
                    result.append("два");
                    break;
                case '3':
                    result.append("три");
                    break;
                default:
                    result.append(c);
            }
        }
        return result.toString();
    }

    // 4. Удаление каждого второго символа в StringBuilder
    public static void removeEvenChars(StringBuilder sb) {
        if (sb == null || sb.length() <= 1) {
            return;
        }

        // Удаляем символы с нечетными индексами (1, 3, 5...)
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (i % 2 == 1) { // Индексы 1, 3, 5... (каждый второй)
                sb.deleteCharAt(i);
            }
        }
    }

    // 5. Обратный порядок слов с сохранением пробелов
    public static String reverseWords(String input) {
        if (input == null) {
            return null;
        }

        if (input.trim().isEmpty()) {
            return input;
        }

        // Разделяем на слова и пробелы более аккуратно
        String[] words = input.split("\\s+");

        // Находим ВСЕ пробельные последовательности (включая начальные и конечные)
        String[] spaceParts = input.split("\\S+");

        StringBuilder result = new StringBuilder();

        // Восстанавливаем начальные пробелы
        if (spaceParts.length > 0) {
            result.append(spaceParts[0]);
        }

        // Добавляем слова в обратном порядке
        for (int i = words.length - 1; i >= 0; i--) {
            if (!words[i].isEmpty()) {
                result.append(words[i]);

                // Добавляем пробелы между словами
                if (i > 0) {
                    // Индекс пробела в массиве spaceParts
                    int spaceIndex = words.length - i;
                    if (spaceIndex < spaceParts.length) {
                        result.append(spaceParts[spaceIndex]);
                    } else {
                        // Fallback - один пробел
                        result.append(" ");
                    }
                }
            }
        }

        // Восстанавливаем конечные пробелы
        if (spaceParts.length > words.length) {
            result.append(spaceParts[words.length]);
        }

        return result.toString();
    }
    // 6. Замена шестнадцатеричных чисел на десятичные
    public static String replaceHexNumbers(String input) {
        if (input == null) {
            return null;
        }

        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            if (i + 8 < input.length() &&
                    input.charAt(i) == '0' &&
                    input.charAt(i + 1) == 'x' &&
                    isHexSequence(input.substring(i + 2, i + 10))) {

                String hexStr = input.substring(i + 2, i + 10);
                long decimalValue = Long.parseLong(hexStr, 16);
                result.append(decimalValue);
                i += 10;
            } else {
                result.append(input.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    private static boolean isHexSequence(String str) {
        if (str.length() != 8) return false;
        for (char c : str.toCharArray()) {
            if (!((c >= '0' && c <= '9') ||
                    (c >= 'A' && c <= 'F') ||
                    (c >= 'a' && c <= 'f'))) {
                return false;
            }
        }
        return true;
    }
}