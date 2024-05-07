package com.dev.rkul.productservice.thirdpartyclients.productservice;

import com.dev.rkul.productservice.dtos.GenericProductDto;
import com.dev.rkul.productservice.exceptions.NotFoundException;
import com.dev.rkul.productservice.thirdpartyclients.productservice.FakeStore.FakeStoreProductDto;

import java.util.List;

public interface ThirdPartyProductServiceClient {

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException;

    public List<FakeStoreProductDto> getAllProducts();

    public FakeStoreProductDto createProduct(GenericProductDto productDto);

    FakeStoreProductDto deleteProduct(Long id);
}
