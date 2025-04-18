package com.scaler.productservicejan31capstone.services;

import com.scaler.productservicejan31capstone.Dtos.FakeStoreProductDto;
import com.scaler.productservicejan31capstone.Dtos.FakeStoreProductRequestDto;
import com.scaler.productservicejan31capstone.Exceptions.ProductNotFoundException;
import com.scaler.productservicejan31capstone.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService
{
    RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject(
                        "https://fakestoreapi.com/products/" + id,
                         FakeStoreProductDto.class);
        if(fakeStoreProductDto == null)
        {
            throw new ProductNotFoundException("The product for id " + id + " does not exist");
        }
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto[] fakeStoreProductDtos =  restTemplate.getForObject ("https://fakestoreapi.com/products",FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(fakeStoreProductDto.toProduct());

        }
        return products;
    }

    @Override
    public Product createProduct(String name, String description, String category, double price, String imageUrl) {

        FakeStoreProductRequestDto fakeStoreProductRequestDto = new FakeStoreProductRequestDto();
        fakeStoreProductRequestDto.setTitle(name);
        fakeStoreProductRequestDto.setDescription(description);
        fakeStoreProductRequestDto.setCategory(category);
        fakeStoreProductRequestDto.setPrice(price);
        fakeStoreProductRequestDto.setImage(imageUrl);


        FakeStoreProductDto fakeStoreProductDto = restTemplate.postForObject("https://fakestoreapi.com/products",fakeStoreProductRequestDto,FakeStoreProductDto.class);
        //System.out.println(fakeStoreProductDto.getId());
        return fakeStoreProductDto.toProduct();
    }
}
