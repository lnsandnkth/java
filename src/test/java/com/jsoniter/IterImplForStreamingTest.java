package com.jsoniter;

import com.jsoniter.any.Any;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class IterImplForStreamingTest extends TestCase {

	public void testReadMaxDouble() throws Exception {
		String maxDouble = "1.7976931348623157e+308";
		JsonIterator iter = JsonIterator.parse("1.7976931348623157e+308");
		IterImplForStreaming.numberChars numberChars = IterImplForStreaming.readNumber(iter);
		String number = new String(numberChars.chars, 0, numberChars.charsLength);
		assertEquals(maxDouble, number);
	}

	public void testStatement3() throws Exception {
		// test resusable chars
		String resuablecharnumber = "555555555555555555555555555555555555555";
		JsonIterator iter = JsonIterator.parse("555555555555555555555555555555555555555");
		IterImplForStreaming.numberChars numberChars = IterImplForStreaming.readNumber(iter);
		String number = new String(numberChars.chars, 0, numberChars.charsLength);
		assertEquals(resuablecharnumber, number);
	}

	public void testReadAnyRepeating() throws Exception {
		// test statement 8
		String resuablecharnumber = "555555555555555555555555555555555555555";
		JsonIterator iter = JsonIterator.parse("555555555555555555555555555555555555555");
		Any numberChars = IterImplForStreaming.readAny(iter);
		String number = numberChars.toString();
		assertEquals(resuablecharnumber, number);
}

	public void testReadAnyInt() throws Exception {
		int intnumber = 235236;
		JsonIterator iter = JsonIterator.parse("235236");
		Any numberChars = IterImplForStreaming.readAny(iter);
		int number = numberChars.toInt();
		assertEquals(intnumber, number);
	}

	public void testReadAnyString() throws Exception {
		String teststring = "Hello";
		JsonIterator iter = JsonIterator.parse("Hello");
		Any midstring = IterImplForStreaming.readAny(iter);
		String string = midstring.toString();
		assertEquals(teststring, string);
	}

	public void testReadAnyBooleanTrue() throws Exception {
		boolean testbool = true;
		JsonIterator iter = JsonIterator.parse("true");
		Any midbool = IterImplForStreaming.readAny(iter);
		boolean resultbool = midbool.toBoolean();
		assertEquals(testbool, resultbool);
	}

	public void testReadAnyBooleanFalse() throws Exception {
		boolean testbool = false;
		JsonIterator iter = JsonIterator.parse("false");
		Any midbool = IterImplForStreaming.readAny(iter);
		boolean resultbool = midbool.toBoolean();
		assertEquals(testbool, resultbool);
	}

	public void testReadAnyArrayOfInts() throws Exception {
		List<Integer> testarray = new ArrayList<>();
		testarray.add(1);testarray.add(2);testarray.add(3);
		JsonIterator iter = JsonIterator.parse("[1, 2, 3]");
		Any midarray = IterImplForStreaming.readAny(iter);
		String resultarray = midarray.toString();
		assertEquals(testarray.toString(), resultarray);
	}

	public void testReadAnyJsonObject() throws Exception {
		String testjson = "{Hello: true, World: false}";
		JsonIterator iter = JsonIterator.parse("{Hello: true, World: false}");
		Any midjson = IterImplForStreaming.readAny(iter);
		String resultjson = midjson.toString();
		assertEquals(testjson, resultjson);
	}
}