package com.javatechie.service;

import com.javatechie.entity.Product;
import com.javatechie.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateStock(int id, Integer stock) {
        Product product = getProductById(id);
        product.setStock(stock);
        return productRepository.save(product);
    }

    public Product placeOrder(int id, Integer quantity) {
        Product product = getProductById(id);
        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }
        product.setStock(product.getStock() - quantity);
        //don't save here send kafka events to order fulfilment service
        return productRepository.save(product);
    }

    public Product receiveShipment(int id, Integer quantity) {
        Product product = getProductById(id);
        product.setStock(product.getStock() + quantity);
        return productRepository.save(product);
    }
}
