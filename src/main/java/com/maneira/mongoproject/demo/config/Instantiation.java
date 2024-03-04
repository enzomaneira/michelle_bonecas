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

        OrderItem orderItem1 = new OrderItem(p1, 2, 25.0);
        OrderItem orderItem2 = new OrderItem(p4, 1, 35.99);
        Date orderDate1 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-01-01");
        Client orderClient1 = maria;
        Order order1 = new Order(null, orderDate1, orderClient1, null);
        order1.getItems().add(orderItem1);
        order1.getItems().add(orderItem2);
        order1.updateTotal();
        orderRepository.save(order1);


        OrderItem orderItem3 = new OrderItem(p2,  3, 30.0);
        OrderItem orderItem4 = new OrderItem(p3,  1, 45.50);
        Date orderDate2 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-02-15");
        Client orderClient2 = alex;
        Order order2 = new Order(null, orderDate2, orderClient2, null);
        order2.getItems().add(orderItem3);
        order2.getItems().add(orderItem4);
        order2.updateTotal();
        orderRepository.save(order2);


        OrderItem order3Item1 = new OrderItem(p2, 3, 15.0);
        OrderItem order3Item2 = new OrderItem(p3, 1, 45.50);

        Date order3Date = new SimpleDateFormat("yyyy-MM-dd").parse("2022-02-15");
        Client order3Client = alex;
        Order order3 = new Order(null, order3Date, order3Client, null);

        order3.getItems().add(order3Item1);
        order3.getItems().add(order3Item2);

        order3.updateTotal();
        orderRepository.save(order3);
    }
}
