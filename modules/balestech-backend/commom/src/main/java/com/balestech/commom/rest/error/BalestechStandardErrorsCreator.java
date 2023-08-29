package com.balestech.commom.rest.error;

import java.util.Locale;

import com.balestech.commom.domain.error.BalestechErrorDTO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class BalestechStandardErrorsCreator {

    @Autowired
    @Qualifier("balestechValidationMessageSource")
    private ReloadableResourceBundleMessageSource messageSource;

    public BalestechErrorDTO internalServerError(Locale locale) {
        String code = "BALESTECH-003";
        String message = this.messageSource.getMessage(code, (Object[])null, locale != null ? locale : LocaleContextHolder.getLocale());
        return new BalestechErrorDTO(code, message);
    }

    public BalestechErrorDTO internalServerError(Locale locale, Exception exception) {
        String code = "BALESTECH-003";
        String message = exception.getMessage();
        return new BalestechErrorDTO(code, message);
    }
}

