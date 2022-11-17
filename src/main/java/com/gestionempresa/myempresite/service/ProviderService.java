package com.gestionempresa.myempresite.service;

import com.gestionempresa.myempresite.dto.ProviderDto;
import com.gestionempresa.myempresite.entity.Provider;
import com.gestionempresa.myempresite.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    public List<Provider> list(){
        return providerRepository.findAll();
    }

    public Optional<Provider> getOne(int id){
        return providerRepository.findById(id);
    }

    public Optional<Provider> getByName(String name){
        return providerRepository.findByName(name);
    }

    public void save(Provider provider){
        providerRepository.save(provider);
    }

    public void delete(int id){
        providerRepository.deleteById(id);
    }

    public List<Provider> getAllMatch (String keyword){
        if(keyword != null){
            return providerRepository.findAllMatch(keyword);
        }
        return providerRepository.findAll();
    }

    public boolean existsById(int id){
        return providerRepository.existsById(id);
    }

    public boolean existsByName(String name){
        return providerRepository.existsByName(name);
    }

    public boolean existsByNif(String nif){
        return providerRepository.existsByNif(nif);
    }
}
