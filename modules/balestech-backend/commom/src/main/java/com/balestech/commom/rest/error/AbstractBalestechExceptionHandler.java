package com.balestech.commom.rest.error;

import com.balestech.commom.config.ProdutosCoreObjectMapper;
import com.balestech.commom.domain.error.BalestechRestErrorDTO;
import com.balestech.commom.domain.error.BalestechRestResponseDTO;
import com.balestech.commom.util.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
@Slf4j
public abstract class AbstractBalestechExceptionHandler implements BalestechExceptionHandler {

    private static final String LINE_BREAK = System.getProperty("line.separator");

    private ObjectMapper objMapper = new ProdutosCoreObjectMapper();

    @Autowired
    private BalestechStandardErrorsCreator standardErrorsCreator;

    @Override
    public ResponseEntity<BalestechRestResponseDTO<Object>> handleException(Exception ex, HttpServletRequest req) {
        Locale localeToUse = null;

        try {
            log.error("Erro na API REST " + req.getRequestURI() + " - parâmetros - " + Arrays.toString(req.getParameterMap().entrySet().toArray()), ex);
            this.validateExceptionType(ex);
            localeToUse = LocaleContextHolder.getLocale();
            List<BalestechRestErrorDTO> errors = this.composeErrors(ex, localeToUse);
            log.error("Erros que serão retornados na response: " + LINE_BREAK + JsonUtils.safeConversionToString(errors, this.objMapper));
            return ((ResponseEntity.BodyBuilder)ResponseEntity.status(this.defineHttpStatus(ex)).header("Content-type", new String[]{"application/json;charset=UTF-8"})).body(BalestechRestResponseDTO.withErrors(errors));
        } catch (Exception var5) {
            log.error(String.format("Erro no tratamento de erro da API REST %s. Será retornado internal server error (500).", req.getRequestURI()), var5);
            return this.internalServerError(localeToUse);
        }
    }

    @Override
    public boolean supports(Exception var1) {
        return false;
    }

    private void validateExceptionType(Exception ex) {
        if (!this.supports(ex)) {
            throw new IllegalStateException(String.format("O exception handler %s foi invocado para tratar uma exceção do tipo %s porém ele não suporta este tipo de exceção. Cheque as configurações de exception handlers.", this.getClass().getSimpleName(), ex.getClass()));
        }
    }

    protected abstract List<BalestechRestErrorDTO> composeErrors(Exception var1, Locale var2);

    private HttpStatus defineHttpStatus(Exception ex) {
        ResponseStatus responseStatus = (ResponseStatus) AnnotatedElementUtils.findMergedAnnotation(ex.getClass(), ResponseStatus.class);
        return responseStatus != null ? responseStatus.code() : this.httpStatusToReturn(ex);
    }

    protected HttpStatus httpStatusToReturn() {
        return HttpStatus.BAD_REQUEST;
    }

    protected HttpStatus httpStatusToReturn(Exception ex) {
        return this.httpStatusToReturn();
    }

    private ResponseEntity<BalestechRestResponseDTO<Object>> internalServerError(Locale locale) {
        BalestechRestErrorDTO error = BalestechRestErrorDTO.fromBalestechErrorDTO(this.standardErrorsCreator.internalServerError(locale));
        return ((ResponseEntity.BodyBuilder)ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Content-type", new String[]{"application/json;charset=UTF-8"})).body(BalestechRestResponseDTO.withError(error));
    }
}
