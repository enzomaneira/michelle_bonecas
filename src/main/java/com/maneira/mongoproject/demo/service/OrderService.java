package com.maneira.mongoproject.demo.service;

import com.maneira.mongoproject.demo.domain.Order;
import com.maneira.mongoproject.demo.domain.Product;
import com.maneira.mongoproject.demo.dto.OrderDTO;
import com.maneira.mongoproject.demo.dto.OrderItemDTO;
import com.maneira.mongoproject.demo.dto.ProductDTO;
import com.maneira.mongoproject.demo.repository.OrderRepository;
import com.maneira.mongoproject.demo.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
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
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public Order insert(Order order) {
        order.getTotal();
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

    public Order fromDto(OrderDTO objDto) throws ParseException {
        return new Order(objDto.getId(), objDto.getDate(), objDto.toEntity().getClient(), objDto.getTotal());
    }

    public Order save(Order order) {
        order.getTotal();
        return repo.save(order);
    }
}
