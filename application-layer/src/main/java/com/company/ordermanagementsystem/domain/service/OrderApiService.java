package com.company.ordermanagementsystem.domain.service;

import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.port.in.OrderApiInPort;
import com.company.ordermanagementsystem.port.out.OrderDaoOutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderApiService implements OrderApiInPort {

    private final OrderDaoOutPort orderDaoOutPort;

    @Autowired
    public OrderApiService(OrderDaoOutPort orderDaoOutPort) {
        this.orderDaoOutPort = orderDaoOutPort;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDaoOutPort.getAllOrders();
    }

    @Override
    public Optional<Order> getOrderById(UUID id) {
        return orderDaoOutPort.getOrderById(id);
    }

    @Override
    public UUID createOrder(Order order) {
        return orderDaoOutPort.createOrder(order);
    }

    @Override
    public void deleteOrder(UUID id) {
        orderDaoOutPort.deleteOrder(id);
    }

}
