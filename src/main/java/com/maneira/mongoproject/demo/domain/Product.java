package com.maneira.mongoproject.demo.domain;

import com.maneira.mongoproject.demo.domain.enums.ProductType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Document(collection = "product")
public class Product implements Serializable {

    @Id
    private String id;
    private Integer number;
    private String name;
    private Double price;
    private String imgUrl;
    private Integer count;
    private Double countMoney;
    private Integer estoque;
    private Integer releaseYear;
    private ProductType productType;
    private List<Client> listClients;

    public Product(){}

    public Product(String id, Integer number, String name, Double price, String imgUrl, Integer releaseYear, ProductType productType) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.count = 0;
        this.countMoney = 0.0;
        this.estoque = 0;
        this.releaseYear = releaseYear;
        this.productType = productType;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getCountMoney() {
        return countMoney;
    }

    public void setCountMoney(Double countMoney) {
        this.countMoney = countMoney;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public List<Client> getListClients() {
        return listClients;
    }

    public void setListClients(List<Client> listClients) {
        this.listClients = listClients;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
