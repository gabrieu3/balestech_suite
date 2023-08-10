package com.balestech.Indicators;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Teste {
    public static void main(String[] args){
        String teste = concatOperations("","12,15,25");
        System.out.println(concatOperations2("10",null));
    }

    public static String concatOperations(String operationCode1, String operationCode2) {
        if (StringUtils.isBlank(operationCode1) && StringUtils.isBlank(operationCode2)) {
            return "";
        }

        StringJoiner joiner = new StringJoiner(",");
        if (StringUtils.isNotBlank(operationCode1)) {
            joiner.add(operationCode1);
        }
        if (StringUtils.isNotBlank(operationCode2)) {
            joiner.add(operationCode2);
        }

        return null;
    }

    public static String concatOperations2(String operationCode1, String operationCode2) {
        return Stream.of(operationCode1, operationCode2)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.joining(","));
    }

    private static Iterable<Long> carregarComDefault() {
        List<Long> franchiseOperations = new ArrayList<>();
        /*franchiseOperations.add(1L);
        franchiseOperations.add(2L);
        franchiseOperations.add(3L);*/
        // Adicione aqui os valores padrão ou carregue-os de alguma fonte de dados
        return franchiseOperations;
    }

    public static  String getFranchiseOperationCode() {
        Iterable<Long> franchiseOperations = carregarComDefault();

        StringJoiner franchiseOperationCodes = new StringJoiner(",");

        for (Long franchiseOperation : franchiseOperations) {
            franchiseOperationCodes.add(franchiseOperation.toString());
        }

        return franchiseOperationCodes.toString();
    }
}
