package com.maneira.mongoproject.demo.resources;

import com.maneira.mongoproject.demo.domain.OrderItem;
import com.maneira.mongoproject.demo.dto.OrderItemDTO;
import com.maneira.mongoproject.demo.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItems")
@CrossOrigin
public class OrderItemResource {

    @Autowired
    private OrderItemService orderItemService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<OrderItem>> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemService.findAll();
        return ResponseEntity.ok().body(orderItems);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable String id) {
        OrderItem orderItem = orderItemService.findById(id);
        if (orderItem != null) {
            return ResponseEntity.ok().body(orderItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        OrderItem orderItem = orderItemDTO.fromDto(orderItemDTO);
        orderItem = orderItemService.insert(orderItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItem);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OrderItem> updateOrderItem(
            @PathVariable String id,
            @RequestBody OrderItemDTO orderItemDTO) {
        OrderItem orderItem = orderItemDTO.fromDto(orderItemDTO);
        orderItem.setId(id);
        orderItem = orderItemService.update(orderItem);
        return ResponseEntity.ok().body(orderItem);
    }

}
