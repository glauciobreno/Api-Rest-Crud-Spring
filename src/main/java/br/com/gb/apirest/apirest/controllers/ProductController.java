package br.com.gb.apirest.apirest.controllers;

import br.com.gb.apirest.apirest.models.Product;
import br.com.gb.apirest.apirest.repositories.ProductRepository;
import br.com.gb.apirest.apirest.utils.ResponseHandler;
import jakarta.websocket.server.PathParam;
import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public List<Product> listar(){
        return productRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> obter(@PathVariable Integer id){
        Optional<Product> product = productRepository.findById(id);

        if(!product.isPresent()){
            return ResponseHandler.generate("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(product.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Product product){
        if (product.getName() == null){
            return ResponseHandler.generate("O nome do produto é obrigatorio", HttpStatus.BAD_REQUEST);
        }
        if (product.getPrice() == null){
            return ResponseHandler.generate("O Preço do produto é obrigatório", HttpStatus.BAD_REQUEST);
        }
        Product newProduct = productRepository.save(product);
        return new ResponseEntity<Object>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Product product){
        Optional<Product> oldProduct = productRepository.findById(id);

        if(!oldProduct.isPresent()){
            return ResponseHandler.generate("Produto não encontrado", HttpStatus.NOT_FOUND);
        }

        if (product.getName() == null){
            return ResponseHandler.generate("O nome do produto é obrigatorio", HttpStatus.BAD_REQUEST);
        }
        if (product.getPrice() == null){
            return ResponseHandler.generate("O Preço do produto é obrigatório", HttpStatus.BAD_REQUEST);
        }

        Product updateProduct = oldProduct.get();
        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setDescription(product.getDescription());

        productRepository.save(updateProduct);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        Optional<Product> oldProduct = productRepository.findById(id);

        if(!oldProduct.isPresent()){
            //retornar status 404
            return ResponseHandler.generate("Produto não encontrado", HttpStatus.NOT_FOUND);
        }

        productRepository.delete(oldProduct.get());
        return ResponseEntity.noContent().build();
    }
}
