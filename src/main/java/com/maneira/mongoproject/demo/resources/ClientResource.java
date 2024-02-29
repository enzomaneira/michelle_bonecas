package com.maneira.mongoproject.demo.resources;
;
import com.maneira.mongoproject.demo.domain.Client;
import com.maneira.mongoproject.demo.dto.ClientDTO;
import com.maneira.mongoproject.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/client")
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


}
