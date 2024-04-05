package com.maneira.mongoproject.demo.repository;

import com.maneira.mongoproject.demo.domain.Product;
import com.maneira.mongoproject.demo.domain.enums.ProductType;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    Product findByNumber(Integer number);
    @Query("{ $and: [ " +
            "{ name: { $regex: ?0, $options: 'i' } }, " +
            "{ price: { $gte: ?1, $lte: ?2 } }, " +
            "{ count: { $gte: ?3, $lte: ?4 } }, " +
            "{ countMoney: { $gte: ?5, $lte: ?6 } }, " +
            "{ releaseYear: { $gte: ?7, $lte: ?8 } }, " +
            "{ productType: { $regex: ?9, $options: 'i' } }" +
            "] }")
    List<Product> fullSearch(
            String name, Double minPrice, Double maxPrice, Integer minCount, Integer maxCount,
            Double minCountMoney, Double maxCountMoney, Integer minReleaseYear, Integer maxReleaseYear,
            String productType, Sort sort);
}

