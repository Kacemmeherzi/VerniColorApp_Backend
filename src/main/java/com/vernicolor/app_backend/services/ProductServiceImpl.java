package com.vernicolor.app_backend.services;

import com.vernicolor.app_backend.models.Product;
import com.vernicolor.app_backend.models.ProductFamily;
import com.vernicolor.app_backend.repositories.ProductFamilyRepository;
import com.vernicolor.app_backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

   @Autowired
   private ProductRepository productRepository;


   @Override
   public Product saveProduct(Product product) {
     return productRepository.save(product); }


   @Override
   public Optional<Product> getProductById(Long id) {
      return productRepository.findById(id);
   }

   @Override
   public List<Product> getAllProducts() {
      return productRepository.findAll();
   }

   @Override
   public Product updateProduct(Long id, Product productDetails) {
      return productRepository.findById(id).map(product -> {
         product.setProductName(productDetails.getProductName());
         product.setProductDescription(productDetails.getProductDescription());
         product.setProductValidatedAt(productDetails.getProductValidatedAt());
         return productRepository.save(product);
      }).orElseThrow(() -> new RuntimeException("Product not found with id " + id));
   }

   @Override

   public void deleteProduct(Long id) {
      productRepository.deleteById(id);
   }

   @Override
   public List<Product> findProductsByFamilyId(Long productFamilyId) {
      return productRepository.findAllByProductFamilyIdOrderByProductFamily(productFamilyId);
   }
}
