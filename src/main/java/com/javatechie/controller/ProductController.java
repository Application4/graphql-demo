package com.javatechie.controller;

import com.javatechie.entity.Product;
import com.javatechie.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @QueryMapping
    public Product getProductById(@Argument int id) {
        return productService.getProductById(id);

    }

    @QueryMapping
    public List<Product> getProductsByCategory(@Argument String category) {
        return productService.getProductsByCategory(category);

    }

    @QueryMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();

    }

    @MutationMapping
    public Product updateStock(@Argument int id, @Argument Integer stock) {
        return productService.updateStock(id, stock);

    }

    @MutationMapping
    public Product placeOrder(@Argument int id, @Argument Integer quantity) {
        return productService.placeOrder(id, quantity);

    }

    @MutationMapping
    public Product receiveShipment(@Argument int id, @Argument Integer quantity) {
        return productService.receiveShipment(id, quantity);

    }
}
