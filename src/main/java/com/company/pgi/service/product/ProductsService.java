package com.company.pgi.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.pgi.model.Products;
import com.company.pgi.repository.product.IProductsRepository;

@Service
public class ProductsService implements IProductsService {

    @Autowired
    private IProductsRepository productsRepository;

    @Override
    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    }
    
    @Override
    public Optional<Products> getProductsById(Long id) {
        return productsRepository.findById(id);
    }

    @Override
    public Products saveProducts(Products product) {
        return productsRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productsRepository.deleteById(id);
    }

}
