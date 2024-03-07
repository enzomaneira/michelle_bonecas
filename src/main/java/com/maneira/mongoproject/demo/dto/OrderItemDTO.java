package com.maneira.mongoproject.demo.dto;

import com.maneira.mongoproject.demo.domain.OrderItem;
import com.maneira.mongoproject.demo.domain.Product;

import java.util.Objects;

public class OrderItemDTO {

    private ProductDTO product;
    private Integer qtd;
    private Double price;
    private Double total;

    public OrderItemDTO(ProductDTO productDTO, Integer qtd, Double price) {}

    public OrderItemDTO(OrderItem orderItem) {
        this.product = new ProductDTO(orderItem.getProduct());
        this.qtd = orderItem.getQtd();
        this.price = orderItem.getPrice();
        this.total = orderItem.getSubTotal();
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
        this.total = calculateTotal();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
        this.total = calculateTotal();
    }

    public Double getTotal() {
        return total;
    }

    private Double calculateTotal() {
        return (qtd != null && price != null) ? qtd * price : null;
    }

    public OrderItem toEntity(ProductDTO productDTO) {
        Product product = (productDTO != null) ? productDTO.toEntity() : null;
        return new OrderItem(product, qtd, price);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemDTO orderItemDTO)) return false;
        return Objects.equals(getProduct(), orderItemDTO.getProduct())
                && Objects.equals(getQtd(), orderItemDTO.getQtd())
                && Objects.equals(getPrice(), orderItemDTO.getPrice())
                && Objects.equals(getTotal(), orderItemDTO.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProduct(), getQtd(), getPrice(), getTotal());
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "product=" + product +
                ", qtd=" + qtd +
                ", price=" + price +
                ", total=" + total +
                '}';
    }
}
