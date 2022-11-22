package com.gestionempresa.myempresite.repository;

import com.gestionempresa.myempresite.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
    Optional<Provider> findByName(String name);
    boolean existsByName(String name);
    @Query("SELECT p FROM Provider p WHERE"
            + " CONCAT(p.id,p.name,p.nif,p.address, p.numberPhone, p.personContact, p.emailContact)"
            + " LIKE %?1%")

    List<Provider> findAllMatch(String keyword);

    Optional<Provider> findByNif(String nif);
    boolean existsByNif(String nif);

}
