package com.maneira.mongoproject.demo.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maneira.mongoproject.demo.domain.Client;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.io.Serializable;

public class ClientDTO implements Serializable {

    private String id;
    private Integer number;
    private String name;
    private String where;
    private String contact;
    private Integer count;
    private Double countMoney;

    public ClientDTO() {}

    public ClientDTO(Client obj) {
        id = obj.getId();
        number = obj.getNumber();
        name = obj.getName();
        where = obj.getWhere();
        contact = obj.getContact();
        count = obj.getCount();
        countMoney = obj.getCountMoney();
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

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public Client toEntity() {
        Client client = new Client();
        client.setId(this.id);
        client.setNumber(this.number);
        client.setName(this.name);
        client.setWhere(this.where);
        client.setContact(this.contact);
        client.setCount(this.count);
        client.setCountMoney(this.countMoney);
        return client;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id='" + id + '\'' +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", where='" + where + '\'' +
                ", contact='" + contact + '\'' +
                ", count=" + count +
                ", countMoney=" + countMoney +
                '}';
    }
}
