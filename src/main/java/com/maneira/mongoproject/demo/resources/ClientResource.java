package com.maneira.mongoproject.demo.resources;

import com.maneira.mongoproject.demo.domain.Client;
import com.maneira.mongoproject.demo.dto.ClientDTO;
import com.maneira.mongoproject.demo.resources.util.URL;
import com.maneira.mongoproject.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
@CrossOrigin
public class ClientResource {

    @Autowired
    private ClientService service;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<Client> list = service.findAll();
        List<ClientDTO> listDto = list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClientDTO> findById(@PathVariable String id){
        Client client = service.findById(id);
        return ResponseEntity.ok().body(new ClientDTO(client));

    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody ClientDTO objDto){
        Client obj = service.fromDto(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody ClientDTO objDto, @PathVariable String id) {
        Client obj = service.fromDto(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> findByName(@RequestParam(value = "name", defaultValue = "") String name) {
        name = URL.decodeParam(name);
        List<Client> list = service.findByName(name);
        List<ClientDTO> listDto = list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/findByContact", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> findByContact(@RequestParam(value = "contact", defaultValue = "") String contact) {
        contact = URL.decodeParam(contact);
        List<Client> list = service.findByContact(contact);
        List<ClientDTO> listDto = list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/findByNameAndContact", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> findByNameAndContact(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "contact", required = false, defaultValue = "") String contact,
            @RequestParam(value = "where", required = false, defaultValue = "") String where,
            @RequestParam(value = "minCount", required = false, defaultValue = "0") String minCount,
            @RequestParam(value = "maxCount", required = false, defaultValue = "100000000.0") String maxCount,
            @RequestParam(value = "minCountMoney", required = false, defaultValue = "0") String minCountMoney,
            @RequestParam(value = "maxCountMoney", required = false, defaultValue = "100000000000.0") String maxCountMoney,
            @RequestParam(defaultValue = "name", required = false) String orderBy,
            @RequestParam(value = "sortDirection", defaultValue = "ASC") String sortDirection
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), orderBy);
        name = URL.decodeParam(name);
        contact = URL.decodeParam(contact);
        where = URL.decodeParam(where);
        Integer parsedMinCount = URL.convertInteger(minCount, null);
        Integer parsedMaxCount = URL.convertInteger(maxCount, null);
        Double parsedMinCountMoney = URL.convertDouble(minCountMoney, null);
        Double parsedMaxCountMoney = URL.convertDouble(maxCountMoney, null);

        List<Client> list = service.searchClient(name, contact, where, parsedMinCount, parsedMaxCount, parsedMinCountMoney, parsedMaxCountMoney, sort);
        List<ClientDTO> listDto = list.stream().map(ClientDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/orderByAlphabetical", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> findAllOrderByAlphabetical(){
        List<Client> list = service.findAllOrderByAlphabetical();
        List<ClientDTO> listDto = list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/orderByCount", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> findAllOrderByCount(){
        List<Client> list = service.findAllOrderByCount();
        List<ClientDTO> listDto = list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/orderByCountMoney", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> findAllOrderByCountMoney(){
        List<Client> list = service.findAllOrderByCountMoney();
        List<ClientDTO> listDto = list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/topBuyers", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> getTopSellingProducts() {
        List<Client> topSellingProducts = service.findTopBuyers();
        List<ClientDTO> topSellingProductsDTO = topSellingProducts.stream().map(ClientDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(topSellingProductsDTO);
    }

    @RequestMapping(value = "/findByNumber", method = RequestMethod.GET)
    public ResponseEntity<Client> findByNumber(@RequestParam String number){
        Integer parsedNumber = URL.convertInteger(number, null);
        Client client = service.findByNumber(parsedNumber);
        return ResponseEntity.ok().body(client);
    }


}
