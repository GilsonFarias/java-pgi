package com.company.pgi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.pgi.model.Products;
import com.company.pgi.service.product.IProductsService;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    @Autowired
    private IProductsService iProductsService;
    

    @GetMapping("/list")
    public List<Products> getAllProducts() {
        return iProductsService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        Optional<Products> product = iProductsService.getProductsById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    

    @PostMapping
    public Products createProduct(@RequestBody Products product) {
        return iProductsService.saveProducts(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable Long id, @RequestBody Products product) {
        Optional<Products> existingProduct = iProductsService.getProductsById(id);
        if (existingProduct.isPresent()) {
            product.setId(id);
            return ResponseEntity.ok(iProductsService.saveProducts(product));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Products> existingProduct = iProductsService.getProductsById(id);
        if (existingProduct.isPresent()) {
            iProductsService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
