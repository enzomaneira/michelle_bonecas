package com.maneira.mongoproject.demo.resources;

import com.maneira.mongoproject.demo.domain.Order;
import com.maneira.mongoproject.demo.domain.Product;
import com.maneira.mongoproject.demo.domain.enums.ProductType;
import com.maneira.mongoproject.demo.dto.ProductDTO;
import com.maneira.mongoproject.demo.resources.util.URL;
import com.maneira.mongoproject.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
@CrossOrigin
public class ProductResource {

    @Autowired
    private ProductService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<Product> list = service.findAll();
        List<ProductDTO> listDto = list.stream().map(ProductDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductDTO> findById(@PathVariable String id) {
        Product product = service.findById(id);
        return ResponseEntity.ok().body(new ProductDTO(product));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody ProductDTO objDto) {
        Product obj = service.fromDto(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody ProductDTO objDto, @PathVariable String id) {
        Product obj = service.fromDto(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDTO>> findByName(@RequestParam(value = "text", defaultValue = "") String name) {
        name = URL.decodeParam(name);
        List<Product> list = service.findByName(name);
        List<ProductDTO> listDto = list.stream().map(ProductDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/findByPriceRange", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDTO>> findByPriceRange(
            @RequestParam(required = false) String minPrice,
            @RequestParam(required = false) String maxPrice) {

        Double parsedMinPrice = URL.convertDouble(minPrice, null);
        Double parsedMaxPrice = URL.convertDouble(maxPrice, null);

        List<Product> list = service.findByPriceRange(parsedMinPrice, parsedMaxPrice);
        List<ProductDTO> listDto = list.stream().map(ProductDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDTO>> searchProducts(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "minPrice", required = false, defaultValue = "") String minPrice,
            @RequestParam(value = "maxPrice", required = false, defaultValue = "10000000.0") String maxPrice,
            @RequestParam(value = "minCount", required = false, defaultValue = "") String minCount,
            @RequestParam(value = "maxCount", required = false, defaultValue = "1000000.0") String maxCount,
            @RequestParam(value = "minCountMoney", required = false, defaultValue = "") String minCountMoney,
            @RequestParam(value = "maxCountMoney", required = false, defaultValue = "") String maxCountMoney,
            @RequestParam(defaultValue = "name", required = false) String orderBy,
            @RequestParam(value = "sortDirection", defaultValue = "ASC") String sortDirection,
            @RequestParam(value = "minReleaseYear", required = false) String minReleaseYear,
            @RequestParam(value = "maxReleaseYear", required = false) String maxReleaseYear,
            @RequestParam(value = "productType", required = false) String productType
    ) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), orderBy);
        name = URL.decodeParam(name);
        Double parsedMinPrice = URL.convertDouble(minPrice, null);
        Double parsedMaxPrice = URL.convertDouble(maxPrice, null);
        Integer parsedMinCount = URL.convertInteger(minCount, null);
        Integer parsedMaxCount = URL.convertInteger(maxCount, null);
        Double parsedMinCountMoney = URL.convertDouble(minCountMoney, null);
        Double parsedMaxCountMoney = URL.convertDouble(maxCountMoney, null);
        Integer parsedMinReleaseYear = URL.convertInteger(minReleaseYear, null);
        Integer parsedMaxReleaseYear = URL.convertInteger(maxReleaseYear, null);

        List<Product> list = service.fullSearch(
                name, parsedMinPrice, parsedMaxPrice, parsedMinCount, parsedMaxCount, parsedMinCountMoney, parsedMaxCountMoney,
                parsedMinReleaseYear, parsedMaxReleaseYear, productType, sort);

        List<ProductDTO> listDto = list.stream().map(ProductDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }


    @RequestMapping(value = "/topSelling", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDTO>> getTopSellingProducts() {
        List<Product> topSellingProducts = service.findTopSellingProducts();
        List<ProductDTO> topSellingProductsDTO = topSellingProducts.stream().map(ProductDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(topSellingProductsDTO);
    }
    @RequestMapping(value = "findByNumber", method = RequestMethod.GET)
    public ResponseEntity<Product> searchByNumber(@RequestParam String number){
        Integer parsedNumber = URL.convertInteger(number, null);
        Product result = service.numberSearch(parsedNumber);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(value = "/{id}/stock", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateStock(@PathVariable String id, @RequestParam Integer quantity) {
        Product product = service.findById(id);
        product.setEstoque(quantity);
        service.save(product);
        return ResponseEntity.noContent().build();
    }
}
