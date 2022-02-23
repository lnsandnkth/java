package com.jsoniter;

import junit.framework.TestCase;

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
}