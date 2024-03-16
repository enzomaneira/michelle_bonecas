package com.maneira.mongoproject.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maneira.mongoproject.demo.domain.Client;
import com.maneira.mongoproject.demo.domain.Order;
import com.maneira.mongoproject.demo.domain.OrderItem;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderDTO implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date date;
    private Client client;
    private Set<OrderItemDTO> items;

    public OrderDTO() {
    }

    public OrderDTO(Date date, Client client) {
        this.date = date;
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<OrderItemDTO> items) {
        this.items = items;
    }

    public Order fromDto() {
        Order order = new Order();
        order.setDate(this.date);
        order.setClient(this.client);
        Set<OrderItem> items = new HashSet<>();
        if (this.items != null) {
            for (OrderItemDTO itemDTO : this.items) {
                items.add(itemDTO.toEntity());
            }
        }
        order.setItems(items);

        return order;
    }
}