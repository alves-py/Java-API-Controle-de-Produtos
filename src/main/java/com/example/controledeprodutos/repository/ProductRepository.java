package com.example.controledeprodutos.repository;

import com.example.controledeprodutos.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private List<Product> products = new ArrayList<Product>();
    private Integer lastId = 0;

    /**
     * Metodo para retornar uma lista de produtos
     * @return Lista de produtos
     * */
    public List<Product> getAllProducts(){
        return products;
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu Id
     * @param id do produto que será localizado
     * @return Retorna um produto caso seja encontrado */
    public Optional<Product> getProductById(Integer id){
        return products
                .stream()
                .filter(product -> Objects.equals(product.getId(), id))
                .findFirst();

    }

    /**
     * Metodo para adicionar produto na lista.
     * @param product que será adicionado
     * @return retorna o produto que foi adicionado na lista.
     */
    public Product addProduct(Product product){
        lastId++;
        product.setId(lastId);
        products.add(product);
        return product;
    }

    /**
     * Metodo para deletar o produto por id.
     * @param id do produto a ser deletado.
     */
    public void deleteProduct(Integer id){
        products.removeIf(product -> Objects.equals(product.getId(), id));
    }

    /**
     * Metodo para atualizar o produto na lista
     * @param product que será atualizado
     * @return o produto após atualizar o produto
     */
    public Product updateProduct(Product product){
        var productFound = getProductById(product.getId());

        if(productFound.isEmpty()){
            throw new InputMismatchException("Produto não encontrado");
        }

        deleteProduct(product.getId());

        products.add(product);

        return product;
    }
}
