package com.maneira.mongoproject.demo.resources;

import com.maneira.mongoproject.demo.domain.Order;
import com.maneira.mongoproject.demo.domain.enums.OrderStatus;
import com.maneira.mongoproject.demo.dto.OrderDTO;
import com.maneira.mongoproject.demo.resources.util.URL;
import com.maneira.mongoproject.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderResource {
    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAll();
        return ResponseEntity.ok().body(orders);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<OrderDTO> insert(@RequestBody OrderDTO objDTO)  {
        Order obj = orderService.fromDto(objDTO);
        obj = orderService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        Order order = orderService.findById(id);
        return  ResponseEntity.ok().body(order);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "findByTotal", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> searchByTotalRange(
            @RequestParam(required = false) String minTotal,
            @RequestParam(required = false) String maxTotal) {

        Double parsedMinTotal = URL.convertDouble(minTotal, null);
        Double parsedMaxTotal = URL.convertDouble(maxTotal, null);

        List<Order> list = orderService.findByTotalRange(parsedMinTotal, parsedMaxTotal);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "findByNumber", method = RequestMethod.GET)
    public ResponseEntity<Order> searchByNumber(@RequestParam String number){
        Integer parsedNumber = URL.convertInteger(number, null);
        Order result = orderService.numberSearch(parsedNumber);
        return ResponseEntity.ok().body(result);
    }

    @RequestMapping(value = "/{id}/status", method = RequestMethod.PUT)
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable String id,
            @RequestParam Integer status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
        OrderStatus newStatus = OrderStatus.valueOf(status);
        Order updatedOrder = orderService.updateOrderStatus(id, newStatus, date);
        return ResponseEntity.ok().body(updatedOrder);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody OrderDTO objDTO, @PathVariable String id) {
        Order obj = orderService.fromDto(objDTO);
        obj.setId(id);
        obj = orderService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/fullSearch", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> fullSearch(
            @RequestParam(defaultValue = "") String text,
            @RequestParam(defaultValue = "") String minDate,
            @RequestParam(defaultValue = "") String maxDate,
            @RequestParam(defaultValue = "0.0") Double minTotal,
            @RequestParam(defaultValue = "100000.0") Double maxTotal,
            @RequestParam(defaultValue = "") String client,
            @RequestParam(defaultValue = "") String product,
            @RequestParam(defaultValue = "date", required = false) String orderBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {

        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), orderBy);

        List<Order> result = orderService.fullSearch(text, min, max, minTotal, maxTotal, client, product, sort);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}