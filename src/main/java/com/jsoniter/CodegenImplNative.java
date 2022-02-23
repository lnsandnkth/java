package com.jsoniter;

import com.jsoniter.any.Any;
import com.jsoniter.spi.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static com.jsoniter.CoverageSuit.stmt_covered;

class CodegenImplNative {
    final static Map<String, String> NATIVE_READS = new HashMap<String, String>() {{
        put("float", "iter.readFloat()");
        put("double", "iter.readDouble()");
        put("boolean", "iter.readBoolean()");
        put("byte", "iter.readShort()");
        put("short", "iter.readShort()");
        put("int", "iter.readInt()");
        put("char", "iter.readInt()");
        put("long", "iter.readLong()");
        put(Float.class.getName(), "(iter.readNull() ? null : java.lang.Float.valueOf(iter.readFloat()))");
        put(Double.class.getName(), "(iter.readNull() ? null : java.lang.Double.valueOf(iter.readDouble()))");
        put(Boolean.class.getName(), "(iter.readNull() ? null : java.lang.Boolean.valueOf(iter.readBoolean()))");
        put(Byte.class.getName(), "(iter.readNull() ? null : java.lang.Byte.valueOf((byte)iter.readShort()))");
        put(Character.class.getName(), "(iter.readNull() ? null : java.lang.Character.valueOf((char)iter.readShort()))");
        put(Short.class.getName(), "(iter.readNull() ? null : java.lang.Short.valueOf(iter.readShort()))");
        put(Integer.class.getName(), "(iter.readNull() ? null : java.lang.Integer.valueOf(iter.readInt()))");
        put(Long.class.getName(), "(iter.readNull() ? null : java.lang.Long.valueOf(iter.readLong()))");
        put(BigDecimal.class.getName(), "iter.readBigDecimal()");
        put(BigInteger.class.getName(), "iter.readBigInteger()");
        put(String.class.getName(), "iter.readString()");
        put(Object.class.getName(), "iter.read()");
        put(Any.class.getName(), "iter.readAny()");
    }};
    final static Map<Class, Decoder> NATIVE_DECODERS = new HashMap<Class, Decoder>() {{
        put(float.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readFloat();
            }
        });
        put(Float.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readNull() ? null : iter.readFloat();
            }
        });
        put(double.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readDouble();
            }
        });
        put(Double.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readNull() ? null : iter.readDouble();
            }
        });
        put(boolean.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readBoolean();
            }
        });
        put(Boolean.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readNull() ? null : iter.readBoolean();
            }
        });
        put(byte.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return Byte.valueOf((byte) iter.readShort());
            }
        });
        put(Byte.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readNull() ? null : (byte)iter.readShort();
            }
        });
        put(short.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readShort();
            }
        });
        put(Short.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readNull() ? null : iter.readShort();
            }
        });
        put(int.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readInt();
            }
        });
        put(Integer.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readNull() ? null : iter.readInt();
            }
        });
        put(char.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return (char)iter.readInt();
            }
        });
        put(Character.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readNull() ? null : (char)iter.readInt();
            }
        });
        put(long.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readLong();
            }
        });
        put(Long.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readNull() ? null : iter.readLong();
            }
        });
        put(BigDecimal.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readBigDecimal();
            }
        });
        put(BigInteger.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readBigInteger();
            }
        });
        put(String.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readString();
            }
        });
        put(Object.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.read();
            }
        });
        put(Any.class, new Decoder() {
            @Override
            public Object decode(JsonIterator iter) throws IOException {
                return iter.readAny();
            }
        });
    }};

    public static String genReadOp(Type type) {
        String cacheKey = TypeLiteral.create(type).getDecoderCacheKey();
        return String.format("(%s)%s", getTypeName(type), genReadOp(cacheKey, type));
    }

    public static String getTypeName(Type fieldType) {
        if (fieldType instanceof Class) {
            Class clazz = (Class) fieldType;
            return clazz.getCanonicalName();
        } else if (fieldType instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) fieldType;
            Class clazz = (Class) pType.getRawType();
            return clazz.getCanonicalName();
        } else if (fieldType instanceof WildcardType) {
            return Object.class.getCanonicalName();
        } else {
            throw new JsonException("unsupported type: " + fieldType);
        }
    }

    static String genField(Binding field) {
        String fieldCacheKey = field.decoderCacheKey();
        Type fieldType = field.valueType;
        return String.format("(%s)%s", getTypeName(fieldType), genReadOp(fieldCacheKey, fieldType));

    }

    private static String genReadOp(String cacheKey, Type valueType) {
        // ------------------------------------------------------------------------

        int i = 0;
        stmt_covered[i] = true;

        // ------------------------------------------------------------------------
        // the field decoder might be registered directly
        Decoder decoder = JsoniterSpi.getDecoder(cacheKey);
        if (decoder == null) {
            i = 1;
            stmt_covered[i] = true;

            // if cache key is for field, and there is no field decoder specified
            // update cache key for normal type
            cacheKey = TypeLiteral.create(valueType).getDecoderCacheKey();
            decoder = JsoniterSpi.getDecoder(cacheKey);
            if (decoder == null) {
                i = 2;
                stmt_covered[i] = true;
    
                if (valueType instanceof Class) {
                    i = 3;
                    stmt_covered[i] = true;
        
                    Class clazz = (Class) valueType;
                    String nativeRead = NATIVE_READS.get(clazz.getCanonicalName());
                    if (nativeRead != null) {
                        i = 4;
                        stmt_covered[i] = true;
            
                        return nativeRead;
                    }
                } else if (valueType instanceof WildcardType) {
                    i = 5;
                    stmt_covered[i] = true;
        
                    return NATIVE_READS.get(Object.class.getCanonicalName());
                }
                Codegen.getDecoder(cacheKey, valueType);
                if (Codegen.canStaticAccess(cacheKey)) {
                    i = 6;
                    stmt_covered[i] = true;
        
                    return String.format("%s.decode_(iter)", cacheKey);
                } else {
                    // can not use static "decode_" method to access, go through codegen cache
                    return String.format("com.jsoniter.CodegenAccess.read(\"%s\", iter)", cacheKey);
                }
            }
        }

        if (valueType == boolean.class) {
            i = 7;
            stmt_covered[i] = true;

            if (!(decoder instanceof Decoder.BooleanDecoder)) {
                i = 8;
                stmt_covered[i] = true;
    
                throw new JsonException("decoder for " + cacheKey + "must implement Decoder.BooleanDecoder");
            }
            return String.format("com.jsoniter.CodegenAccess.readBoolean(\"%s\", iter)", cacheKey);
        }
        if (valueType == byte.class) {
            i = 9;
            stmt_covered[i] = true;

            if (!(decoder instanceof Decoder.ShortDecoder)) {
                i = 10;
                stmt_covered[i] = true;
    
                throw new JsonException("decoder for " + cacheKey + "must implement Decoder.ShortDecoder");
            }
            return String.format("com.jsoniter.CodegenAccess.readShort(\"%s\", iter)", cacheKey);
        }
        if (valueType == short.class) {
            i = 11;
            stmt_covered[i] = true;

            if (!(decoder instanceof Decoder.ShortDecoder)) {
                i = 12;
                stmt_covered[i] = true;
    
                throw new JsonException("decoder for " + cacheKey + "must implement Decoder.ShortDecoder");
            }
            return String.format("com.jsoniter.CodegenAccess.readShort(\"%s\", iter)", cacheKey);
        }
        if (valueType == char.class) {
            i = 13;
            stmt_covered[i] = true;

            if (!(decoder instanceof Decoder.IntDecoder)) {
                i = 14;
                stmt_covered[i] = true;
    
                throw new JsonException("decoder for " + cacheKey + "must implement Decoder.IntDecoder");
            }
            return String.format("com.jsoniter.CodegenAccess.readInt(\"%s\", iter)", cacheKey);
        }
        if (valueType == int.class) {
            i = 15;
            stmt_covered[i] = true;

            if (!(decoder instanceof Decoder.IntDecoder)) {
                i = 16;
                stmt_covered[i] = true;
    
                throw new JsonException("decoder for " + cacheKey + "must implement Decoder.IntDecoder");
            }
            return String.format("com.jsoniter.CodegenAccess.readInt(\"%s\", iter)", cacheKey);
        }
        if (valueType == long.class) {
            i = 17;
            stmt_covered[i] = true;

            if (!(decoder instanceof Decoder.LongDecoder)) {
                i = 18;
                stmt_covered[i] = true;
    
                throw new JsonException("decoder for " + cacheKey + "must implement Decoder.LongDecoder");
            }
            return String.format("com.jsoniter.CodegenAccess.readLong(\"%s\", iter)", cacheKey);
        }
        if (valueType == float.class) {
            i = 19;
            stmt_covered[i] = true;

            if (!(decoder instanceof Decoder.FloatDecoder)) {
                i = 20;
                stmt_covered[i] = true;
    
                throw new JsonException("decoder for " + cacheKey + "must implement Decoder.FloatDecoder");
            }
            return String.format("com.jsoniter.CodegenAccess.readFloat(\"%s\", iter)", cacheKey);
        }
        if (valueType == double.class) {
            i = 21;
            stmt_covered[i] = true;

            if (!(decoder instanceof Decoder.DoubleDecoder)) {
                i = 22;
                stmt_covered[i] = true;
    
                throw new JsonException("decoder for " + cacheKey + "must implement Decoder.DoubleDecoder");
            }
            return String.format("com.jsoniter.CodegenAccess.readDouble(\"%s\", iter)", cacheKey);
        }
        return String.format("com.jsoniter.CodegenAccess.read(\"%s\", iter)", cacheKey);
    }
}
