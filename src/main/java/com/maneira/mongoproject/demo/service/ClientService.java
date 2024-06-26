package com.maneira.mongoproject.demo.service;

import com.maneira.mongoproject.demo.domain.Client;
import com.maneira.mongoproject.demo.domain.Product;
import com.maneira.mongoproject.demo.dto.ClientDTO;
import com.maneira.mongoproject.demo.repository.ClientRepository;
import com.maneira.mongoproject.demo.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static com.maneira.mongoproject.demo.domain.enums.ProductType.FELTRO;

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

    public Client insert(Client client) {
        Integer number = client.getNumber();
        Client existingClient = repo.findByNumber(number);

        if (existingClient != null) {
            throw new RuntimeException("Um cliente com o número " + number + " já existe.");
        }

        return repo.insert(client);
    }


    public Client fromDto(ClientDTO objDto){
        return new Client(objDto.getId(), objDto.getNumber(), objDto.getName(), objDto.getWhere(), objDto.getContact());
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
        newObj.setNumber(obj.getNumber());
        newObj.setWhere(obj.getWhere());
    }

    public List<Client> findByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    public List<Client> findByContact(String contact) {
        return repo.findByContactContainingIgnoreCase(contact);
    }

    public List<Client> searchClient(
            String name, String contact, String where, Integer minCount, Integer maxCount, Double minCountMoney, Double maxCountMoney, Sort sort
    ) {
        if (minCount == null) minCount = 0;
        if (maxCount == null) maxCount = Integer.MAX_VALUE;
        if (minCountMoney == null) minCountMoney = 0.0;
        if (maxCountMoney == null) maxCountMoney = Double.MAX_VALUE;
        if (where == null)where = "";
        if (contact == null) {
            contact = "";
        }
        return repo.fullSearch(
                name, contact, where, minCount, maxCount, minCountMoney, maxCountMoney, sort
        );
    }

    public Client fromDTO(ClientDTO client){
        return new Client(client.getId(), client.getNumber(), client.getName(), client.getWhere(), client.getContact());
    }

    public List<Client> findAllOrderByAlphabetical() {
        return repo.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<Client> findAllOrderByCount() {
        return repo.findAll(Sort.by(Sort.Direction.DESC, "count"));
    }

    public List<Client> findAllOrderByCountMoney() {
        return repo.findAll(Sort.by(Sort.Direction.DESC, "countMoney"));
    }

    public List<Client> findTopBuyers() {
        Sort sortByCountDesc = Sort.by(Sort.Direction.DESC, "count");
        return repo.findAll(sortByCountDesc);
    }

    public Client findByNumber(Integer number){
        return repo.findByNumber(number);
    }
}