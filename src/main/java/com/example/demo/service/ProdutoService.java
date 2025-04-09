package com.example.demo.service;

import com.example.demo.dto.ProdutoDTO;
import com.example.demo.entities.Produto;
import com.example.demo.enums.DemoApiCodeEnum;
import com.example.demo.exception.ApiException;
import com.example.demo.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    private static final Integer DEFAULT_QUANTITY = 1;

    public Produto createProduct (ProdutoDTO produtoDTO) {
        if (produtoRepository.findByNome(produtoDTO.getNome()).isPresent()) {
            throw new ApiException(DemoApiCodeEnum.API_ERROR_EXISTENT_PRODUCT, "Produto já cadastrado. Tente novamente");
        }

        Produto produto = toEntity(produtoDTO);
        return produtoRepository.save(produto);
    }

    private static Produto toEntity(ProdutoDTO produtoDTO) {
        return Produto.builder()
                .nome(produtoDTO.getNome())
                .valor(produtoDTO.getValor())
                .quantidade(DEFAULT_QUANTITY)
                .build();
    }

    public void deleteProduct(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ApiException(DemoApiCodeEnum.API_ERROR_PRODUCT_DOESNT_EXIST, "Produto não existente na base de dados");
        }
        produtoRepository.deleteById(id);
    }

    public Produto getProductById(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new ApiException(DemoApiCodeEnum.API_ERROR_PRODUCT_DOESNT_EXIST, "Produto não existe"));
    }

    public List<Produto> getAllProducts(Pageable pageable) {
        return produtoRepository.findAll();
    }

    public boolean validateProduct(String name) {
        return produtoRepository.findByNome(name).isPresent();
    }

    public BigDecimal getProductPriceByName(String name) {
        return produtoRepository.findByNome(name).orElseThrow(() -> new ApiException(DemoApiCodeEnum.API_ERROR_PRODUCT_DOESNT_EXIST, "Produto não existe")).getValor();
    }

    public Produto getProductByName(String name) {
        return produtoRepository.findByNome(name).orElseThrow(() -> new ApiException(DemoApiCodeEnum.API_ERROR_PRODUCT_DOESNT_EXIST, "Produto não existe"));

    }

}
