package com.maneira.mongoproject.demo.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maneira.mongoproject.demo.domain.Product;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.io.Serializable;

public class ProductDTO implements Serializable {

    private String id;
    private String name;
    private Double price;
    private String imgUrl;

    public ProductDTO() {

    }

    public ProductDTO(Product obj) {
        id = obj.getId();
        name = obj.getName();
        price = obj.getPrice();
        imgUrl = obj.getImgUrl();
    }

    public ProductDTO(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductDTO productDTO = objectMapper.readValue(jsonString, ProductDTO.class);
        BeanUtils.copyProperties(productDTO, this);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Product toEntity() {
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setPrice(this.price);
        product.setImgUrl(this.imgUrl);
        return product;
    }

    public static ProductDTO fromEntity(Product productEntity) {
        return new ProductDTO(productEntity);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
