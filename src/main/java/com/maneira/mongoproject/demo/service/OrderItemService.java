package com.maneira.mongoproject.demo.service;

import com.maneira.mongoproject.demo.domain.OrderItem;
import com.maneira.mongoproject.demo.domain.Product;
import com.maneira.mongoproject.demo.dto.OrderItemDTO;
import com.maneira.mongoproject.demo.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductService productService;

    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    public OrderItem findById(String id) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        return orderItem.orElse(null);
    }

    public OrderItem insert(OrderItem orderItem) {
        OrderItem savedOrderItem = orderItemRepository.insert(orderItem);

        Product product = productService.findById(orderItem.getProduct().getId());

        if (product != null) {
            product.setCount(product.getCount() + orderItem.getQtd());
            product.setCountMoney(product.getCountMoney() + orderItem.getSubTotal());
            productService.save(product);
        }

        return savedOrderItem;
    }
}
