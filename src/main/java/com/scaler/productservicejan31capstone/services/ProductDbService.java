package com.scaler.productservicejan31capstone.services;

import com.scaler.productservicejan31capstone.Exceptions.ProductNotFoundException;
import com.scaler.productservicejan31capstone.models.Category;
import com.scaler.productservicejan31capstone.models.Product;
import com.scaler.productservicejan31capstone.repositories.CategoryRepository;
import com.scaler.productservicejan31capstone.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Primary
public class ProductDbService implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    ProductDbService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product for id " + id + " not found");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String name, String description, String category, double price, String imageUrl) {

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImageUrl(imageUrl);
        product.setPrice(price);

        Category categoryObj = getCategoryFromDb(category);
        categoryObj.setName(category);
        product.setCategory(categoryObj);
        return productRepository.save(product);

    }

    private Category getCategoryFromDb(String name)
    {
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        if(optionalCategory.isPresent())
        {
            return optionalCategory.get();
        }
        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }
}
