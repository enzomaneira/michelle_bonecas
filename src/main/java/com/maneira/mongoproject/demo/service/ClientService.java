package com.maneira.mongoproject.demo.service;

import com.maneira.mongoproject.demo.domain.Client;
import com.maneira.mongoproject.demo.repository.ClientRepository;
import com.maneira.mongoproject.demo.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    public List<Client> findAll(){
        return repo.findAll();
    }

    public Client findById(String id) {
        Optional<Client> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

}
