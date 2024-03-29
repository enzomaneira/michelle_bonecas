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
import org.springframework.data.domain.Sort;
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
        Order savedOrder = repo.insert(order);

        Client client = clientService.findById(order.getClient().getId());
        if(client != null){
            client.setCount(client.getCount() + 1);
            client.setCountMoney(client.getCountMoney() + order.getTotal());
            clientService.save(client);
        }

        return savedOrder;
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public List<Order> findByTotalRange(Double minTotal, Double maxTotal) {
        return repo.findByTotalBetween(minTotal, maxTotal);
    }

    public List<Order> fullSearch(String text, Date minDate, Date maxDate, Double minTotal, Double maxTotal, String client, String product, Sort sort) {
        return repo.fullSearch(text, minDate, maxDate, minTotal, maxTotal, client, product, sort);
    }


    public Order fromDto(OrderDTO dto) {
        Client client = dto.getClient();
        Date date = dto.getDate();
        Set<OrderItem> items = new HashSet<>();
        Double total = 0.0;
        if (dto.getItems() != null) {
            for (OrderItemDTO itemDTO : dto.getItems()) {
                Product product = itemDTO.getProduct().toEntity();
                OrderItem newOrderItem = itemDTO.toEntity();
                newOrderItem.setProduct(product);
                Double subTotal = newOrderItem.getSubTotal();
                total += subTotal;
                newOrderItem.setSubTotal(subTotal);
                items.add(newOrderItem);
            }
        }
        Order order = new Order(null, date, client, total);
        order.setItems(items);
        return order;
    }
}
