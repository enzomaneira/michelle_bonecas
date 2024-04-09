package com.maneira.mongoproject.demo.service;

import com.maneira.mongoproject.demo.domain.Client;
import com.maneira.mongoproject.demo.domain.Order;
import com.maneira.mongoproject.demo.domain.enums.OrderStatus;
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

    @Autowired
    private ProductService productService;

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
        Integer number = order.getNumber();
        Order existingOrder = numberSearch(number);

        if (existingOrder != null) {
            throw new RuntimeException("Um pedido com o número " + number + " já existe.");
        }

        Order savedOrder = repo.insert(order);

        if (!order.getItems().isEmpty()) {
            OrderItem firstItem = order.getItems().iterator().next();
            String productId = firstItem.getProduct().getId();
            String clientId = order.getClient().getId();

        }

        Client client = clientService.findById(order.getClient().getId());
        client.setCount(client.getCount() + 1);
        client.setCountMoney(client.getCountMoney() + order.getTotal());
        clientService.save(client);

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

    public Order numberSearch(Integer number){
        return repo.findByNumber(number);
    }

    public Order updateOrderStatus(String orderId, OrderStatus newStatus, Date date) {
        Order order = findById(orderId);
        order.setOrderStatus(newStatus);
        switch (newStatus) {
            case EM_CONFECCAO:
                order.setDateInit(date);
                break;
            case PRONTO:
                order.setDateEnd(date);
                break;
            case ENTREGUE:
                order.setDateDeliver(date);
                break;
            case PAGO:
                order.setDatePayment(date);
                break;
            default:
                break;
        }

        return repo.save(order);
    }

    public Order fromDto(OrderDTO dto) {
        Client client = dto.getClient();
        Date date = dto.getDate();
        Set<OrderItem> items = new HashSet<>();
        Double total = 0.0;
        Integer number = dto.getNumber();
        Integer orderStatus = dto.getOrderStatus();
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
        Order order = new Order(null, number, date, client, total, OrderStatus.valueOf(orderStatus));
        order.setItems(items);
        return order;
    }

}
