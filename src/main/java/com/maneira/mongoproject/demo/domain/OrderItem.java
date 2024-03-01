package com.maneira.mongoproject.demo.domain;

import org.springframework.boot.convert.DurationFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Document
public class OrderItem implements Serializable {

    @Id
    private String id;

    private Product product;

    private Integer qtd;
    private Double price;

    public OrderItem(){

    }

    public OrderItem(String id,  Product product, Integer qtd, Double price) {
        this.id = id;
        this.product = product;
        this.qtd = qtd;
        this.price = price;
    }

    public String getId(){
        return id;
    }

    public void setId(){
        this.id = id;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem orderItem)) return false;
        return Objects.equals(getId(), orderItem.getId()) && Objects.equals(getQtd(), orderItem.getQtd()) && Objects.equals(getPrice(), orderItem.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getQtd(), getPrice());
    }
}
