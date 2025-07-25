package com.ecom.productservice.service;

import com.ecom.productservice.model.Product;
import com.ecom.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("ID should not be provided for creating a new product.");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required.");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0.");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existing = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required.");
        }
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0.");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stock cannot be negative.");
        }
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());
        return productRepository.save(existing);
    }

    public Product patchProduct(Long id, Product product) {
        Product existing = productRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
        // Update only non-null fields
        if (product.getName() != null) {
            existing.setName(product.getName());
        }
        if (product.getPrice() > 0) { // Allow 0 for no change, > 0 for valid update
            existing.setPrice(product.getPrice());
        }
        if (product.getStock() >= 0) { // Allow 0 for no change, >= 0 for valid update
            existing.setStock(product.getStock());
        }
        return productRepository.save(existing);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}