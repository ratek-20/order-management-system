package com.company.ordermanagementsystem.controller;

import com.company.ordermanagementsystem.dto.CreateOrderRequest;
import com.company.ordermanagementsystem.dto.OrderDTO;
import com.company.ordermanagementsystem.exception.OrderNotFoundException;
import com.company.ordermanagementsystem.mapper.OrderMapper;
import com.company.ordermanagementsystem.port.OrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderUseCase orderUseCase;

    @Autowired
    public OrderController(OrderMapper orderMapper, OrderUseCase orderUseCase) {
        this.orderMapper = orderMapper;
        this.orderUseCase = orderUseCase;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity
                .status(200)
                .body(orderUseCase.getAllOrders().stream()
                        .map(orderMapper::toOrderDTO)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable UUID id) throws OrderNotFoundException {
        return ResponseEntity
                .status(200)
                .body(orderUseCase.getOrderById(id)
                        .map(orderMapper::toOrderDTO)
                        .orElseThrow(() -> new OrderNotFoundException(id)));
    }

    @PostMapping
    public ResponseEntity<UUID> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return ResponseEntity
                .status(201)
                .body(orderUseCase.createOrder(orderMapper.toOrder(createOrderRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        orderUseCase.deleteOrder(id);
        return ResponseEntity
                .status(204)
                .build();
    }


}
