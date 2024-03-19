package com.maneira.mongoproject.demo.service;

import com.maneira.mongoproject.demo.domain.Client;
import com.maneira.mongoproject.demo.dto.ClientDTO;
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

    public Client insert(Client client){
        return repo.insert(client);
    }

    public Client fromDto(ClientDTO objDto){
        return new Client(objDto.getId(), objDto.getName(), objDto.getContact(), objDto.getCount());
    }

    public void delete(String id){
        findById(id);
        repo.deleteById(id);
    }

    public Client update(Client obj) {
        Client newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public Client save(Client client){
        return repo.save(client);
    }

    private void updateData(Client newObj, Client obj) {
        newObj.setName(obj.getName());
        newObj.setContact(obj.getContact());
    }

    public List<Client> findByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    public List<Client> findByContact(String contact) {
        return repo.findByContactContainingIgnoreCase(contact);
    }

    public List<Client> findByNameAndContact(String name, String contact, Integer minCount, Integer maxCount) {
        return repo.findByNameContainingIgnoreCaseAndContactContainingIgnoreCaseAndCountBetween(name, contact, minCount, maxCount);
    }

    public Client fromDTO(ClientDTO client){
        return new Client(client.getId(), client.getName(), client.getContact(), client.getCount());
    }
}