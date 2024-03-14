package com.maneira.mongoproject.demo.dto;

import com.maneira.mongoproject.demo.domain.OrderItem;
import com.maneira.mongoproject.demo.domain.Product;
import java.io.Serializable;

public class OrderItemDTO implements Serializable {

    private String id;
    private ProductDTO product;
    private Double price;
    private Integer quantity;
    private Double subTotal;

    public OrderItemDTO(OrderItem orderItem) {}

    public OrderItemDTO(String id, ProductDTO product, Double price, Integer quantity) {
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

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
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

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public OrderItem fromDto(OrderItemDTO dto) {
        ProductDTO productDto = dto.getProduct();
        Product product = new Product(productDto.getId(), productDto.getName(), productDto.getPrice(), productDto.getImgUrl());
        OrderItem orderItem = new OrderItem(dto.getId(), product, dto.getPrice(), dto.getQuantity());
        Double subTotal = orderItem.getSubTotal();
        dto.setSubTotal(subTotal);
        return orderItem;
    }


    public OrderItem toEntity() {
        return new OrderItem(id, product.toEntity(), price, quantity);
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "id='" + id + '\'' +
                ", product=" + product +
                ", price=" + price +
                ", quantity=" + quantity +
                ", subTotal=" + subTotal +
                '}';
    }
}
