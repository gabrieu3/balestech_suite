package com.balestech.commom.config;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProdutosCoreObjectMapper extends ObjectMapper {
    private static final String TIMEZONE_PADRAO = "America/Sao_Paulo";

    public ProdutosCoreObjectMapper() {
        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        this.configuraTimeZone();
        this.setSerializationInclusion(Include.NON_NULL);
        this.enable(SerializationFeature.INDENT_OUTPUT);
        this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        this.registerModule(javaTimeModule);
    }

    public ObjectMapper copy() {
        return new ProdutosCoreObjectMapper();
    }

    public SerializationConfig getSerializationConfig() {
        return super.getSerializationConfig().with(Feature.WRITE_BIGDECIMAL_AS_PLAIN);
    }

    private void configuraTimeZone() {
        TimeZone timeZoneDefault = TimeZone.getDefault();
        log.debug("Using JVM default timezone: " + timeZoneDefault);
        this.setTimeZone(timeZoneDefault);
    }
}
