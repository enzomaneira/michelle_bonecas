package com.maneira.mongoproject.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maneira.mongoproject.demo.domain.enums.OrderStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.*;

@Document
public class Order implements Serializable {

    @Id
    private String id;
    private Integer number;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date date;
    private Client client;
    private Double total;
    private Integer orderStatus;
    private Set<OrderItem> items = new HashSet<>();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date dateInit;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date dateEnd;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date dateDeliver;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT")
    private Date datePayment;

    public Order(){}

    public Order(String id, Integer number, Date date, Client client, Double total, OrderStatus orderStatus) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.client = client;
        this.total = total;
        setOrderStatus(orderStatus);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus.getCode();
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

    public Date getDateInit() {
        return dateInit;
    }

    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getDateDeliver() {
        return dateDeliver;
    }

    public void setDateDeliver(Date dateDeliver) {
        this.dateDeliver = dateDeliver;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
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
                ", number=" + number +
                ", date=" + date +
                ", client=" + client +
                ", total=" + total +
                ", orderStatus=" + orderStatus +
                ", items=" + items +
                ", dateInit=" + dateInit +
                ", dateEnd=" + dateEnd +
                ", dateDeliver=" + dateDeliver +
                ", datePayment=" + datePayment +
                '}';
    }
}