package com.maneira.mongoproject.demo.dto;

import com.maneira.mongoproject.demo.domain.Client;

public class ClientDTO {


    private String id;
    private String name;
    private String contact;

    public ClientDTO(){

    }

    public ClientDTO(Client obj) {
        id = obj.getId();
        name = obj.getName();
        contact = obj.getContact();
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
