package com.example.demo.exception;

import com.example.demo.enums.DemoApiCodeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends RuntimeException {

    protected final DemoApiCodeEnum code;
    protected Object response;

    public ApiException(final DemoApiCodeEnum code, final String message) {
        super(message);
        this.code = code;
    }
}
