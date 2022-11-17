package com.gestionempresa.myempresite.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "providers", uniqueConstraints = {@UniqueConstraint(columnNames = {"nif"})})
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String nif;
    private String address;
    private String numberPhone;
    private String personContact;
    private String emailContact;

    public Provider(String name, String nif, String address, String numberPhone, String personContact, String emailContact) {
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.numberPhone = numberPhone;
        this.personContact = personContact;
        this.emailContact = emailContact;
    }
}
