package com.jsoniter.spi;

import com.google.gson.internal.bind.util.ISO8601Utils;
import fr.mxyns.coverage.Coverage;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public interface OmitValue {

    boolean shouldOmit(Object val);

    String code();

    class Null implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return val == null;
        }

        @Override
        public String code() {
            return "null == %s";
        }
    }

    class ZeroByte implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return (Byte) val == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroShort implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return (Short) val == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroInt implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return ((Integer) val) == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroLong implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return ((Long) val) == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroFloat implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return ((Float) val) == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroDouble implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return ((Double) val) == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroChar implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return (Character) val == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class False implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return !((Boolean) val);
        }

        @Override
        public String code() {
            return "false == %s";
        }
    }

    class Parsed implements OmitValue {

        private final Object defaultValue;
        private final String code;

        public Parsed(Object defaultValue, String code) {
            this.defaultValue = defaultValue;
            this.code = code;
        }
        
        public static int constantID;
        static {
            System.out.println("=== COVERAGE INITIATED ===");
            constantID = Coverage.initCoverage(19);
            Coverage.dumpResults(constantID);
            System.out.println("=== DUMPED RESULTS ===");
        }

        public static OmitValue parse(Type valueType, String defaultValueToOmit) {

            int coverageId = Parsed.constantID;

            Coverage.markBranch(coverageId, Coverage.FUNCTION_CALL_KEY);
            System.out.println("PUTE : " + valueType  + " : " + defaultValueToOmit);

            /* branch id = 0 */
            if ("void".equals(defaultValueToOmit)) {
                Coverage.markBranch(coverageId, 0);
                return null;
            }
            /* branch id = 1 */
            else if ("null".equals(defaultValueToOmit)) {
                Coverage.markBranch(coverageId, 1);
                return new OmitValue.Null();
            }
            /* branch id = 2 */
            else if (boolean.class.equals(valueType)) {
                Coverage.markBranch(coverageId, 2);
                Boolean defaultValue = Boolean.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s");
            }
            /* branch id = 3 */
            else if (Boolean.class.equals(valueType)) {
                Coverage.markBranch(coverageId, 3);
                Boolean defaultValue = Boolean.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s.booleanValue()");
            }
            /* branch id = 4 */
            else if (int.class.equals(valueType)) {
                Coverage.markBranch(coverageId, 4);
                Integer defaultValue = Integer.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s");
            }
            /* branch id = 5 */
            else if (Integer.class.equals(valueType)) {
                Coverage.markBranch(coverageId, 5);
                Integer defaultValue = Integer.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s.intValue()");
            }
            /* branch id = 6 */
            else if (byte.class.equals(valueType)) {
                Coverage.markBranch(coverageId, 6);
                Byte defaultValue = Byte.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s");
            }
            /* branch id = 7 */
            else if (Byte.class.equals(valueType)) {
                Coverage.markBranch(coverageId, 7);
                Byte defaultValue = Byte.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s.byteValue()");
            }
            /* branch id = 8 */
            else if (short.class.equals(valueType)) {
                Coverage.markBranch(coverageId, 8);
                Short defaultValue = Short.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s");
            }
            /* branch id = 9 */
            else if (Short.class.equals(valueType)) {
                Coverage.markBranch(coverageId, 9);
                Short defaultValue = Short.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s.shortValue()");
            }
            /* branch id = 10 */
            else if (long.class.equals(valueType)) {
                Coverage.markBranch(coverageId, 10);
                Long defaultValue = Long.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "L == %s");
            }
            /* branch id = 11 */
            else if (Long.class.equals(valueType)) {
                Coverage.markBranch(coverageId, 11);
                Long defaultValue = Long.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "L == %s.longValue()");
            }
            /* branch id = 12 */
            else if (float.class.equals(valueType)) {
                Coverage.markBranch(coverageId, 12);
                Float defaultValue = Float.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "F == %s");
            }
            /* branch id = 13 */
            else if (Float.class.equals(valueType)) {
                Coverage.markBranch(coverageId, 13);
                Float defaultValue = Float.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "F == %s.floatValue()");
            }
            /* branch id = 14 */
            else if (double.class.equals(valueType)) {
                Coverage.markBranch(coverageId, 14);
                Double defaultValue = Double.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "D == %s");
            }
            /* branch id = 15 */
            else if (Double.class.equals(valueType)) {
                Coverage.markBranch(coverageId, 15);
                Double defaultValue = Double.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "D == %s.doubleValue()");
            }
            /* branch id = 16 */
            else if (char.class.equals(valueType) && defaultValueToOmit.length() == 1) {
                Coverage.markBranch(coverageId, 16);
                Character defaultValue = defaultValueToOmit.charAt(0);
                return new OmitValue.Parsed(defaultValue, "'" + defaultValueToOmit + "' == %s");
            }
            /* branch id = 17 */
            else if (Character.class.equals(valueType) && defaultValueToOmit.length() == 1) {
                Coverage.markBranch(coverageId, 17);
                Character defaultValue = defaultValueToOmit.charAt(0);
                return new OmitValue.Parsed(defaultValue, "'" + defaultValueToOmit + "' == %s.charValue()");
            }
            /* branch id = 18 */
            else {
                Coverage.markBranch(coverageId, 18);
                throw new UnsupportedOperationException("failed to parse defaultValueToOmit: " + defaultValueToOmit);
            }
        }

        @Override
        public boolean shouldOmit(Object val) {
            return defaultValue.equals(val);
        }

        @Override
        public String code() {
            return code;
        }
    }
}
