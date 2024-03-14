package com.maneira.mongoproject.demo.domain;

import com.maneira.mongoproject.demo.dto.OrderItemDTO;
import com.maneira.mongoproject.demo.dto.ProductDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.Objects;

@Document
public class OrderItem implements Serializable {

    @Id
    private String id;
    private Product product;
    private Double price;
    private Integer quantity;
    private Double subTotal;

    public OrderItem(){

    }

    public OrderItem(String id, Product product, Double price, Integer quantity) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer qtd) {
        this.quantity = qtd;
    }

    public Double getSubTotal() {
        if (quantity != null) {
            return price * quantity;
        } else {
            return 0.0;
        }
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem orderItem)) return false;
        return Objects.equals(getId(), orderItem.getId()) && Objects.equals(getProduct(), orderItem.getProduct()) && Objects.equals(getPrice(), orderItem.getPrice()) && Objects.equals(getQuantity(), orderItem.getQuantity()) && Objects.equals(getSubTotal(), orderItem.getSubTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProduct(), getPrice(), getQuantity(), getSubTotal());
    }

    public static OrderItem fromDto(OrderItemDTO dto) {
        ProductDTO productDto = dto.getProduct();
        Product product = new Product(productDto.getId(), productDto.getName(), productDto.getPrice(), productDto.getImgUrl());
        return new OrderItem(dto.getId(), product, dto.getPrice(), dto.getQuantity());
    }

    public OrderItemDTO toDto() {
        ProductDTO productDto = new ProductDTO(product);
        return new OrderItemDTO(id, productDto, price, quantity);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id='" + id + '\'' +
                ", product=" + product +
                ", price=" + price +
                ", quantity=" + quantity +
                ", subTotal=" + subTotal +
                '}';
    }
}
