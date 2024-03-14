package com.maneira.mongoproject.demo.service.test;


import com.maneira.mongoproject.demo.domain.Client;
import com.maneira.mongoproject.demo.domain.Order;
import com.maneira.mongoproject.demo.domain.Product;
import com.maneira.mongoproject.demo.dto.ClientDTO;
import com.maneira.mongoproject.demo.dto.OrderItemDTO;
import com.maneira.mongoproject.demo.dto.ProductDTO;
import com.maneira.mongoproject.demo.repository.OrderRepository;
import com.maneira.mongoproject.demo.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;


}
