package com.gestionempresa.myempresite.dto;

import com.gestionempresa.myempresite.entity.Provider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String name;
    private String description;
    private String ref;
    private Provider provider;
}
