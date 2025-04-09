package com.example.demo.service;

import com.example.demo.dto.ProdutoDTO;
import com.example.demo.entities.Produto;
import com.example.demo.enums.DemoApiCodeEnum;
import com.example.demo.exception.ApiException;
import com.example.demo.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto createProduct (ProdutoDTO produtoDTO) {
        if (produtoRepository.getProductByName(produtoDTO.getNome()).isPresent()) {
            throw new ApiException(DemoApiCodeEnum.API_ERROR_EXISTENT_PRODUCT, "Produto já cadastrado. Tente novamente");
        }


        Produto produto = Produto.builder()
                .nome(produtoDTO.getNome())
                .valor(produtoDTO.getValor())
                .quantidade(50)
                .build();

        produtoRepository.save(produto);
        return produto;
    }

    public void deleteProduct(Long id) {
        try {
            produtoRepository.deleteById(id);
        } catch (Exception e) {
            throw new ApiException(DemoApiCodeEnum.API_ERROR_COULDNT_DELETE_PRODUCT, "Não foi possível deletar o produto.");
        }
    }

    public Produto getProductById(Long id) {
        return produtoRepository.getProductById(id).orElseThrow(() -> new ApiException(DemoApiCodeEnum.API_ERROR_PRODUCT_DOESNT_EXIST, "Produto não existe"));
    }

    public List<Produto> getAllProducts() {
        return produtoRepository.getAllProducts();
    }

    public boolean validateProduct(String name) {
        return produtoRepository.getProductByName(name).isPresent();
    }

    public BigDecimal getProductPriceByName(String name) {
        return produtoRepository.getProductByName(name).orElseThrow(() -> new ApiException(DemoApiCodeEnum.API_ERROR_PRODUCT_DOESNT_EXIST, "Produto não existe")).getValor();
    }

    public Produto getProductByName(String name) {
        return produtoRepository.getProductByName(name).orElseThrow(() -> new ApiException(DemoApiCodeEnum.API_ERROR_PRODUCT_DOESNT_EXIST, "Produto não existe"));

    }

}
