package com.dev.rkul.productservice.services;

import com.dev.rkul.productservice.dtos.GenericProductDto;
import com.dev.rkul.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductServiceImpl")
public class selfServiceImpl implements ProductService{

    @Override
    public GenericProductDto getProductById(Long id){
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto productDto) {
        return null;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return null;
    }

}
