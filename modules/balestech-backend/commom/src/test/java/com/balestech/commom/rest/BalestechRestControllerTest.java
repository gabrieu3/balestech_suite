package com.balestech.commom.rest;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BalestechRestControllerTest extends BalestechRestController{


    @Test
    public void testHeritageClass(){
        handle(new Exception(), null);
    }
}
