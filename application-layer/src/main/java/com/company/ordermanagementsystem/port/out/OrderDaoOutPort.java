package com.company.ordermanagementsystem.port.out;

import com.company.ordermanagementsystem.domain.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderDaoOutPort {

    List<Order> getAllOrders();

    Optional<Order> getOrderById(UUID id);

    UUID createOrder(Order order);

    void deleteOrder(UUID id);

    void updateOrder(Order order);
}
