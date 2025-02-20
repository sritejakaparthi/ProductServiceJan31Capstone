package com.scaler.productservicejan31capstone.Dtos;

import com.scaler.productservicejan31capstone.models.Category;
import com.scaler.productservicejan31capstone.models.Product;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FakeStoreProductDto
{
    private  long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;


    public Product toProduct()
    {
        Product product = new Product();
        product.setId(this.id);
        product.setDescription(this.description);
        product.setName(this.title);
        product.setPrice(this.price);
        product.setImageUrl(this.image);

        Category category1 = new Category();
        category1.setName(this.category);

        product.setCategory(category1);
        return product;
    }
}