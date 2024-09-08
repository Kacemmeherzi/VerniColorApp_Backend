package com.vernicolor.app_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;


import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String productName ;
    private String productDescription ;

    private String productStatus ;
    private String productValidatedAt ;
    private String productCreatedAt ;
    @ManyToOne
    @JoinColumn(name = "product_family_id", nullable = false)
    private ProductFamily productFamily;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductIssue> issues;
}
