package com.vernicolor.app_backend.models;

import com.vernicolor.app_backend.models.ProductFamily;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
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
    private String productStatut ;

    @Temporal(TemporalType.DATE)
    private Date productValidatedAt ;
    @Temporal(TemporalType.DATE)
    private Date productCreatedAt ;
    @ManyToOne
    @JoinColumn(name = "product_family_id", nullable = false)
    private ProductFamily productFamily;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductIssue> issues;
}
