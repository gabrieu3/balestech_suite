package com.balestech.commom.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BalestechErrorDTO {
    private String code;
    private String message;
    private String field;
    private String action;

    public BalestechErrorDTO(String code, String message, String field) {
        this.code = code;
        this.message = message;
        this.field = field;
    }

    public BalestechErrorDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }
}