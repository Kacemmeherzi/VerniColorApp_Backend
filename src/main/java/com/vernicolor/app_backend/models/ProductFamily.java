package com.vernicolor.app_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class ProductFamily {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String Name;
    private String Description;
    private String Quantity ;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @ManyToMany
    @JoinTable(
            name = "product_family_material",
            joinColumns = @JoinColumn(name = "product_family_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id")
    )
    private List<Material> materials;
}
