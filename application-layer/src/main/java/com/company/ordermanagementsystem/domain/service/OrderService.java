package com.company.ordermanagementsystem.domain.service;

import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.domain.model.OrderItem;
import com.company.ordermanagementsystem.domain.model.OrderStatus;
import com.company.ordermanagementsystem.port.in.OrderInPort;
import com.company.ordermanagementsystem.port.out.OrderOutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService implements OrderInPort {

    private final OrderOutPort orderOutPort;

    @Autowired
    public OrderService(OrderOutPort orderOutPort) {
        this.orderOutPort = orderOutPort;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderOutPort.getAllOrders();
    }

    @Override
    public Optional<Order> getOrderById(UUID id) {
        return orderOutPort.getOrderById(id);
    }

    @Override
    public UUID createOrder(Order order) {
        return orderOutPort.createOrder(order);
    }

    @Override
    public void deleteOrder(UUID id) {
        orderOutPort.deleteOrder(id);
    }
}
