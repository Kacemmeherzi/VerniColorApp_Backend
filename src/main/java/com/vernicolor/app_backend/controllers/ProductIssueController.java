package com.vernicolor.app_backend.controllers;

import com.vernicolor.app_backend.models.ProductIssue;
import com.vernicolor.app_backend.services.ProductIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product-issues")
public class ProductIssueController {

    @Autowired
    private ProductIssueService productIssueService;

    // Get all product issues
    @GetMapping
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
    @GetMapping("/product/{productId}")
    public List<ProductIssue> getIssuesByProductId(@PathVariable Long productId) {
        return productIssueService.getIssuesByProductId(productId);
    }

    // Create a new product issue
    @PostMapping
    public ProductIssue createProductIssue(@RequestBody ProductIssue productIssue ) {
        return productIssueService.saveProductIssue(productIssue);
    }

    // Delete a product issue by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductIssue(@PathVariable Long id) {
        productIssueService.deleteProductIssue(id);
        return ResponseEntity.noContent().build();
    }
}
