package com.vernicolor.app_backend.controllers;

import com.vernicolor.app_backend.dto.ProductDTO;
import com.vernicolor.app_backend.models.Product;
import com.vernicolor.app_backend.models.ProductFamily;
import com.vernicolor.app_backend.services.ImageService;
import com.vernicolor.app_backend.services.ProductFamilyService;
import com.vernicolor.app_backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private final ProductService productService;
    @Autowired
    private  final ProductFamilyService productFamilyService;
    @Autowired
    private  final ImageService imageService;
    public ProductController(ProductService productService, ProductFamilyService productFamilyService, ImageService imageService) {
        this.productService = productService;
        this.productFamilyService = productFamilyService;
        this.imageService = imageService;
    }

    @PostMapping("create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        System.out.println(productDTO.toString());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductValidatedAt("not validated yet ");
        product.setProductStatus(productDTO.getProductStatus());
        product.setProductCreatedAt(formatter.format(new Date()));
        ProductFamily productFamily =  productFamilyService.findById(productDTO.getProductFamily());
        product.setProductFamily(productFamily);

        return new ResponseEntity<>( productService.saveProduct(product), HttpStatus.CREATED);
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
    @PostMapping("/createp")
    public ResponseEntity<Product> createProduct(
            @RequestParam("name") String name,
            @RequestParam("status") String status,
            @RequestParam("prodfamily") Long productFamilyId,
            @RequestParam("description") String description,
            @RequestParam("image") MultipartFile imageFile) {

        try {
            // 1. Save the image using ImageService and create folder if not exists
            String fileName = imageService.saveImage(imageFile);

            // 2. Generate the image URL
            String imageUrl = imageService.generateImageUrl(fileName);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");

            // 3. Create the product and set its image URL
            ProductFamily productFamily = productFamilyService.findById(productFamilyId);
            Product product = new Product();
            product.setProductName(name);
            product.setProductDescription(description);
            product.setImageUrl(imageUrl);
            product.setProductFamily(productFamily);
            product.setProductStatus(status);
            product.setProductCreatedAt(formatter.format(new Date()));
            Product savedProduct = productService.saveProduct(product);

            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

