package com.gestionempresa.myempresite.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "providers", uniqueConstraints = {@UniqueConstraint(columnNames = {"nif"})})
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String nif;
    private String address;
    private String numberPhone;
    private String personContact;
    private String emailContact;

    @OneToMany(mappedBy = "provider",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products = new HashSet<>();

    public Provider() {
    }

    public Provider(Integer id, String name, String nif, String address, String numberPhone, String personContact, String emailContact) {
        this.id = id;
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.numberPhone = numberPhone;
        this.personContact = personContact;
        this.emailContact = emailContact;
    }

    public Provider(String name, String nif, String address, String numberPhone, String personContact, String emailContact) {
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.numberPhone = numberPhone;
        this.personContact = personContact;
        this.emailContact = emailContact;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getPersonContact() {
        return personContact;
    }

    public void setPersonContact(String personContact) {
        this.personContact = personContact;
    }

    public String getEmailContact() {
        return emailContact;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
        for(Product product : products){
            product.setProvider(this);
        }
    }
}
