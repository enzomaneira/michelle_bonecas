package com.maneira.mongoproject.demo.repository;

import com.maneira.mongoproject.demo.domain.Product;
import com.maneira.mongoproject.demo.domain.enums.ProductType;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    Product findByNumber(Integer number);
    List<Product> findByNameIgnoreCaseContainingAndPriceBetweenAndCountBetweenAndCountMoneyBetweenAndReleaseYearBetweenAndProductType(String name, Double minPrice, Double maxPrice, Integer minCount, Integer maxCount, Double minCountMoney, Double maxCountMoney, Integer minReleaseYear, Integer maxReleaseYear, ProductType productType,  Sort sort);
}

