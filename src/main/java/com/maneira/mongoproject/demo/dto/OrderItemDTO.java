package com.maneira.mongoproject.demo.dto;

import com.maneira.mongoproject.demo.domain.OrderItem;
import com.maneira.mongoproject.demo.domain.Product;
import java.io.Serializable;

public class OrderItemDTO implements Serializable {

    private String id;
    private ProductDTO product;
    private Double price;
    private Integer qtd;
    private Double subTotal;

    public OrderItemDTO() {}

    public OrderItemDTO(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.product = new ProductDTO(orderItem.getProduct());
        this.price = orderItem.getPrice();
        this.qtd = orderItem.getQtd();
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

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Double getSubTotal() {
        if (qtd != null) {
            return price * qtd;
        } else {
            return 0.0;
        }
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public OrderItem fromDto(OrderItemDTO dto) {
        ProductDTO productDto = dto.getProduct();
        Product product = new Product(productDto.getId(), productDto.getNumber(), productDto.getName(), productDto.getPrice(), productDto.getImgUrl(), productDto.getReleaseYear(), productDto.getProductType());
        OrderItem orderItem = new OrderItem(dto.getId(), product, dto.getPrice(), dto.getQtd());
        Double subTotal = orderItem.getSubTotal();
        dto.setSubTotal(subTotal);
        return orderItem;
    }

    public OrderItem toEntity() {
        return new OrderItem(id, product.toEntity(), price, qtd);
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "id='" + id + '\'' +
                ", product=" + product +
                ", price=" + price +
                ", qtd=" + qtd +
                ", subTotal=" + subTotal +
                '}';
    }
}