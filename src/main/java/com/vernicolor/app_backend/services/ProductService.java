package com.vernicolor.app_backend.services;

import com.vernicolor.app_backend.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);
    Optional<Product> getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product productDetails);
    void deleteProduct(Long id);
    List<Product> findProductsByFamilyId(Long productFamilyId);
}
