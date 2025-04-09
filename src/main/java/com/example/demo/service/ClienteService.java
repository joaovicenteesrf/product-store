package com.example.demo.service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.entities.Cliente;
import com.example.demo.enums.DemoApiCodeEnum;
import com.example.demo.exception.ApiException;
import com.example.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente createCliente(ClienteDTO clienteDTO) {
        try {
            validateClientNotExists(clienteDTO.getEmail());

            Cliente cliente = Cliente.builder()
                    .nome(clienteDTO.getNome())
                    .email(clienteDTO.getEmail())
                    .build();

            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new ApiException(DemoApiCodeEnum.API_ERROR_COULDNT_CREATE_USER, String.format("Não foi possível criar o usuário: %s", e.getMessage()));
        }
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ApiException(DemoApiCodeEnum.API_ERROR_INEXISTENT_USER, "Não existe um usuário cadastrado com esse ID."));

    }

    public Cliente getClienteByEmail(String email) {
        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new ApiException(DemoApiCodeEnum.API_ERROR_INEXISTENT_EMAIL, "Não existe um usuário cadastrado com esse e-mail."));

    }

    public void updateClientName(ClienteDTO request) {
        Cliente cliente = getClienteByEmail(request.getEmail());

        cliente.setNome(request.getNome());
        clienteRepository.save(cliente);
    }

    private void validateClientNotExists(String email) {
       if (clienteRepository.findByEmail(email).isPresent()) {
           throw new ApiException(DemoApiCodeEnum.API_ERROR_USER_ALREADY_EXISTS, "Já existe um usuário para o email informado.");
       }
    }
}
