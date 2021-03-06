package com.ecommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.ProductDTO;

@Repository
public interface ProductDAO extends MongoRepository<ProductDTO, Integer>{
	

}
