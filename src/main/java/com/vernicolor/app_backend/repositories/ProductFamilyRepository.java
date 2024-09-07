package com.vernicolor.app_backend.repositories;

import com.vernicolor.app_backend.models.ProductFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFamilyRepository  extends JpaRepository<ProductFamily,Long> {
}
