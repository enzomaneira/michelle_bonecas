package com.maneira.mongoproject.demo.service;

import com.maneira.mongoproject.demo.domain.Client;
import com.maneira.mongoproject.demo.domain.Order;
import com.maneira.mongoproject.demo.dto.OrderDTO;
import com.maneira.mongoproject.demo.domain.OrderItem;

import com.maneira.mongoproject.demo.domain.Product;
import com.maneira.mongoproject.demo.dto.OrderItemDTO;
import com.maneira.mongoproject.demo.repository.OrderRepository;
import com.maneira.mongoproject.demo.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private ClientService clientService;

    public List<Order> findAll() {
        return repo.findAll();
    }

    public Order findById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }

        Optional<Order> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public Order insert(Order order) {
        return repo.insert(order);
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public List<Order> findByClient(String clientName) {
        return repo.findByClientNameIgnoreCase(clientName);
    }

    public List<Order> findByProduct(String productName) {
        return repo.findByItemsProductNameIgnoreCase(productName);
    }

    public List<Order> findOrdersByDateRange(Date startDate, Date endDate) {
        return repo.findByDateRange(startDate, endDate);
    }

    public List<Order> findByTotalRange(Double minTotal, Double maxTotal) {
        return repo.findByTotalBetween(minTotal, maxTotal);
    }

    public List<Order> fullSearch(String text, Date minDate, Date maxDate, Double minTotal, Double maxTotal, String client, String product) {
        return repo.fullSearch(text, minDate, maxDate, minTotal, maxTotal, client, product);
    }

    public Order save(Order order) {
        if (order.getClient() == null || order.getClient().getId() == null) {
            throw new IllegalArgumentException("A order must have a valid client associated with it.");
        }
        Client client = clientService.findById(order.getClient().getId());
        order.setClient(client);
        Double total = order.getTotal();
        order.setTotal(total);
        return repo.save(order);
    }

    public Order fromDto(OrderDTO dto) {
        System.out.println("DTO: " + dto);
        Client client = dto.getClient();
        Date date = dto.getDate();
        System.out.println("Client: " + client + ", Date: " + date);
        Set<OrderItem> newOrderItems = new HashSet<>();
        Double total = 0.0;
        if (dto.getOrderItems() != null) {
            for (OrderItemDTO itemDTO : dto.getOrderItems()) {
                if (itemDTO.getId() == null) {
                    String itemId = UUID.randomUUID().toString();
                    itemDTO.setId(itemId);
                }
                System.out.println("OrderItemDTO: " + itemDTO);
                Product product = itemDTO.getProduct().toEntity();
                System.out.println("ProductDTO: " + itemDTO.getProduct() + " -> Product: " + product);
                OrderItem newOrderItem = itemDTO.toEntity();
                System.out.println("New Order Item: " + newOrderItem);
                newOrderItem.setProduct(product);
                Double subTotal = newOrderItem.getSubTotal();
                System.out.println("SubTotal: " + subTotal);
                total += subTotal;
                newOrderItem.setSubTotal(subTotal);
                newOrderItems.add(newOrderItem);
            }
        }
        System.out.println("New Order Items: " + newOrderItems);
        System.out.println("Total: " + total);
        Order order = new Order(null, date, client, total);
        order.setItems(newOrderItems);
        System.out.println("Order Items in Order: " + order.getOrderItems());
        System.out.println("Order: " + order);
        return order;
    }
}
