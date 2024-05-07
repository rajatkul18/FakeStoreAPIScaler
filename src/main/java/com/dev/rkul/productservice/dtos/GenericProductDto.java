package com.dev.rkul.productservice.dtos;

import com.dev.rkul.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericProductDto {

    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;


}
