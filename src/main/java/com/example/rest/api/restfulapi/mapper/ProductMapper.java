package com.example.rest.api.restfulapi.mapper;

import com.example.rest.api.restfulapi.dto.ProductDto;
import com.example.rest.api.restfulapi.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toProductDto(Product entity);

    List<ProductDto> toProductDtos(List<Product> products);
}
