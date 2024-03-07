package com.maneira.mongoproject.demo.repository;

import com.maneira.mongoproject.demo.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Aggregation;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findAll();

    List<Order> findById();

    @Query("{ $and: [ " +
            "{ date: { $gte: ?1, $lte: ?2 } }, " +
            "{ total: { $gte: ?3, $lte: ?4 } }, " +
            "{ $and: [ " +
            "{ 'client.name': { $regex: ?5, $options: 'i' } }, " +
            "{ 'items.product.name': { $regex: ?6, $options: 'i' } }" +
            "] }" +
            "] }")
    List<Order> fullSearch(String text, Date minDate, Date maxDate, Double minTotal, Double maxTotal, String client, String product);

    @Query("{ 'client.name' : { $regex: ?0, $options: 'i' } }")
    List<Order> findByClientNameIgnoreCase(String clientName);

    @Query("{ 'items.product.name' : { $regex: ?0, $options: 'i' } }")
    List<Order> findByItemsProductNameIgnoreCase(String productName);

    @Query("{ 'date': { $gte: ?0, $lte: ?1 } }")
    List<Order> findByDateRange(Date startDate, Date endDate);

    List<Order> findByTotalBetween(Double minTotal, Double maxTotal);

}
