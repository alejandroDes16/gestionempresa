package com.gestionempresa.myempresite.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
