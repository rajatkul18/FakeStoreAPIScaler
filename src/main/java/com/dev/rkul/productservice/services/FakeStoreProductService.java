package com.dev.rkul.productservice.services;

import com.dev.rkul.productservice.exceptions.NotFoundException;
import com.dev.rkul.productservice.thirdpartyclients.productservice.FakeStore.FakeStoreProductDto;
import com.dev.rkul.productservice.dtos.GenericProductDto;
import com.dev.rkul.productservice.thirdpartyclients.productservice.FakeStore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    private GenericProductDto convertFakeStoreProductDtoToGeneric(FakeStoreProductDto fakeStoreProductDto){

        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());

        return product;
    }


    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return convertFakeStoreProductDtoToGeneric(fakeStoreProductServiceClient.getProductById(id));

    }

    @Override
    public List<GenericProductDto> getAllProducts(){

        List<GenericProductDto> list = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto: fakeStoreProductServiceClient.getAllProducts()
             ) {
            list.add(convertFakeStoreProductDtoToGeneric(fakeStoreProductDto));
        }
        return list;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto productDto){
        return convertFakeStoreProductDtoToGeneric(fakeStoreProductServiceClient.createProduct(productDto));
    }

    @Override
    public GenericProductDto deleteProduct(Long id){
        return convertFakeStoreProductDtoToGeneric(fakeStoreProductServiceClient.deleteProduct(id));
    }
}
