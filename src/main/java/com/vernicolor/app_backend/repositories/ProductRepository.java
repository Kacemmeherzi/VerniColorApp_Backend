package com.vernicolor.app_backend.repositories;

import com.vernicolor.app_backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findAllByProductFamilyIdOrderByProductFamily(Long productFamilyId);

}
