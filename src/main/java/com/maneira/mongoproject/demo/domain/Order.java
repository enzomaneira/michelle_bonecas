package com.maneira.mongoproject.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maneira.mongoproject.demo.dto.OrderItemDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.*;

@Document
public class Order implements Serializable {

    @Id
    private String id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date date;
    @DBRef
    private Client client;

    private Double total;

    @DBRef
    private Set<OrderItem> items = new HashSet<>();

    public Order(){}

    public Order(String id, Date date, Client client, Double total) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setTotal(Double total) {
        this.total = total;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public double getTotal() {
        double sum = 0.0;
        for (OrderItem x : items) {
            if (x != null) {
                sum += x.getSubTotal();
            }
        }
        return Math.round(sum * 100.0) / 100.0;
    }

    public Set<OrderItem> getOrderItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", client=" + client +
                ", total=" + total +
                ", orderItems=" + items +
                '}';
    }




}