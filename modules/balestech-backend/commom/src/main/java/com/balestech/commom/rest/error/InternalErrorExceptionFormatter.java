package com.balestech.commom.rest.error;


import com.balestech.commom.dto.BalestechErrorDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@NoArgsConstructor
public class InternalErrorExceptionFormatter {
    @Autowired
    private BalestechStandardErrorsCreator standardErrrorsCreator;

    public List<BalestechErrorDTO> format(Exception exception) {
        return Arrays.asList(this.standardErrrorsCreator.internalServerError(LocaleContextHolder.getLocale(), exception));
    }
}

