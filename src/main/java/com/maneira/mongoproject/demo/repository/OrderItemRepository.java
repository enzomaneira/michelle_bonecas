package com.maneira.mongoproject.demo.repository;

import com.maneira.mongoproject.demo.domain.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends MongoRepository<OrderItem, String> {
}
