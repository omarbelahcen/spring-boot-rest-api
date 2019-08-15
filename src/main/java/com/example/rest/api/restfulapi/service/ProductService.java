package com.example.rest.api.restfulapi.service;

import com.example.rest.api.restfulapi.entity.Product;
import com.example.rest.api.restfulapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> retrieveAllProducts(){
        return productRepository.findAll();
    }

}
