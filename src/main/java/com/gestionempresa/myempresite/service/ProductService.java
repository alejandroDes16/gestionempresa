package com.gestionempresa.myempresite.service;

import com.gestionempresa.myempresite.entity.Product;
import com.gestionempresa.myempresite.entity.Provider;
import com.gestionempresa.myempresite.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> list(){

        return productRepository.findAll();
    }

    public Optional<Product> getOne(Integer id){

        return productRepository.findById(id);
    }

    public Optional<Product> getByRef(String ref){

        return productRepository.findByRef(ref);
    }

    public void save(Product product){

        productRepository.save(product);
    }

    public void delete(Integer id){

        productRepository.deleteById(id);
    }

    public boolean existsById(Integer id){
        return productRepository.existsById(id);
    }

    public boolean existsByRef(String ref){
        return productRepository.existsByRef(ref);
    }

    public List<Product> getAllProducts(Integer id){
        return productRepository.findAllProducts(id);
    }


}
