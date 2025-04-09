package com.example.demo.response;

import lombok.Data;

@Data
public class CpfValidationResponse {
    private boolean isValid;
    private String message;
}
