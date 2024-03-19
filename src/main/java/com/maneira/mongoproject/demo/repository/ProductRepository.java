package com.maneira.mongoproject.demo.repository;

import com.maneira.mongoproject.demo.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findByNameIgnoreCaseContainingAndPriceBetweenAndCountBetween(String name, Double minPrice, Double maxPrice, Integer minCount, Integer maxCount);
}

