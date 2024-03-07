package com.maneira.mongoproject.demo.dto;

import com.maneira.mongoproject.demo.domain.Order;
import com.maneira.mongoproject.demo.domain.OrderItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class OrderDTO {

    private String id;
    private ClientDTO client;
    private Date date;
    private Double total;
    private List<OrderItemDTO> items;

    public OrderDTO() {

    }

    public OrderDTO(String id, ClientDTO client, Date date, Double total, List<OrderItemDTO> items) {
        this.id = id;
        this.client = client;
        this.date = date;
        this.total = total;
        this.items = items;
    }



    private String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    private List<OrderItemDTO> convertOrderItemsToDTO(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItemDTO::new)
                .collect(toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public Order toEntity() throws ParseException {
        Order order = new Order(id, date, client, total);

        if (items != null) {
            Set<OrderItem> orderItems = new HashSet<>();
            for (OrderItemDTO item : items) {
                OrderItem entity = item.toEntity();
                orderItems.add(entity);  // Correção aqui
            }

            order.setItems(orderItems);
        }

        return order;
    }
    public static OrderItemDTO fromDto(OrderItem orderItemEntity) {
        ProductDTO productDto = (orderItemEntity.getProduct() != null) ? ProductDTO.fromEntity(orderItemEntity.getProduct()) : null;
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProduct(productDto);
        orderItemDTO.setQtd(orderItemEntity.getQtd());
        orderItemDTO.setPrice(orderItemEntity.getPrice());
        orderItemDTO.setTotal(orderItemEntity.getSubTotal());
        return orderItemDTO;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id='" + id + '\'' +
                ", client=" + client +
                ", date=" + date +
                ", total=" + total +
                ", items=" + items +
                '}';
    }
}
