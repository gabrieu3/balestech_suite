package com.balestech.commom.dto;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@NoArgsConstructor
public class BalestechRestErrorDTO extends BalestechErrorDTO{


    public BalestechRestErrorDTO(String code, String message, String field, String action) {
        super(code, message, field, action);
    }

    public BalestechRestErrorDTO(String code, String message, String field) {
        super(code, message, field);
    }

    public BalestechRestErrorDTO(String code, String message) {
        super(code, message);
    }

    public static BalestechRestErrorDTO fromBalestechErrorDTO(BalestechErrorDTO balestechErrorDTO) {
        return new BalestechRestErrorDTO(balestechErrorDTO.getCode(), balestechErrorDTO.getMessage(), balestechErrorDTO.getField(), balestechErrorDTO.getAction());
    }

    public static List<BalestechRestErrorDTO> fromListBalestechErrorDTO(List<BalestechErrorDTO> materaErrorDTOs) {
        List<BalestechRestErrorDTO> restErrors = new ArrayList(materaErrorDTOs.size());
        Iterator var2 = materaErrorDTOs.iterator();

        while(var2.hasNext()) {
            BalestechErrorDTO errorDTO = (BalestechErrorDTO)var2.next();
            restErrors.add(fromBalestechErrorDTO(errorDTO));
        }

        return restErrors;
    }
}