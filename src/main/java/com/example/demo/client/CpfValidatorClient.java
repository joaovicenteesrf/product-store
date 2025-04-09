package com.example.demo.client;

import com.example.demo.response.CpfValidationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${external.cpf-validator.url", name = "apiPaymentClient")
public interface CpfValidatorClient {

    // Realiza chamadas externas para uma url configurada no application.yml
    @GetMapping(value = "/cpf/validate/{cpf}")
    CpfValidationResponse validateCpf(@PathVariable("cpf") String cpf);
}
