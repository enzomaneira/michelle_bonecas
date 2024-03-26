package com.maneira.mongoproject.demo.service;

import com.maneira.mongoproject.demo.domain.Product;
import com.maneira.mongoproject.demo.dto.ProductDTO;
import com.maneira.mongoproject.demo.repository.ProductRepository;
import com.maneira.mongoproject.demo.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> findAll() {
        return repo.findAll();
    }

    public Product findById(String id) {
        Optional<Product> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public Product insert(Product product) {
        return repo.insert(product);
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public Product update(Product obj) {
        Product newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    private void updateData(Product newObj, Product obj) {
        newObj.setName(obj.getName());
        newObj.setPrice(obj.getPrice());
        newObj.setImgUrl(obj.getImgUrl());
    }

    public List<Product> findByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    public List<Product> findByPriceRange(Double minPrice, Double maxPrice) {
        return repo.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> findByNameIgnoreCaseContainingAndPriceBetweenAndCountBetweenAndCountMoneyBetween(String name, Double minPrice, Double maxPrice, Integer minCount, Integer maxCount, Double minCountMoney, Double maxCountMoney, Sort sort) {
        if (minPrice == null) minPrice = 0.0;
        if (maxPrice == null) maxPrice = 10000.0;
        if (minCount == null) minCount = 0;
        if (maxCount == null) maxCount = Integer.MAX_VALUE;
        if (minCountMoney == null) minCountMoney = 0.0;
        if (maxCountMoney == null) maxCountMoney = Double.MAX_VALUE;
        return repo.findByNameIgnoreCaseContainingAndPriceBetweenAndCountBetweenAndCountMoneyBetween(name, minPrice, maxPrice, minCount, maxCount, minCountMoney, maxCountMoney, sort);
    }

    public Product fromDto(ProductDTO product){
        return new Product(product.getId(), product.getName(), product.getPrice(), product.getImgUrl());
    }

    public Product save(Product product){
        return repo.save(product);
    }
    public List<Product> findTopSellingProducts() {
        Sort sortByCountDesc = Sort.by(Sort.Direction.DESC, "count");
        return repo.findAll(sortByCountDesc);
    }

}