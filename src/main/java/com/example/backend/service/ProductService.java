package com.example.backend.service;

import com.example.backend.entity.Product;
import com.example.backend.repository.ProductRepository;
import com.example.backend.dto.ProductDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(String name, Product.Category category, Integer minPrice, Integer maxPrice) {
        return productRepository.searchProducts(name, category, minPrice, maxPrice);
    }
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }
    public Product createProduct(ProductDTO product) {
        Product productEntity = new Product();
        productEntity.setName(product.getName());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setImage(product.getImage());
        productEntity.setStockQuantity(product.getStockQuantity());
        productEntity.setCategory(product.getCategory());
        return productRepository.save(productEntity);
    }
    public Product updateProduct(Integer id, ProductDTO productData) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        product.setName(productData.getName());
        product.setDescription(productData.getDescription());
        product.setPrice(productData.getPrice());
        product.setImage(productData.getImage());
        product.setStockQuantity(productData.getStockQuantity());
        product.setCategory(productData.getCategory());
        return productRepository.save(product);
    }
    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(product);
    }
}