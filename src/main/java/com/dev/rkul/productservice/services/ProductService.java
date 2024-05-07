package com.dev.rkul.productservice.services;

import com.dev.rkul.productservice.exceptions.NotFoundException;
import com.dev.rkul.productservice.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {

    public GenericProductDto getProductById(Long id) throws NotFoundException;

    public List<GenericProductDto> getAllProducts();

    public GenericProductDto createProduct(GenericProductDto productDto);

    GenericProductDto deleteProduct(Long id);
}
