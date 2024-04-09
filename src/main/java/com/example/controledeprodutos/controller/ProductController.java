package com.example.controledeprodutos.controller;

import com.example.controledeprodutos.model.Product;
import com.example.controledeprodutos.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAll();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable Integer id){
        productService.deleteProduct(id);
        return  "Produto deletado com sucesso!";
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable Integer id){
        return productService.updateProduct(id, product);
    }
}
