package com.vernicolor.app_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MaterialDTO {
    private String name;
    private String description;
    private float quantity ;
    private Long productFamilyId ;
}
