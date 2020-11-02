package com.zdd;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public final class Primitives {
    private Primitives() {
    }

    private static final Map<String, Class<?>> STRING_TO_PRIMITIVE_TYPE;
    private static final Map<String, Function> STRING_TO_FUNCTION;

    static {
        Map<String, Class<?>> strToPrim = new HashMap<>(16);
        Map<String, Function> strToFunc = new HashMap<>(16);

        add(strToPrim, "boolean", boolean.class);
//        add(strToPrim, "boolean", Boolean.TYPE);
        add(strToPrim, "byte", byte.class);
        add(strToPrim, "char", char.class);
        add(strToPrim, "double", double.class);
        add(strToPrim, "float", float.class);
        add(strToPrim, "int", int.class);
        add(strToPrim, "long", long.class);
        add(strToPrim, "short", short.class);
        add(strToPrim, "void", void.class);

        add(strToFunc, "int", (Function<String, Object>) Integer::valueOf);
        add(strToFunc, "long", (Function<String, Object>) Long::valueOf);
        add(strToFunc, "byte", (Function<String, Object>) Byte::valueOf);
        add(strToFunc, "short", (Function<String, Object>) Short::valueOf);
        add(strToFunc, "boolean", (Function<String, Object>) Boolean::valueOf);
        add(strToFunc, "double", (Function<String, Object>) Double::valueOf);


        STRING_TO_PRIMITIVE_TYPE = Collections.unmodifiableMap(strToPrim);
        STRING_TO_FUNCTION = Collections.unmodifiableMap(strToFunc);
    }

    private static void add(
            Map<String, Class<?>> forward,
            String key,
            Class<?> value) {
        forward.put(key, value);
    }

    private static void add(
            Map<String, Function> forward,
            String key,
            Function value) {
        forward.put(key, value);

    }

    public static boolean isPrimitive(String type) {
        return STRING_TO_PRIMITIVE_TYPE.containsKey(type);
    }

    public static Class<?> type(String type) {
        return STRING_TO_PRIMITIVE_TYPE.get(type);
    }


    public static Object box(String value, String type) {
        if (STRING_TO_FUNCTION.containsKey(type)){
          return STRING_TO_FUNCTION.get(type).apply(value);
        }
        return null;
    }

}
