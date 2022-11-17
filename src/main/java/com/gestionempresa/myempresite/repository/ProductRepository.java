package com.gestionempresa.myempresite.repository;

import com.gestionempresa.myempresite.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository {

    Optional<Product> findByRef(String ref);

    boolean existsByRef(String ref);

    Optional<Product> findByName(String name);



}
