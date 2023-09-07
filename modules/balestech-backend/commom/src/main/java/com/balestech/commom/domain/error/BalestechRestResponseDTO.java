package com.balestech.commom.domain.error;

import lombok.*;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BalestechRestResponseDTO<T> {
    private T data;
    private List<BalestechRestErrorDTO> error;

    public static BalestechRestResponseDTO<Object> withErrors(List<BalestechRestErrorDTO> errors) {
        BalestechRestResponseDTO<Object> restReturn = new BalestechRestResponseDTO();
        restReturn.setError(errors);
        return restReturn;
    }

    public static BalestechRestResponseDTO<Object> withError(BalestechRestErrorDTO error) {
        return withErrors(Arrays.asList(error));
    }

}
