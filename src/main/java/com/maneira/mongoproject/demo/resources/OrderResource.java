package com.maneira.mongoproject.demo.resources;

import com.maneira.mongoproject.demo.domain.Order;
import com.maneira.mongoproject.demo.resources.util.URL;
import com.maneira.mongoproject.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        Order order = orderService.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/clientsearch", method=RequestMethod.GET)
    public ResponseEntity<List<Order>> findByClient(@RequestParam(value = "text", defaultValue = "") String clientName) {
        clientName = URL.decodeParam(clientName);
        List<Order> list = orderService.findByClient(clientName);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/findclientbyproduct", method=RequestMethod.GET)
    public ResponseEntity<List<Order>> findByProduct(@RequestParam(value = "text", defaultValue = "") String productName){
        productName = URL.decodeParam(productName);
        List<Order> list = orderService.findByProduct(productName);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/findByDate", method=RequestMethod.GET)
    public ResponseEntity<List<Order>> findByDate(@RequestParam(value = "minDate", required = false) String minDate,
                                                  @RequestParam(value = "maxDate", required = false) String maxDate) {
        Date startDate = URL.convertDate(minDate, new Date(0L));
        Date endDate = URL.convertDate(maxDate, new Date());

        List<Order> list = orderService.findOrdersByDateRange(startDate, endDate);

        return ResponseEntity.ok().body(list);
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

    @RequestMapping(value = "/fullSearch", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> fullSearch(
            @RequestParam(defaultValue = "") String text,
            @RequestParam(defaultValue = "") String minDate,
            @RequestParam(defaultValue = "") String maxDate,
            @RequestParam(defaultValue = "0.0") Double minTotal,
            @RequestParam(defaultValue = "100000.0") Double maxTotal,
            @RequestParam(defaultValue = "") String client,
            @RequestParam(defaultValue = "") String product) {

        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());

        List<Order> result = orderService.fullSearch(text, min, max, minTotal, maxTotal, client, product);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
