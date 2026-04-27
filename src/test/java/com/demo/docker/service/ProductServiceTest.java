package com.demo.docker.service;


import com.demo.docker.model.Product;
import com.demo.docker.model.ProductDTO;
import com.demo.docker.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;


    @Test
    @DisplayName("Should create product successfully")
    void shouldCreateAnewProductSuccessfully() {

        //Given
        ProductDTO productDTO = new ProductDTO("productTest");
        Product newProduct = new Product(1L, "productTest");
        given(productRepository.save(any(Product.class))).willReturn(newProduct);

        //When
        Product result = productService.addProduct(productDTO);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("productTest");

        then(productRepository).should().save(any(Product.class));
    }


    @Test
    @DisplayName("Should return ID Successfully")
    void shouldReturnProductSuccessfully() {

        //Given
        Product product = new Product(1L, "productTest");
        given(productRepository.findById(1L)).willReturn(Optional.of(product));

        //When
        Product result = productService.getProductById(1L);

        //Then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("productTest");

        then(productRepository).should(times(1)).findById(any(Long.class));
    }


    @Test
    @DisplayName("Should throw Exception For Invalid ID")
    void shouldThrowExceptionForInvalidId() {

        //Given
        Long invalidId = 1L;

        //When & Then
        assertThatThrownBy(() -> productService.getProductById(invalidId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Product Not Found By ID:" + invalidId);
    }

    @Test
    @DisplayName("Should return list of products")
    void shouldReturnListOfProducts() {

        //Given
        List<Product> products = List.of(new Product(1L, "productTest"), new Product(2L, "productTest2"));
        given(productRepository.findAll()).willReturn(products);

        //When
        List<Product> result = productService.getAll();

        //Then
        assertThat(result).isNotEmpty();
        assertThat(result).size().isEqualTo(2);


    }
}
