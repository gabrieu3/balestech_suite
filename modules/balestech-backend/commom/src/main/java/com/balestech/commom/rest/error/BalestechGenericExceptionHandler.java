package com.balestech.commom.rest.error;

import com.balestech.commom.dto.BalestechRestErrorDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
@NoArgsConstructor
public class BalestechGenericExceptionHandler extends AbstractBalestechExceptionHandler{

    @Autowired
    private InternalErrorExceptionFormatter formatter;

    @Override
    protected List<BalestechRestErrorDTO> composeErrors(Exception ex, Locale locale) {
        return BalestechRestErrorDTO.fromListBalestechErrorDTO(this.formatter.format(ex));
    }

    protected HttpStatus httpStatusToReturn() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public boolean supports(Exception ex) {
        return true;
    }
}
