package com.maneira.mongoproject.demo.repository;

import com.maneira.mongoproject.demo.domain.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

}
