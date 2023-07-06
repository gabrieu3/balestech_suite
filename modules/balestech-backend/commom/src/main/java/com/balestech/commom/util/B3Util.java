package com.balestech.commom.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.List;

public class B3Util {

    public static Double greater(List<Double> values){
        Double greaterValue = 0.0;
        for(Double value: values){
            if(value.compareTo(greaterValue) > 0)
                greaterValue = value;
        }
        return greaterValue;
    }

    public static Double lower(List<Double> values){
        Double lowerValue = 0.0;

        for(Double value: values){
            if(value.compareTo(lowerValue) < 0 )
                lowerValue = value;
        }
        return lowerValue;
    }

    public static Double lowerThatNotIsZero(List<Double> values){
        Double lowerValue = Double.MAX_VALUE;

        for(Double value: values){
            if(value.compareTo(lowerValue) < 0 && value > 0)
                lowerValue = value;
        }
        if(lowerValue.compareTo(Double.MAX_VALUE) == 0 )
            lowerValue = 0.0;

        return lowerValue;
    }

    public static BigDecimal formatBigDecimal(String value, char decimal, char grouping){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(decimal);
        symbols.setGroupingSeparator(grouping);
        BigDecimal number = new BigDecimal("0");
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        decimalFormat.setParseBigDecimal(true);
        try {
            number = (BigDecimal) decimalFormat.parse(value);
        } catch (ParseException e) {
            System.out.println("Invalid number format: " + value);
        }
        return number;
    }
}
