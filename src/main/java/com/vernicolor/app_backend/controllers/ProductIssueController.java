package com.vernicolor.app_backend.controllers;

import com.vernicolor.app_backend.dto.ProductIssueDTO;
import com.vernicolor.app_backend.models.Product;
import com.vernicolor.app_backend.models.ProductIssue;
import com.vernicolor.app_backend.services.ProductIssueService;
import com.vernicolor.app_backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product-issues")
public class ProductIssueController {

    @Autowired
    private ProductIssueService productIssueService;
    @Autowired
    private ProductService productService;

    // Get all product issues
    @GetMapping("getall")
    public List<ProductIssue> getAllProductIssues() {
        return productIssueService.getAllProductIssues();
    }

    // Get product issue by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductIssue> getProductIssueById(@PathVariable Long id) {
        Optional<ProductIssue> productIssue = productIssueService.getProductIssueById(id);
        return productIssue.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get issues by product ID
    @GetMapping("/byproduct/{productId}")
    public List<ProductIssue> getIssuesByProductId(@PathVariable Long productId) {
        return productIssueService.getIssuesByProductId(productId);
    }

    // Create a new product issue
    @PostMapping("/create")
    public ProductIssue createProductIssue(@RequestBody ProductIssueDTO productIssueDTO ) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        Product product =  productService.findProductById(productIssueDTO.getProductId()) ;
        product.setProductValidatedAt(formatter.format(new Date()));
        productService.updateProduct(product.getId(),product) ;

        ProductIssue productIssue = new ProductIssue();
        productIssue.setType(productIssueDTO.getType());
        productIssue.setDescription(productIssueDTO.getDescription());
        productIssue.setProduct(productService.updateProduct(product.getId(),product)) ;


        return productIssueService.saveProductIssue(productIssue);
    }

    // Delete a product issue by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductIssue(@PathVariable Long id) {
        productIssueService.deleteProductIssue(id);
        return ResponseEntity.noContent().build();
    }
}
