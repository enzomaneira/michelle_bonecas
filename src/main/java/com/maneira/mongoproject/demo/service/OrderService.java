package com.maneira.mongoproject.demo.service;

import com.maneira.mongoproject.demo.domain.Order;
import com.maneira.mongoproject.demo.repository.OrderRepository;
import com.maneira.mongoproject.demo.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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


    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public List<Order> findByClient(String clientName) {
        return repo.findByClientNameIgnoreCase(clientName);
    }

    public List<Order> findByProduct(String productName){
        return repo.findByItemsProductNameIgnoreCase(productName);
    }

    public List<Order> customSearch(String text, Date minDate, Date maxDate, Double minTotal, Double maxTotal, String client, String product) {
        return repo.customSearch(text, minDate, maxDate, minTotal, maxTotal, client, product);
    }

    public Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(dateString);
    }




}
