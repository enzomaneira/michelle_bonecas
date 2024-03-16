package com.maneira.mongoproject.demo.service;

import com.maneira.mongoproject.demo.domain.Product;
import com.maneira.mongoproject.demo.dto.ProductDTO;
import com.maneira.mongoproject.demo.repository.ProductRepository;
import com.maneira.mongoproject.demo.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Product> findByNameAndPriceRange(String name, Double minPrice, Double maxPrice) {
        return repo.findByNameIgnoreCaseContainingAndPriceBetween(name, minPrice, maxPrice);
    }

    public Product fromDto(ProductDTO product){
        return new Product(product.getId(), product.getName(), product.getPrice(), product.getImgUrl());
    }

}