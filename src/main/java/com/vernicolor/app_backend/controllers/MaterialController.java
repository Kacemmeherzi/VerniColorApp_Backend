package com.vernicolor.app_backend.controllers;

import com.vernicolor.app_backend.models.Material;
import com.vernicolor.app_backend.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @PostMapping("create")
    public ResponseEntity<Material> create(@RequestBody Material material) {
        Material savedMaterial = materialService.saveMaterial(material);
        return ResponseEntity.ok(savedMaterial);
    }

    // Get all Materials
    @GetMapping("getall")
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    // Get Material by ID
    @GetMapping("/getone/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable Long id) {
        Optional<Material> material = materialService.getMaterialById(id);
        return material.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete Material by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMaterialById(@PathVariable Long id) {
        materialService.deleteMaterialById(id);
        return ResponseEntity.noContent().build();
    }
}
