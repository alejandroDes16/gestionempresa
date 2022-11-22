package com.gestionempresa.myempresite.controller;

import com.gestionempresa.myempresite.dto.Message;
import com.gestionempresa.myempresite.dto.ProductDto;
import com.gestionempresa.myempresite.dto.ProviderDto;
import com.gestionempresa.myempresite.entity.Product;
import com.gestionempresa.myempresite.entity.Provider;
import com.gestionempresa.myempresite.service.ProductService;
import com.gestionempresa.myempresite.service.ProviderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    ProviderService providerService;

    @Autowired
    ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<List<Product>> list(){
        List<Product> list = productService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Product> getById (@PathVariable ("id") Integer id){
        if(!productService.existsById(id)){
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        }
        Product product = productService.getOne(id).get();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/allProduct/{id}")
    public ResponseEntity<List<Product>> getAllById (@PathVariable("id") Integer id){
        if(!providerService.existsById(id)){
            return new ResponseEntity(new Message("El proveedor no existe"), HttpStatus.NOT_FOUND);
        }
        List<Product> productList = productService.getAllProducts(id);

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductDto productDto){
        if (!providerService.existsById(productDto.getProvider().getId())){
            return new ResponseEntity(new Message("El proveedor no existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(productDto.getName())){
            return new ResponseEntity(new Message("El nombre del producto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(productDto.getRef())){
            return new ResponseEntity(new Message("La referencia es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if(productDto.getPrecio().isNaN() || productDto.getPrecio() < 0){
            return new ResponseEntity(new Message("El precio no puede ser inferior a cero"), HttpStatus.BAD_REQUEST);
        }
        if(productService.existsByRef(productDto.getRef())){
            return new ResponseEntity(new Message("La referencia ya existe en el sistema"), HttpStatus.BAD_REQUEST);
        }

        Product newProduct = new Product(
                productDto.getName(),
                productDto.getDescription(),
                productDto.getRef(),
                productDto.getPrecio(),
                productDto.getProvider());

        productService.save(newProduct);

        return new ResponseEntity(new Message("Producto registrado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody ProductDto productDto){
        if (!providerService.existsById(productDto.getProvider().getId())){
            return new ResponseEntity(new Message("El proveedor no existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(productDto.getName())){
            return new ResponseEntity(new Message("El nombre del producto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(productDto.getRef())){
            return new ResponseEntity(new Message("La referencia es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if(productService.existsByRef(productDto.getRef()) && productService.getByRef(productDto.getRef()).get().getId() != id){
            return new ResponseEntity(new Message("La referencia ya existe en el sistema"), HttpStatus.BAD_REQUEST);
        }
        if(productDto.getPrecio(). || productDto.getPrecio() < 0){
            return new ResponseEntity(new Message("El precio no puede ser inferior a cero"), HttpStatus.BAD_REQUEST);
        }

        Product newProduct = productService.getOne(id).get();
        newProduct.setName(productDto.getName());
        newProduct.setDescription(productDto.getDescription());
        newProduct.setRef(productDto.getRef());
        newProduct.setPrecio(productDto.getPrecio());
        newProduct.setProvider(productDto.getProvider());

        productService.save(newProduct);

        return new ResponseEntity(new Message("Producto actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") Integer id){
        if(!productService.existsById(id)) {
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        }
        productService.delete(id);
        return new ResponseEntity(new Message("Producto eliminado"), HttpStatus.OK);

    }




}
