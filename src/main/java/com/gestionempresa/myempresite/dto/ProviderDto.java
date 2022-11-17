package com.gestionempresa.myempresite.dto;

import javax.validation.constraints.NotBlank;

import com.gestionempresa.myempresite.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderDto {

    @NotBlank
    private String name;
    @NotBlank
    private String nif;
    private String address;
    private String numberPhone;
    private String personContact;
    private String emailContact;

    private Set<Product> products;

}
