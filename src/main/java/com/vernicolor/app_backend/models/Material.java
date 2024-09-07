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
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private float quantity ;

    @ManyToMany(mappedBy = "materials")
    private List<ProductFamily> productFamilies;

}
