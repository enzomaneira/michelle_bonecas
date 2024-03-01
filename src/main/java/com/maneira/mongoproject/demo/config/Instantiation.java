package com.maneira.mongoproject.demo.config;

import com.maneira.mongoproject.demo.domain.Client;
import com.maneira.mongoproject.demo.domain.Order;
import com.maneira.mongoproject.demo.domain.OrderItem;
import com.maneira.mongoproject.demo.domain.Product;
import com.maneira.mongoproject.demo.repository.ClientRepository;
import com.maneira.mongoproject.demo.repository.OrderRepository;
import com.maneira.mongoproject.demo.repository.ProductRepository;
import com.maneira.mongoproject.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Override
    public void run(String... arg0) throws Exception {

        clientRepository.deleteAll();
        productRepository.deleteAll();
        orderRepository.deleteAll();

        Client maria = new Client(null, "Maria Brown", "maria@gmail.com");
        Client alex = new Client(null, "Alex Green", "alex@gmail.com");
        Client bob = new Client(null, "Bob Grey", "bob@gmail.com");

        Product p1 = new Product(null, "Cinderela", 50.00, "https://example.com/cinderela.jpg");
        Product p2 = new Product(null, "Batman", 35.99, "https://example.com/batman.jpg");
        Product p3 = new Product(null, "Elsa", 45.50, "https://example.com/elsa.jpg");
        Product p4 = new Product(null, "Spider-Man", 29.99, "https://example.com/spiderman.jpg");
        Product p5 = new Product(null, "Mickey Mouse", 20.00, "https://example.com/mickey-mouse.jpg");

        clientRepository.saveAll(Arrays.asList(maria, alex, bob));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        OrderItem orderItem1 = new OrderItem(null, p1,  2, 25.0);
        OrderItem orderItem2 = new OrderItem(null, p4,  1, 35.99);

        Date orderDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01");
        Client orderClient = maria;
        Order order = new Order(null, orderDate, orderClient);

        order.getItems().add(orderItem1);
        order.getItems().add(orderItem2);


        orderRepository.save(order);
    }
}
