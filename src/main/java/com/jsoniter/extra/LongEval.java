package com.jsoniter.extra;

import com.jsoniter.JsonIterator;
import com.jsoniter.ValueType;
import com.jsoniter.spi.JsonException;

import java.io.IOException;

public class LongEval {

    public static long evaluator(JsonIterator iter) throws IOException {
        ValueType valueType = iter.whatIsNext();
        if (valueType == ValueType.NUMBER) {
            CoverageTest.coverageArray[9] = true;
            CoverageTest.writeToFile();
            return iter.readLong();
        } else if (valueType == ValueType.NULL) {
            CoverageTest.coverageArray[10] = true;
            CoverageTest.writeToFile();
            iter.skip();
            return 0;
        } else {
            throw new JsonException("expect long, but found " + valueType);
        }
    }
}
