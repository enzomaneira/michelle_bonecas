package com.maneira.mongoproject.demo.repository;

import com.maneira.mongoproject.demo.domain.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    List<Client> findByNameContainingIgnoreCase(String name);


    List<Client> findByContactContainingIgnoreCase(String contact);


    List<Client> findByNameContainingIgnoreCaseAndContactContainingIgnoreCase(String name, String contact);


}
