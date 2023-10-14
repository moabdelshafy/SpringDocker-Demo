package com.demo.docker.service;

import com.demo.docker.model.Product;
import com.demo.docker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(value = "products")
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product addProduct(Product product){
        return productRepository.save(product);
    }
}
