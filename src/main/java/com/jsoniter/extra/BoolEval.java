package com.jsoniter.extra;

import com.jsoniter.JsonIterator;
import com.jsoniter.ValueType;
import com.jsoniter.spi.JsonException;

import java.io.IOException;

public class BoolEval {

    public static boolean evaluator(JsonIterator iter) throws IOException {
        ValueType valueType = iter.whatIsNext();
        if (valueType == ValueType.BOOLEAN) {
            CoverageTest.coverageArray[6] = true;
            CoverageTest.writeToFile();
            return iter.readBoolean();
        } else if (valueType == ValueType.NULL) {
            CoverageTest.coverageArray[7] = true;
            CoverageTest.writeToFile();
            iter.skip();
            return false;
        } else {
            throw new JsonException("expect boolean, but found " + valueType);
        }


    }
}
