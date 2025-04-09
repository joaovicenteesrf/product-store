package com.example.demo.service;

import com.example.demo.dto.CompraDTO;
import com.example.demo.dto.PedidoDTO;
import com.example.demo.entities.Cliente;
import com.example.demo.entities.Pedido;
import com.example.demo.entities.Produto;
import com.example.demo.enums.DemoApiCodeEnum;
import com.example.demo.enums.StatusPedido;
import com.example.demo.exception.ApiException;
import com.example.demo.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PedidoRepository pedidoRepository;


    public Pedido criarPedido(PedidoDTO pedidoDTO) {
        try {
            Cliente cliente = clienteService.getClienteByEmail(pedidoDTO.getClienteEmail());

            List<Produto> produtoList = buildProductList(pedidoDTO.getProdutos());

            BigDecimal totalPrice = calculateTotalPrice(produtoList);

            Pedido pedido = Pedido.builder()
                    .cliente(cliente)
                    .data(LocalDateTime.now())
                    .status(StatusPedido.EM_PROCESSAMENTO)
                    .produtos(produtoList)
                    .total(totalPrice)
                    .build();

            pedidoRepository.save(pedido);
            return pedido;
        } catch (Exception e) {
            throw new ApiException(DemoApiCodeEnum.API_ERROR_COULDNT_CREATE_ORDER, String.format("Não foi possível criar o pedido: %s", e.getMessage()));
        }

    }

    private BigDecimal calculateTotalPrice (List<Produto> produtos) {
        return produtos.stream()
                .map(Produto::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<Produto> buildProductList(List<CompraDTO> produtos) {
        return produtos.stream()
                .filter(produto -> produtoService.validateProduct(produto.getProductNome()))
                .map(produto -> produtoService.getProductByName(produto.getProductNome()))
                .collect(Collectors.toList());
    }

    public Pedido getPedidoById(Long id) {
       return pedidoRepository.getById(id);
    }
}
