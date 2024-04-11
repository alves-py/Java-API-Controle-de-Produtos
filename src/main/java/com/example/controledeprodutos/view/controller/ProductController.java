package com.example.controledeprodutos.view.controller;

import com.example.controledeprodutos.model.Product;
import com.example.controledeprodutos.services.ProductService;
import com.example.controledeprodutos.shared.ProductDTO;
import com.example.controledeprodutos.view.model.ProductRequest;
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
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest){
        ModelMapper mapper = new ModelMapper();

        ProductDTO productDTO = mapper.map(productRequest, ProductDTO.class);
        productDTO = productService.addProduct(productDTO);

        return new ResponseEntity<>(mapper.map(productDTO, ProductResponse.class), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> getProductById(@PathVariable Integer id){
        Optional<ProductDTO> productDTO = productService.getProductById(id);
        ProductResponse productResponse = new ModelMapper().map(productDTO.get(), ProductResponse.class);

        return new ResponseEntity<>(Optional.of(productResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Integer id){
        productService.deleteProduct(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable Integer id){
        ModelMapper mapper = new ModelMapper();
        ProductDTO productDTO = mapper.map(productRequest, ProductDTO.class);
        productDTO = productService.updateProduct(id, productDTO);

        return new ResponseEntity<>(
                mapper.map(productDTO, ProductResponse.class),
                HttpStatus.OK);
    }
}
