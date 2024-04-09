package com.maneira.mongoproject.demo.service;

import com.maneira.mongoproject.demo.domain.Client;
import com.maneira.mongoproject.demo.domain.Order;
import com.maneira.mongoproject.demo.domain.Product;
import com.maneira.mongoproject.demo.domain.enums.ProductType;
import com.maneira.mongoproject.demo.dto.ProductDTO;
import com.maneira.mongoproject.demo.repository.ProductRepository;
import com.maneira.mongoproject.demo.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static com.maneira.mongoproject.demo.domain.enums.ProductType.FELTRO;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private ClientService clientService;

    public List<Product> findAll() {
        return repo.findAll();
    }

    public Product findById(String id) {
        Optional<Product> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public Product insert(Product product) {
        Integer number = product.getNumber();
        Product existingProduct = repo.findByNumber(number);

        if (existingProduct != null) {
            throw new RuntimeException("Um produto com o número " + number + " já existe.");
        }

        return repo.insert(product);
    }


    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public Product update(Product obj) {
        Product newObj = findById(obj.getId());
        updateData(newObj, obj);
        newObj.setProductType(obj.getProductType());
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

    public List<Product> fullSearch(
            String name, Double minPrice, Double maxPrice, Integer minCount, Integer maxCount, Double minCountMoney, Double maxCountMoney,
            Integer minReleaseYear, Integer maxReleaseYear, String productType, Sort sort) {
        if (minPrice == null) minPrice = 0.0;
        if (maxPrice == null) maxPrice = 10000.0;
        if (minCount == null) minCount = 0;
        if (maxCount == null) maxCount = Integer.MAX_VALUE;
        if (minCountMoney == null) minCountMoney = 0.0;
        if (maxCountMoney == null) maxCountMoney = Double.MAX_VALUE;
        if (minReleaseYear == null) minReleaseYear = 0;
        if (maxReleaseYear == null) maxReleaseYear = Integer.MAX_VALUE;
        if(productType == null) productType = "";


        return repo.fullSearch(
                name, minPrice, maxPrice, minCount, maxCount, minCountMoney, maxCountMoney,
                minReleaseYear, maxReleaseYear, productType, sort);
    }


    public Product fromDto(ProductDTO product){
        return new Product(product.getId(), product.getNumber(),  product.getName(), product.getPrice(), product.getImgUrl(), product.getReleaseYear(), product.getProductType());
    }

    public Product save(Product product){
        return repo.save(product);
    }
    public List<Product> findTopSellingProducts() {
        Sort sortByCountDesc = Sort.by(Sort.Direction.DESC, "count");
        return repo.findAll(sortByCountDesc);
    }

    public Product numberSearch(Integer number){
        return repo.findByNumber(number);
    }

    public Product addClientToProduct(String productId, String clientId) {
        Product product = findById(productId);
        List<Client> clients = product.getListClients();

        boolean clientExists = clients.stream().anyMatch(client -> client.getId().equals(clientId));

        if (!clientExists) {
            Client client = clientService.findById(clientId);
            clients.add(client);
            product.setListClients(clients);
            save(product);
        }

        return product;
    }




}