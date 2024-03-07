package com.maneira.mongoproject.demo.resources;
;
import com.maneira.mongoproject.demo.domain.Client;
import com.maneira.mongoproject.demo.dto.ClientDTO;
import com.maneira.mongoproject.demo.resources.util.URL;
import com.maneira.mongoproject.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println("Dados do cliente recebidos no servidor: " + objDto.toString());
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
    public ResponseEntity<List<Client>> findByNameAndContact(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "contact", required = false) String contact) {
        if (name != null) name = URL.decodeParam(name);
        if (contact != null) contact = URL.decodeParam(contact);

        List<Client> list;

        if (name != null && contact != null) {
            list = service.findByNameAndContact(name, contact);
        } else if (name != null) {
            list = service.findByName(name);
        } else if (contact != null) {
            list = service.findByContact(contact);
        } else {
            // Ambos são nulos, você decide como lidar com esse caso
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().body(list);
    }


}
