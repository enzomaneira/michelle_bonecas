package com.maneira.mongoproject.demo.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maneira.mongoproject.demo.domain.Client;
import com.maneira.mongoproject.demo.domain.Product;
import com.maneira.mongoproject.demo.domain.enums.ProductType;
import org.springframework.beans.BeanUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class ProductDTO implements Serializable {

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

    public ProductDTO() {
    }

    public ProductDTO(Product obj) {
        id = obj.getId();
        number = obj.getNumber();
        name = obj.getName();
        price = obj.getPrice();
        imgUrl = obj.getImgUrl();
        count = obj.getCount();
        countMoney = obj.getCountMoney();
        estoque = obj.getEstoque();
        releaseYear = obj.getReleaseYear();
        productType = obj.getProductType();
        listClients = obj.getListClients();
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

    public List<Client> getListClients() {
        return listClients;
    }

    public void setListClients(List<Client> listClients) {
        this.listClients = listClients;
    }

    public Product toEntity() {
        Product product = new Product();
        product.setId(this.id);
        product.setNumber(this.number);
        product.setName(this.name);
        product.setPrice(this.price);
        product.setImgUrl(this.imgUrl);
        product.setCount(this.count);
        product.setCountMoney(this.countMoney);
        product.setReleaseYear(this.releaseYear);
        product.setProductType(this.productType);
        product.setListClients(this.listClients);
        return product;
    }

    public static ProductDTO fromEntity(Product productEntity) {
        return new ProductDTO(productEntity);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id='" + id + '\'' +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                ", count=" + count +
                ", countMoney=" + countMoney +
                ", estoque=" + estoque +
                ", releaseYear=" + releaseYear +
                ", productType=" + productType +
                ", listClients=" + listClients +
                '}';
    }
}
