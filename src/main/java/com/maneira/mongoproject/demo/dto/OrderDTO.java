package com.maneira.mongoproject.demo.dto;

import com.maneira.mongoproject.demo.domain.Client;
import com.maneira.mongoproject.demo.domain.Order;
import com.maneira.mongoproject.demo.domain.OrderItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderDTO {

    private String id;
    private ClientDTO client;
    private String date;
    private Double total;
    private List<OrderItemDTO> items;

    public OrderDTO() {}

    public OrderDTO(Order obj) {
        this.id = obj.getId();
        this.date = formatDate(obj.getDate());
        this.client = new ClientDTO(obj.getClient());
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    private List<OrderItemDTO> convertOrderItemsToDTO(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItemDTO::new)
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public Order toEntity() throws ParseException {
        Order order = new Order(id, new SimpleDateFormat("yyyy-MM-dd").parse(date), client.toEntity());

        Set<OrderItem> orderItems = items.stream()
                .map(orderItemDTO -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(orderItemDTO.getProduct().toEntity());
                    orderItem.setQtd(orderItemDTO.getQtd());
                    orderItem.setPrice(orderItemDTO.getPrice());
                    return orderItem;
                })
                .collect(Collectors.toSet());

        order.setItems(orderItems);

        return order;
    }

}
