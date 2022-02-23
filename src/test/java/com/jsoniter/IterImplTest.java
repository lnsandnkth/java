package com.jsoniter;

import com.jsoniter.spi.JsonException;
import junit.framework.TestCase;

import java.io.IOException;

public class IterImplTest extends TestCase {

    public void testInvalidString() throws IOException {
        try {
            JsonIterator.deserialize("\"\\fd834\\x\"", String.class);
        } catch (JsonException e) {
        } catch (IndexOutOfBoundsException e) {
        }
    }
    public void testNewLine() throws IOException {
        JsonIterator iter = JsonIterator.parse("\"[hehe\\n\"]\"");
        assertEquals("[hehe\n", iter.readString());
    }
    public void testSpecialString() throws IOException {
        JsonIterator iter = JsonIterator.parse("\"[totest\\b\"]\"");
        assertEquals("[totest\b", iter.readString());
    }
    public void testSpecialCharacter() throws IOException {
        JsonIterator iter1 = JsonIterator.parse("\"\u2620\"");
        assertEquals("☠", iter1.readString());
        JsonIterator iter2 = JsonIterator.parse("\"\ud83c\udf49\"");
        assertEquals("🍉", iter2.readString());
        JsonIterator iter3 = JsonIterator.parse("\"\ud83d\ude4f\"");
        assertEquals("🙏", iter3.readString());
        JsonIterator iter4 = JsonIterator.parse("\"\ud83d\udc6f\"");
        assertEquals("👯", iter4.readString());
    }


}