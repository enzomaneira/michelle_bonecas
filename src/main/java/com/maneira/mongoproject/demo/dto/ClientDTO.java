package com.maneira.mongoproject.demo.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maneira.mongoproject.demo.domain.Client;
import org.springframework.beans.BeanUtils;
import java.io.IOException;
import java.io.Serializable;

public class ClientDTO implements Serializable {

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

    public ClientDTO(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClientDTO clientDTO = objectMapper.readValue(jsonString, ClientDTO.class);
        BeanUtils.copyProperties(clientDTO, this);
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

    public Client toEntity() {
        Client client = new Client();
        client.setId(this.id);
        client.setName(this.name);
        client.setContact(this.contact);
        return client;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
