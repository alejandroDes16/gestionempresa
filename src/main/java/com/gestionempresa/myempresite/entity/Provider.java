package com.gestionempresa.myempresite.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @JsonBackReference
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products = new HashSet<>();

    public Provider(String name, String nif, String address, String numberPhone, String personContact, String emailContact) {
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.numberPhone = numberPhone;
        this.personContact = personContact;
        this.emailContact = emailContact;
    }
}
