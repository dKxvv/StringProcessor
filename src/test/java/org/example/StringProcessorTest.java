package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringProcessorTest {

    @Test
    public void testCopyString() {
        assertEquals("aaa", StringProcessor.copyString("a", 3));
        assertEquals("", StringProcessor.copyString("a", 0));
        assertEquals("", StringProcessor.copyString(null, 5));

        assertThrows(IllegalArgumentException.class, () -> {
            StringProcessor.copyString("a", -1);
        });
    }

    @Test
    public void testCountOccurrences() {
        assertEquals(2, StringProcessor.countOccurrences("hello world hello", "hello"));
        assertEquals(1, StringProcessor.countOccurrences("hello world", "world"));
        assertEquals(0, StringProcessor.countOccurrences("hello world", "test"));

        assertEquals(0, StringProcessor.countOccurrences("", "hello"));
        assertEquals(0, StringProcessor.countOccurrences(null, "hello"));

        assertEquals(2, StringProcessor.countOccurrences("aaaa", "aa"));

        assertThrows(IllegalArgumentException.class, () -> {
            StringProcessor.countOccurrences("hello", "");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            StringProcessor.countOccurrences("hello", null);
        });
    }

    @Test
    public void testReplaceNumbers() {
        assertEquals("одиндватри", StringProcessor.replaceNumbers("123"));
        assertEquals("aодинbдваcтриd", StringProcessor.replaceNumbers("a1b2c3d"));
        assertNull(StringProcessor.replaceNumbers(null));
    }

    @Test
    public void testRemoveEvenChars() {
        StringBuilder sb1 = new StringBuilder("123456");
        StringProcessor.removeEvenChars(sb1);
        assertEquals("135", sb1.toString());

        StringBuilder sb2 = new StringBuilder("1");
        StringProcessor.removeEvenChars(sb2);
        assertEquals("1", sb2.toString());

        StringProcessor.removeEvenChars(null); // Не должно вызывать исключение
    }

    @Test
    public void testReverseWords() {
        assertEquals("dd  cc bbb aaa",
                StringProcessor.reverseWords("aaa  bbb cc dd"));
    }

    @Test
    public void testReplaceHexNumbers() {
        assertEquals("Васе 16 лет", StringProcessor.replaceHexNumbers("Васе 0x00000010 лет"));
        assertEquals("Number 255", StringProcessor.replaceHexNumbers("Number 0x000000FF"));
    }
}