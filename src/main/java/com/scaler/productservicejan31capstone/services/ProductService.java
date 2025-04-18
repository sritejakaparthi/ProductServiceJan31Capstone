package com.scaler.productservicejan31capstone.services;

import com.scaler.productservicejan31capstone.Exceptions.ProductNotFoundException;
import com.scaler.productservicejan31capstone.models.Product;
import java.util.List;

public interface ProductService
{
    Product getProductById(long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(String name, String description, String category, double price, String imageUrl);
}

