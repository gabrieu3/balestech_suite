package com.balestech.commom.util;

import java.util.ArrayList;
import java.util.List;

public class EnumUtils {

    public static <T extends Enum<T>> List<T> toList(Class<T> enumClass) {
        List<T> enumList = new ArrayList<>();
        T[] enumValues = enumClass.getEnumConstants();
        for (T enumValue : enumValues) {
            enumList.add(enumValue);
        }
        return enumList;
    }
}
