package com.dev.rkul.productservice.thirdpartyclients.productservice.FakeStore;

import com.dev.rkul.productservice.dtos.GenericProductDto;
import com.dev.rkul.productservice.exceptions.NotFoundException;
import com.dev.rkul.productservice.thirdpartyclients.productservice.ThirdPartyProductServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 Wrapper over the fakestore api
*/


@Service
public class FakeStoreProductServiceClient implements ThirdPartyProductServiceClient {
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    private String getAllProductsRequestUrl = "https://fakestoreapi.com/products";



    @Value("${}")
    private String fakeStoreAPIUrl;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
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
    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {

        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        if(fakeStoreProductDto ==  null){
            throw new NotFoundException("Id is not found");
        }
        return fakeStoreProductDto;

    }

    @Override
    public List<FakeStoreProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response =
                restTemplate.getForEntity(getAllProductsRequestUrl, FakeStoreProductDto[].class);

        List<FakeStoreProductDto> answer  = new ArrayList<>();

        Collections.addAll(answer, response.getBody());

        return answer;

    }

    @Override
    public FakeStoreProductDto createProduct(GenericProductDto productDto){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                createProductRequestUrl, productDto, FakeStoreProductDto.class
        );

        return response.getBody();
    }

    @Override
    public FakeStoreProductDto deleteProduct(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(getProductRequestUrl, HttpMethod.DELETE,requestCallback,responseExtractor, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        return fakeStoreProductDto;
    }
}
