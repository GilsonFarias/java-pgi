package com.company.pgi.repository.product;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.pgi.model.Products;

public interface IProductsRepository extends JpaRepository<Products, Long> {

    // List<Products> getProdutcts();

    // Products saveProducts(Products products);
}
