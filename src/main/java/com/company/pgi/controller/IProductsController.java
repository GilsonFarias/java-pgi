package com.company.pgi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.company.pgi.model.Products;

public interface IProductsController {
    List<Products> getAllProducts();
    ResponseEntity<Products> getProductById(Long id);
    Products createProduct(Products product);
    ResponseEntity<Products> updateProduct(Long id, Products product);
    ResponseEntity<Void> deleteProduct(Long id);
}
