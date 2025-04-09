package com.example.demo.service;

import com.example.demo.client.CpfValidatorClient;
import com.example.demo.enums.DemoApiCodeEnum;
import com.example.demo.exception.ApiException;
import feign.FeignException;
import org.springframework.web.client.RestTemplate;

public class CpfValidatorService {

    private final CpfValidatorClient cpfValidatorClient;

    public CpfValidatorService(CpfValidatorClient cpfValidatorClient, RestTemplate restTemplate) {
        this.cpfValidatorClient = cpfValidatorClient;
    }

    public boolean isCpfValid(String cpf) {
        try {
            return cpfValidatorClient.validateCpf(cpf).isValid();
        } catch (FeignException.NotFound e) {
            return false;
        }
        catch (Exception e) {
            throw new ApiException(DemoApiCodeEnum.API_ERROR_EXTERNAL_SERVICE, "Não foi possível validar o CPF");
        }
    }
}
