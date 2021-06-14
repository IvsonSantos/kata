package com.example.kata.service.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageHelper {

    @Autowired
    private MessageSource messageSource;

    private static MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource);
    }

    public static String get(String code, Object... args) {
        return accessor.getMessage(code, args);
    }
}
