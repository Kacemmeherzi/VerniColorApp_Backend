package com.vernicolor.app_backend.controllers;

import com.vernicolor.app_backend.dto.ProductDTO;
import com.vernicolor.app_backend.models.Product;
import com.vernicolor.app_backend.models.ProductFamily;
import com.vernicolor.app_backend.services.ProductFamilyService;
import com.vernicolor.app_backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private final ProductService productService;
    @Autowired
    private  final ProductFamilyService productFamilyService;
    public ProductController(ProductService productService, ProductFamilyService productFamilyService) {
        this.productService = productService;
        this.productFamilyService = productFamilyService;
    }

    @PostMapping("create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductValidatedAt(productDTO.getProductValidatedAt());
        ProductFamily productFamily =  productFamilyService.findById(productDTO.getProductFamily());
        product.setProductFamily(productFamily);
        productService.saveProduct(product);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        try {
            Product updatedProduct = productService.updateProduct(id, productDetails);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/byfamily/{productFamilyId}")
    public List<Product> getProductsByProductFamilyId(@PathVariable Long productFamilyId) {
        return productService.findProductsByFamilyId(productFamilyId);
    }
}
