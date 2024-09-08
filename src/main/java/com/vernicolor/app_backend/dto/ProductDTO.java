package com.vernicolor.app_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductDTO {
    private long id;
    private String productName;
    private String productDescription;
    @NotNull
    private String productStatus ;
    private long productFamily;


}
