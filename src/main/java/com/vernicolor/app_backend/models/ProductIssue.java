package com.vernicolor.app_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductIssue {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type ;
    private String description  ;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
