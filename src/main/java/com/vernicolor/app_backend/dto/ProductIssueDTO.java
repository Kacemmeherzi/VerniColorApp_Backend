package com.vernicolor.app_backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductIssueDTO {
    private long productId  ;
     private String type ;
    private String description ;
}
