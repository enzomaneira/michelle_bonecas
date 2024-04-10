package com.maneira.mongoproject.demo.config;

import com.maneira.mongoproject.demo.repository.ClientRepository;
import com.maneira.mongoproject.demo.repository.OrderRepository;
import com.maneira.mongoproject.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public void run(String... arg0) throws Exception {

    }
}
