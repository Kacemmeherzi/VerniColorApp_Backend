package com.vernicolor.app_backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private long id;
    private String productName;
    private String productDescription;
    private Date productValidatedAt;
    private long productFamily;


}
