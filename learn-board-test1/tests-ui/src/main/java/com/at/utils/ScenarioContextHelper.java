package com.at.utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContextHelper {

    private static ThreadLocal<Map<String, Object>> context = new ThreadLocal<>();

    private ScenarioContextHelper() {
    }

    public static void init() {
        context.set(new HashMap<>());
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(final String key) {
        try {
            return (T) context.get().get(key);
        } catch (IllegalStateException ex) {
            throw new IllegalStateException(String.format("Value for key '%s' was not found in ScenarioContext", key));
        }
    }

    public static <T> void put(final String key, final T value) {
        context.get().remove(key);
        context.get().put(key, value);
    }
}