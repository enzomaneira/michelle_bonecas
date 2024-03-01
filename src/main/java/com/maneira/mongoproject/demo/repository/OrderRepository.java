package com.maneira.mongoproject.demo.repository;

import com.maneira.mongoproject.demo.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
