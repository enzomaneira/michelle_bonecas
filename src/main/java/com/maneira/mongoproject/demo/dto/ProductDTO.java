package com.maneira.mongoproject.demo.dto;

import com.maneira.mongoproject.demo.domain.Product;

public class ProductDTO {

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
}
