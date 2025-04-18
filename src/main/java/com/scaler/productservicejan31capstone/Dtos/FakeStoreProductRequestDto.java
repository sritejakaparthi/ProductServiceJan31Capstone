package com.scaler.productservicejan31capstone.Dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductRequestDto
{
    String title;
    double price;
    String description;
    String category;
    String image;
}