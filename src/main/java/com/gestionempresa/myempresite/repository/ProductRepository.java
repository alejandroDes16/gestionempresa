package com.gestionempresa.myempresite.repository;

import com.gestionempresa.myempresite.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByRef(String ref);

    boolean existsByRef(String ref);

    @Query(value = "SELECT * FROM products p WHERE p.provider_id =:id", nativeQuery = true)
    List<Product> findAllProducts(Integer id);
}
