package com.example.demo.serviceTest;
import com.example.demo.dto.CompraDTO;
import com.example.demo.dto.PedidoDTO;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Pedido;
import com.example.demo.entities.Produto;
import com.example.demo.enums.StatusPedido;
import com.example.demo.repositories.PedidoRepository;
import com.example.demo.service.ClienteService;
import com.example.demo.service.PedidoService;
import com.example.demo.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Test
    void deveCriarPedidoComSucesso() {
        Cliente cliente = Cliente.builder().id(1L).email("teste@email.com").build();
        Produto produto = Produto.builder().nome("Produto 1").valor(BigDecimal.TEN).build();
        CompraDTO compraDTO = new CompraDTO("Produto 1", 1);

        PedidoDTO pedidoDTO = new PedidoDTO("teste@email.com", List.of(compraDTO));

        when(clienteService.getClienteByEmail("teste@email.com")).thenReturn(cliente);
        when(produtoService.validateProduct("Produto 1")).thenReturn(true);
        when(produtoService.getProductByName("Produto 1")).thenReturn(produto);
        when(pedidoRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Pedido pedido = pedidoService.criarPedido(pedidoDTO);

        assertEquals(cliente, pedido.getCliente());
        assertEquals(1, pedido.getProdutos().size());
        assertEquals(BigDecimal.TEN, pedido.getTotal());
        assertEquals(StatusPedido.EM_PROCESSAMENTO, pedido.getStatus());
    }
}

