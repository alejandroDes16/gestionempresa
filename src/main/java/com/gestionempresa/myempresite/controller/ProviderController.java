package com.gestionempresa.myempresite.controller;

import com.gestionempresa.myempresite.dto.Message;
import com.gestionempresa.myempresite.dto.ProviderDto;
import com.gestionempresa.myempresite.entity.Provider;
import com.gestionempresa.myempresite.service.ProviderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/provider")
@CrossOrigin(origins = "http://localhost:4200")
public class ProviderController {

    @Autowired
    ProviderService providerService;

    @GetMapping("/list")
    public ResponseEntity<List<Provider>> list(){
        List<Provider> list = providerService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Provider> getById (@PathVariable ("id") int id){
        if(!providerService.existsById(id)){
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        }
        Provider provider = providerService.getOne(id).get();
        return new ResponseEntity<>(provider, HttpStatus.OK);
    }

    @GetMapping("/detailname/{name}")
    public ResponseEntity<Provider> getByName (@PathVariable ("name") String name){
        if(!providerService.existsByName(name)){
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        }
        Provider provider = providerService.getByName(name).get();
        return new ResponseEntity<>(provider, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProviderDto providerDto){
        if(StringUtils.isBlank(providerDto.getName())){
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(providerDto.getNif())){
            return new ResponseEntity(new Message("El número de identificación fiscal es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(providerService.existsByName(providerDto.getName())){
            return new ResponseEntity(new Message("El nombre de proveedor ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (providerService.existsByNif(providerDto.getNif())){
            return new ResponseEntity(new Message("El número de identificación fiscal del proveedor ya existe"), HttpStatus.BAD_REQUEST);
        }

        Provider newProvider = new Provider(
                providerDto.getName(),
                providerDto.getNif(),
                providerDto.getAddress(),
                providerDto.getPersonContact(),
                providerDto.getNumberPhone(),
                providerDto.getEmailContact());

        providerService.save(newProvider);
        return new ResponseEntity(new Message("Proveedor registrado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProviderDto providerDto){
        if(!providerService.existsById(id)){
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        }
        if(providerService.existsByName(providerDto.getName()) && providerService.getByName(providerDto.getName()).get().getId() != id){
            return new ResponseEntity(new Message("El nombre de proveedor ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(providerDto.getName())){
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(providerDto.getNif())){
            return new ResponseEntity(new Message("El número de identificación fiscal es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (providerService.existsByNif(providerDto.getNif())){
            return new ResponseEntity(new Message("El número de identificación fiscal del proveedor ya existe"), HttpStatus.BAD_REQUEST);
        }

        Provider newProvider = providerService.getOne(id).get();
        newProvider.setName(providerDto.getName());
        newProvider.setNif(providerDto.getNif());
        newProvider.setAddress(providerDto.getAddress());
        newProvider.setEmailContact(providerDto.getEmailContact());
        newProvider.setNumberPhone(providerDto.getNumberPhone());
        newProvider.setPersonContact(providerDto.getPersonContact());

        providerService.save(newProvider);
        return new ResponseEntity(new Message("Proveedor actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") int id){
        if(!providerService.existsById(id)) {
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        }
        providerService.delete(id);
        return new ResponseEntity(new Message("Proveedor eliminado"), HttpStatus.OK);

    }

}
