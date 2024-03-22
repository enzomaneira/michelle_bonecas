package com.maneira.mongoproject.demo.repository;

import com.maneira.mongoproject.demo.domain.Order;
import com.maneira.mongoproject.demo.dto.OrderDTO;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findAll();
    List<Order> findById();
    List<Order> findByTotalBetween(Double minTotal, Double maxTotal);
    @Query("{ $and: [ " +
            "{ date: { $gte: ?1, $lte: ?2 } }, " +
            "{ total: { $gte: ?3, $lte: ?4 } }, " +
            "{ $and: [ " +
            "{ 'client.name': { $regex: ?5, $options: 'i' } }, " +
            "{ 'items.product.name': { $regex: ?6, $options: 'i' } }" +
            "] }" +
            "] }")
    List<Order> fullSearch(String text, Date minDate, Date maxDate, Double minTotal, Double maxTotal, String client, String product, Sort sort);
}
