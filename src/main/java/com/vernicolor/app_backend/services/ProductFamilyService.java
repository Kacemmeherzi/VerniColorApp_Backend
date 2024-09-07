package com.vernicolor.app_backend.services;

import com.vernicolor.app_backend.models.Material;
import com.vernicolor.app_backend.models.ProductFamily;
import com.vernicolor.app_backend.repositories.MaterialRepository;
import com.vernicolor.app_backend.repositories.ProductFamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductFamilyService {
    @Autowired
    private ProductFamilyRepository productFamilyRepository;
    @Autowired
    private MaterialRepository materialRepository;
    public List<ProductFamily> findAll() {


        return productFamilyRepository.findAll();
    };
    public ProductFamily save(ProductFamily productFamily) {
        return productFamilyRepository.save(productFamily);
    };
    public ProductFamily findById(long id) {
        return productFamilyRepository.findById(id).orElse(null)  ; }
    public void delete(long id) {
        productFamilyRepository.deleteById(id);
        
    }
    public ProductFamily associateMaterialWithProductFamily(Long productFamilyId, Long materialId) {
        Optional<ProductFamily> productFamilyOpt = productFamilyRepository.findById(productFamilyId);
        Optional<Material> materialOpt = materialRepository.findById(materialId);

        if (productFamilyOpt.isPresent() && materialOpt.isPresent()) {
            ProductFamily productFamily = productFamilyOpt.get();
            Material material = materialOpt.get();

            productFamily.getMaterials().add(material);
            material.getProductFamilies().add(productFamily);

            return productFamilyRepository.save(productFamily);
        } else {
            throw new RuntimeException("ProductFamily or Material not found");
        }
    }
}
