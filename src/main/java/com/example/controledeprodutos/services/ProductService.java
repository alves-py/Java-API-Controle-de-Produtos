package com.example.controledeprodutos.services;

import com.example.controledeprodutos.model.Product;
import com.example.controledeprodutos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAllProducts();
    }

    public Optional<Product> getProductById(Integer id){
        return productRepository.getProductById(id);
    }

    public Product addProduct(Product product){
        return productRepository.addProduct(product);
    }

    public void  deleteProduct(Integer id){
        productRepository.deleteProduct(id);
    }
    public Product updateProduct(Integer id, Product product){
        product.setId(id);
        return productRepository.updateProduct(product);
    }
}
