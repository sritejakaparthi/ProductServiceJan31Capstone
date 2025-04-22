package com.scaler.productservicejan31capstone.Controllers;


import com.scaler.productservicejan31capstone.Dtos.CreateFakeStoreProductDto;
import com.scaler.productservicejan31capstone.Dtos.ErrorDto;
import com.scaler.productservicejan31capstone.Dtos.ProductResponseDto;
import com.scaler.productservicejan31capstone.Exceptions.ProductNotFoundException;
import com.scaler.productservicejan31capstone.models.Product;
import com.scaler.productservicejan31capstone.services.FakeStoreProductService;
import com.scaler.productservicejan31capstone.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable long id) throws ProductNotFoundException {
        Product product = productService.getProductById(id);
        ProductResponseDto productResponseDto = ProductResponseDto.from(product);

        ResponseEntity<ProductResponseDto> responseEntity = new ResponseEntity<>(productResponseDto, HttpStatus.OK);

        return responseEntity;
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts() {

        List<Product> products = productService.getAllProducts();

        List<ProductResponseDto> productResponseDto = new ArrayList<>();

        for (Product product : products) {
            productResponseDto.add(ProductResponseDto.from(product));
        }
        return productResponseDto;
    }
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody CreateFakeStoreProductDto createFakeStoreProductDto)
    {
        Product product = productService.createProduct(
                createFakeStoreProductDto.getName(),
                createFakeStoreProductDto.getDescription(),
                createFakeStoreProductDto.getCategory(),
                createFakeStoreProductDto.getPrice(),
                createFakeStoreProductDto.getImageUrl()
        );

        ProductResponseDto productResponseDto = ProductResponseDto.from(product);
        //System.out.println(productResponseDto.getName());
        return productResponseDto;
    }

    //    @ExceptionHandler(NullPointerException.class)
    //    public ErrorDto handleNullPointerException()
    //    {
    //        ErrorDto errorDto = new ErrorDto();
    //        errorDto.setStatus("Failure");
    //        errorDto.setMessage("NullPointer exception is occurred");
    //
    //        return errorDto;
    //    }
}
