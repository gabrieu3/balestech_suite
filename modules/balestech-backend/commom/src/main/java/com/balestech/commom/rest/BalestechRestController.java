package com.balestech.commom.rest;


import com.balestech.commom.dto.BalestechRestResponseDTO;
import com.balestech.commom.rest.error.BalestechGenericExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class BalestechRestController{

    @Autowired
    private BalestechGenericExceptionHandler exceptionHandler;

    @ExceptionHandler({Exception.class})
    public ResponseEntity<BalestechRestResponseDTO<Object>> handle(Exception ex, HttpServletRequest req) {
        return this.exceptionHandler.handleException(ex, req);
    }

}
