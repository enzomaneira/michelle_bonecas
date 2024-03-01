package com.maneira.mongoproject.demo.service;

import com.maneira.mongoproject.demo.domain.Order;
import com.maneira.mongoproject.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    public List<Order> findAll() {
        return repo.findAll();
    }

    public Order findById(String id) {
        Optional<Order> obj = repo.findById(id);
        return obj.get();
    }
}
