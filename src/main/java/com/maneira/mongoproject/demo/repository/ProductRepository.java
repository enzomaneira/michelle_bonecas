package com.maneira.mongoproject.demo.repository;

import com.maneira.mongoproject.demo.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
