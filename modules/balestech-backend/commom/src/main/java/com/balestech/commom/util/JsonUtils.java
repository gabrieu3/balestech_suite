package com.balestech.commom.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    public JsonUtils() {
    }

    public static String safeConversionToString(Object obj, ObjectMapper mapper) {
        if (obj == null) {
            return "null";
        } else {
            try {
                return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            } catch (Exception var3) {
                return obj.toString();
            }
        }
    }
}