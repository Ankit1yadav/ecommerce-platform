package com.ecom.productservice.service;

import com.ecom.productservice.model.Product;
import com.ecom.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    List<Product> productList = new ArrayList<>();

    public List<Product> getAllProducts() {

        return productList;
    }

    public Optional<Product> getProductById(int id) {

        return Optional.ofNullable(productList.get(id));
    }

    public boolean createProduct(Product product) {
        return productList.add(product);
    }

    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }
}