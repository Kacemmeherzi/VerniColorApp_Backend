package com.vernicolor.app_backend.services;

import com.vernicolor.app_backend.models.Material;
import com.vernicolor.app_backend.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    // Create or Update a Material
    public Material saveMaterial(Material material) {
        return materialRepository.save(material);
    }

    // Read all Materials
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    // Read a Material by ID
    public Optional<Material> getMaterialById(Long id) {
        return materialRepository.findById(id);
    }

    // Delete a Material by ID
    public void deleteMaterialById(Long id) {
        materialRepository.deleteById(id);
    }
}
