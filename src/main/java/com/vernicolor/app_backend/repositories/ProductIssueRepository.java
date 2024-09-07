package com.vernicolor.app_backend.repositories;

import com.vernicolor.app_backend.models.ProductIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductIssueRepository extends JpaRepository<ProductIssue, Long> {
    // Custom method to find issues by product ID
    List<ProductIssue> findByProductId(Long productId);
}
