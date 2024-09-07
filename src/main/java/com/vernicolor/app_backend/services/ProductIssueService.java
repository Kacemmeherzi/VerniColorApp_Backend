package com.vernicolor.app_backend.services;

import com.vernicolor.app_backend.models.ProductIssue;
import com.vernicolor.app_backend.repositories.ProductIssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductIssueService {

    @Autowired
    private ProductIssueRepository productIssueRepository;

    public List<ProductIssue> getAllProductIssues() {
        return productIssueRepository.findAll();
    }

    public Optional<ProductIssue> getProductIssueById(Long id) {
        return productIssueRepository.findById(id);
    }

    public List<ProductIssue> getIssuesByProductId(Long productId) {
        return productIssueRepository.findByProductId(productId);
    }

    public ProductIssue saveProductIssue(ProductIssue productIssue) {
        return productIssueRepository.save(productIssue);
    }

    public void deleteProductIssue(Long id) {
        productIssueRepository.deleteById(id);
    }
}

