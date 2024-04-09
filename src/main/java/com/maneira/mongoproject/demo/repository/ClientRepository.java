package com.maneira.mongoproject.demo.repository;

import com.maneira.mongoproject.demo.domain.Client;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {
    List<Client> findByNameContainingIgnoreCase(String name);
    List<Client> findByContactContainingIgnoreCase(String contact);
    @Query("{ $and: [ " +
            "{ name: { $regex: ?0, $options: 'i' } }, " +
            "{ contact: { $regex: ?1, $options: 'i' } }, " +
            "{ where: { $regex: ?2, $options: 'i' } }, " +
            "{ count: { $gte: ?3, $lte: ?4 } }, " +
            "{ countMoney: { $gte: ?5, $lte: ?6 } }" +
            "] }")
    List<Client> fullSearch(
            String nameRegex, String contactRegex, String whereRegex,
            Integer minCount, Integer maxCount, Double minCountMoney, Double maxCountMoney,
            Sort sort);

    Client findByNumber(Integer number);

}
