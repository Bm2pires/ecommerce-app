package com.ecommerce.spring.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ecommerce.spring.entities.Products;
import com.ecommerce.spring.entities.User;


public interface ProductRepository extends JpaRepository<Products, Long> {
	public 	Optional<Products> findByProductName(String name);
	public 	Optional<Products> findByProductDescription(String description);


}
