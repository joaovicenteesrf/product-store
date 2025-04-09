package com.example.demo.serviceTest;

import com.example.demo.dto.ProdutoDTO;
import com.example.demo.entities.Produto;
import com.example.demo.exception.ApiException;
import com.example.demo.repositories.ProdutoRepository;
import com.example.demo.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Test
    void deveCriarProdutoComSucesso() {
        ProdutoDTO produtoDTO = new ProdutoDTO("Mouse", BigDecimal.valueOf(120));
        Produto produtoMock = Produto.builder().nome("Mouse").valor(BigDecimal.valueOf(120)).quantidade(50).build();

        when(produtoRepository.findByNome("Mouse")).thenReturn(Optional.empty());
        when(produtoRepository.save(any())).thenReturn(produtoMock);

        Produto result = produtoService.createProduct(produtoDTO);

        assertNotNull(result);
        assertEquals("Mouse", result.getNome());
        assertEquals(BigDecimal.valueOf(120), result.getValor());
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void deveLancarErroAoCriarProdutoDuplicado() {
        ProdutoDTO produtoDTO = new ProdutoDTO("Teclado", BigDecimal.valueOf(200));
        Produto produtoExistente = Produto.builder().nome("Teclado").valor(BigDecimal.valueOf(200)).build();

        when(produtoRepository.findByNome("Teclado")).thenReturn(Optional.of(produtoExistente));

        ApiException exception = assertThrows(ApiException.class, () -> {
            produtoService.createProduct(produtoDTO);
        });

        assertEquals("Produto já cadastrado. Tente novamente", exception.getMessage());
    }
}

