package com.soft.stock.services;

import org.springframework.stereotype.Service;

import com.soft.stock.entities.Product;

@Service
public interface ProductService {
    Iterable<Product> listAllProducts();
	Product getProductById(Integer id);
    Product saveProduct(Product product);
}