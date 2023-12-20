package com.company.ordermanagementsystem.controller;

import com.company.ordermanagementsystem.dto.CreateOrderRequest;
import com.company.ordermanagementsystem.dto.OrderDTO;
import com.company.ordermanagementsystem.exception.OrderNotFoundException;
import com.company.ordermanagementsystem.mapper.OrderMapper;
import com.company.ordermanagementsystem.port.in.OrderApiInPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderApiInPort orderApiInPort;

    @Autowired
    public OrderController(OrderMapper orderMapper, OrderApiInPort orderApiInPort) {
        this.orderMapper = orderMapper;
        this.orderApiInPort = orderApiInPort;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity
                .status(200)
                .body(orderApiInPort.getAllOrders().stream()
                        .map(orderMapper::toOrderDTO)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable UUID id) throws OrderNotFoundException {
        return ResponseEntity
                .status(200)
                .body(orderApiInPort.getOrderById(id)
                        .map(orderMapper::toOrderDTO)
                        .orElseThrow(() -> new OrderNotFoundException(id)));
    }

    @PostMapping
    public ResponseEntity<UUID> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return ResponseEntity
                .status(201)
                .body(orderApiInPort.createOrder(orderMapper.toOrder(createOrderRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        orderApiInPort.deleteOrder(id);
        return ResponseEntity
                .status(204)
                .build();
    }


}
