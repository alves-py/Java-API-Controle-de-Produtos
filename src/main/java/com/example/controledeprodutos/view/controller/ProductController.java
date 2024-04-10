package com.example.controledeprodutos.view.controller;

import com.example.controledeprodutos.model.Product;
import com.example.controledeprodutos.services.ProductService;
import com.example.controledeprodutos.shared.ProductDTO;
import com.example.controledeprodutos.view.model.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductDTO> productDTOS = productService.getAll();
        ModelMapper mapper = new ModelMapper();

        List<ProductResponse> responses = productDTOS.stream()
                .map(productDTO -> mapper.map(productDTO, ProductResponse.class))
                .toList();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping
    public ProductDTO addProduct(@RequestBody ProductDTO productdto){
        return productService.addProduct(productdto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> getProductById(@PathVariable Integer id){
        Optional<ProductDTO> productDTO = productService.getProductById(id);
        ProductResponse productResponse = new ModelMapper().map(productDTO.get(), ProductResponse.class);

        return new ResponseEntity<>(Optional.of(productResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable Integer id){
        productService.deleteProduct(id);
        return  "Produto deletado com sucesso!";
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Integer id){
        return productService.updateProduct(id, productDTO);
    }
}
