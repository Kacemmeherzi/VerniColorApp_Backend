package com.vernicolor.app_backend.controllers;


import com.vernicolor.app_backend.dto.AssociateDTO;
import com.vernicolor.app_backend.dto.ProdfamilyDTO;
import com.vernicolor.app_backend.dto.ProductDTO;
import com.vernicolor.app_backend.models.Customer;
import com.vernicolor.app_backend.models.ProductFamily;
import com.vernicolor.app_backend.services.CustomerService;
import com.vernicolor.app_backend.services.ProductFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prodfamily")
public class ProductFamilyController {
    @Autowired
    private ProductFamilyService productFamilyService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(productFamilyService.findAll());

    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProdfamilyDTO prodfamilyDTO) {
        ProductFamily productFamily = new ProductFamily();
        productFamily.setName(prodfamilyDTO.getName());
        productFamily.setDescription(prodfamilyDTO.getDescription());
        Customer customer = customerService.getCustomerById(prodfamilyDTO.getCustomerid());
        productFamily.setCustomer(customer);

        return new ResponseEntity<>(productFamilyService.save(productFamily), HttpStatus.CREATED);

    }
    @PostMapping("/associate")
    public ResponseEntity<ProductFamily> associateMaterialWithProductFamily(
           @RequestBody AssociateDTO associateDTO) {
        ProductFamily updatedProductFamily = productFamilyService.associateMaterialWithProductFamily(
                associateDTO.getProdfamid(),
                associateDTO.getMatid());
        return ResponseEntity.ok(updatedProductFamily);
    }


}
