package com.example.rest.api.restfulapi.controller;

import com.example.rest.api.restfulapi.dto.ProductDto;
import com.example.rest.api.restfulapi.mapper.ProductMapper;
import com.example.rest.api.restfulapi.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtoList = productMapper.toProductDtos(productService.retrieveAllProducts());
        return ResponseEntity.ok().body(productDtoList);
    }

}
