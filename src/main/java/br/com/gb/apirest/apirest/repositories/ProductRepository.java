package br.com.gb.apirest.apirest.repositories;

import br.com.gb.apirest.apirest.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
