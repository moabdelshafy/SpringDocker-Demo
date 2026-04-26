package com.demo.docker.service;

import com.demo.docker.model.Product;
import com.demo.docker.model.ProductDTO;
import com.demo.docker.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(value = "products", key = "'allProducts'")
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    @CacheEvict(value = "products", allEntries = true)
    public Product addProduct(ProductDTO productDTO){
        Product entity = new Product(productDTO.name());
        return productRepository.save(entity);
    }


    public Product getProductById(Long id){
       return productRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Product Not Found By ID:" + id));
    }
}
