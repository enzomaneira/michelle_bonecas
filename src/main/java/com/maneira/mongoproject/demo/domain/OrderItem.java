package com.maneira.mongoproject.demo.domain;

import org.springframework.boot.convert.DurationFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

public class OrderItem implements Serializable {

    private Product product;

    private Integer qtd;
    private Double price;

    private Double total;



    public OrderItem() {

    }

    public OrderItem(Product product, Integer qtd, Double price) {
        ;
        this.product = product;
        this.qtd = qtd;
        this.price = price;
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

    public Double getSubTotal() {
        return price * qtd;
    }

}
