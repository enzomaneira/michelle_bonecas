package com.maneira.mongoproject.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maneira.mongoproject.demo.dto.ClientDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document
public class Order implements Serializable {

    @Id
    private String id;

    @DBRef
    private ClientDTO client;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date date;

    private Double total;

    private Set<OrderItem> items = new HashSet<OrderItem>();

    public Order(){

    }

    public Order(String id, Client client, Date date) {}

    public Order(String id, Date date, ClientDTO client, Double total) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.total = calculateTotal();
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

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public Double getTotal() {
        return total;
    }

    private Double calculateTotal() {
        double sum = 0.0;
        for (OrderItem item : items) {
            sum += item.getSubTotal();
        }
        return new BigDecimal(sum)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
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



}
