package com.balestech.b3.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class B3Util {

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
