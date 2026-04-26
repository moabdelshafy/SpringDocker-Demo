package com.demo.docker.controller;

import com.demo.docker.model.Product;
import com.demo.docker.model.ProductDTO;
import com.demo.docker.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAll();
    }

    @PostMapping("/add")
    public Product addProduct (@RequestBody ProductDTO product){
        return productService.addProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
}
