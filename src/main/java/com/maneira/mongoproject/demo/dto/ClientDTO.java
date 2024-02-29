package com.maneira.mongoproject.demo.dto;

import com.maneira.mongoproject.demo.domain.Client;
import org.springframework.data.annotation.Id;

public class ClientDTO {


    private String id;
    private String name;
    private String contact;

    private Double total;
    ;
    public ClientDTO(){

    }

    public ClientDTO(Client obj) {
        id = obj.getId();
        name = obj.getName();
        contact = obj.getContact();
        total = obj.getTotal();
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

    public String getcontact() {
        return contact;
    }

    public void setcontact(String contact) {
        this.contact = contact;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
