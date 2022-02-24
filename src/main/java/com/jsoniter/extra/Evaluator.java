package com.jsoniter.extra;

import com.jsoniter.JsonIterator;
import com.jsoniter.ValueType;
import com.jsoniter.spi.JsonException;

import java.io.IOException;

public class Evaluator {

    public static Object evaluator(JsonIterator iter) throws IOException {

        ValueType valueType = iter.whatIsNext();
        if (valueType == ValueType.STRING) {
            CoverageTest.coverageArray[2] = true;
            CoverageTest.writeToFile();
            return iter.readString();
        } else if (valueType == ValueType.NUMBER) {
            CoverageTest.coverageArray[3] = true;
            CoverageTest.writeToFile();
            return iter.readNumberAsString();
        } else if (valueType == ValueType.BOOLEAN) {
            CoverageTest.coverageArray[4] = true;
            CoverageTest.writeToFile();
            return iter.readBoolean() ? "true" : "false";
        } else if (valueType == ValueType.NULL) {
            CoverageTest.coverageArray[5] = true;
            CoverageTest.writeToFile();
            iter.skip();
            return null;
        } else {
            throw new JsonException("expect string, but found " + valueType);
        }
    }
}
