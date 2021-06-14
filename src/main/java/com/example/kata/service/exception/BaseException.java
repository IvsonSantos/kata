package com.example.kata.service.exception;

import com.example.kata.service.helper.MessageHelper;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
@NoArgsConstructor
public class BaseException extends RuntimeException {

    private String messageKey;
    private Object[] messageArgs;

    public BaseException(Throwable throwable) {
        super(throwable);
    }

    public BaseException(String messageKey, Object... args) {
        super(MessageHelper.get(messageKey, args));
    }

    public String getUserMessage() {
        return MessageHelper.get(messageKey, messageArgs);
    }

    public BaseException userMessage(String messageKey, Object... messageArgs) {
        this.messageArgs = messageArgs;
        this.messageKey = messageKey;
        return this;
    }
}
