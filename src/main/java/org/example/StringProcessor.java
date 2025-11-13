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

    // 2. Подсчет вхождений подстроки
    public static int countOccurrences(String mainString, String subString) {
        if (subString == null || subString.isEmpty()) {
            throw new IllegalArgumentException("Substring cannot be null or empty");
        }
        if (mainString == null || mainString.isEmpty()) {
            return 0;
        }

        int count = 0;
        int index = 0;
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
        if (sb == null) {
            return;
        }

        for (int i = sb.length() - 2; i >= 0; i -= 2) {
            sb.deleteCharAt(i);
        }
    }

    // 5. Обратный порядок слов с сохранением пробелов
    public static String reverseWords(String input) {
        if (input == null) {
            return null;
        }

        // Разделяем на слова и пробелы
        String[] words = input.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        // Восстанавливаем оригинальные пробелы в начале
        int leadingSpaces = 0;
        while (leadingSpaces < input.length() && input.charAt(leadingSpaces) == ' ') {
            result.append(' ');
            leadingSpaces++;
        }

        // Добавляем слова в обратном порядке
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i > 0) {
                // Восстанавливаем пробелы между словами
                result.append(' ');
            }
        }

        // Восстанавливаем пробелы в конце
        int trailingSpaces = input.length() - 1;
        while (trailingSpaces >= 0 && input.charAt(trailingSpaces) == ' ') {
            result.append(' ');
            trailingSpaces--;
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