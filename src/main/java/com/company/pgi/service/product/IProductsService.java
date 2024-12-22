package com.company.pgi.service.product;

import java.util.List;
import java.util.Optional;

import com.company.pgi.model.Products;

public interface IProductsService {
    List<Products> getAllProducts();
    Optional<Products> getProductsById(Long id);
    Products saveProducts(Products products);
    void deleteProduct(Long id);
}
