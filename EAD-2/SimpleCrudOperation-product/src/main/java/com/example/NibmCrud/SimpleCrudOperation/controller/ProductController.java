package com.example.NibmCrud.SimpleCrudOperation.controller;



import com.example.NibmCrud.SimpleCrudOperation.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private List<Product> products = new ArrayList<>();
    private int currentId = 1;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        product.setId(currentId++);
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Product>> createMultipleProducts(@RequestBody List<Product> productList) {
        for (Product product : productList) {
            product.setId(currentId++);
            products.add(product);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productList);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> product = products.stream().filter(p -> p.getId().equals(id)).findFirst();
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product updatedProduct) {
        Optional<Product> product = products.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (product.isPresent()) {
            Product existingProduct = product.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setCategory(updatedProduct.getCategory());
            return ResponseEntity.ok(existingProduct);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        boolean removed = products.removeIf(product -> product.getId().equals(id));
        if (removed) {
            return ResponseEntity.ok("Product deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }

    @GetMapping("/filter")
    public List<Product> filterProductsByCategory(@RequestParam String category) {
        return products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}


