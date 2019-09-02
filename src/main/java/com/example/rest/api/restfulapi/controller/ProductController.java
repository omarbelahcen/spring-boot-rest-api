package com.example.rest.api.restfulapi.controller;

import com.example.rest.api.restfulapi.dto.ProductDto;
import com.example.rest.api.restfulapi.mapper.ProductMapper;
import com.example.rest.api.restfulapi.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity getAllProducts(){
        List<ProductDto> productDtoList = productMapper.toProductDtos(productService.retrieveAllProducts());
        if(productDtoList == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products available! ");
        }
        return ResponseEntity.ok().body(productDtoList);
    }

    @GetMapping("/pageable")
    public ResponseEntity getPageableProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "30") int size){

        Page<ProductDto> productDtoPage = productService.retrievePageableProducts(page, size).map(productMapper::toProductDto);
        if(productDtoPage == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products available! ");
        }
        if(page > productDtoPage.getTotalPages()){
            throw new RuntimeException("Page number is higher than the total pages");
        }
        return ResponseEntity.ok().body(productDtoPage);

    }

}
