package com.dev.rkul.productservice.thirdpartyclients.productservice.FakeStore;

import com.dev.rkul.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDto {

    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

}
