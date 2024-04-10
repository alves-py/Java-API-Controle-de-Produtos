package com.example.controledeprodutos.services;

import com.example.controledeprodutos.model.Product;
import com.example.controledeprodutos.model.exception.ResourceNotFoundException;
import com.example.controledeprodutos.model.repository.ProductRepository;
import com.example.controledeprodutos.shared.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAll(){
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ModelMapper().map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> getProductById(Integer id){
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
            throw new ResourceNotFoundException("Produto id("+ id +") não encontrado! ");
        }

        //Converte optional de Produto em ProdutoDTO
        ProductDTO dto = new ModelMapper().map(product.get(), ProductDTO.class);
        return Optional.of(dto);
    }

    public ProductDTO addProduct(ProductDTO productDTO){
        productDTO.setId(null);
        Product product = new ModelMapper().map(productDTO, Product.class);
        product = productRepository.save(product);

        //Adicionando Id no DTO
        productDTO.setId(product.getId());

        return productDTO;
    }

    public void  deleteProduct(Integer id){
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível deletar o produto com id" + id + "Produto não encontrado!");
        }

        productRepository.deleteById(id);
    }
    public ProductDTO updateProduct(Integer id, ProductDTO productDTO){
        productDTO.setId(id);

        Product product = new ModelMapper().map(productDTO, Product.class);

        product = productRepository.save(product);

        return productDTO;
    }
}
