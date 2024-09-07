package com.vernicolor.app_backend.repositories;

import com.vernicolor.app_backend.models.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
