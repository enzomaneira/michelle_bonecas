package com.maneira.mongoproject.demo.config;

import com.maneira.mongoproject.demo.domain.Client;
import com.maneira.mongoproject.demo.repository.ClientRepository;
import com.maneira.mongoproject.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import java.text.SimpleDateFormat;


import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private ClientRepository ClientRepository;

    @Autowired
    private ClientService clientService;


    @Override
    public void run(String... arg0) throws Exception {

        ClientRepository.deleteAll();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        Client maria = new Client(null, "Maria Brown", "maria@gmail.com", 0.0);
        Client alex = new Client(null, "Alex Green", "alex@gmail.com", 0.0);
        Client bob = new Client(null, "Bob Grey", "bob@gmail.com", 0.0);

        ClientRepository.saveAll(Arrays.asList(maria, alex, bob));


    }
}
