package com.demo.docker.controller;

import com.demo.docker.model.Product;
import com.demo.docker.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAll();
    }

    @PostMapping("/add")
    public Product addProduct (@RequestBody Product product){
        return productService.addProduct(product);
    }
}
