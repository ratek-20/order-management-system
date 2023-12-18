package com.company.ordermanagementsystem.domain.service;

import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.domain.model.OrderItem;
import com.company.ordermanagementsystem.domain.model.OrderStatus;
import com.company.ordermanagementsystem.port.OrderUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService implements OrderUseCase {
    @Override
    public List<Order> getAllOrders() {
        return List.of(
                new Order(
                        UUID.randomUUID(),
                        OrderStatus.CONFIRMED,
                        LocalDateTime.of(2021, 1, 1, 12, 0),
                        List.of(
                                new OrderItem(UUID.randomUUID(), 1, BigDecimal.valueOf(100.23)),
                                new OrderItem(UUID.randomUUID(), 1, BigDecimal.valueOf(20.11)),
                                new OrderItem(UUID.randomUUID(), 3, BigDecimal.valueOf(0.87))
                        ),
                        BigDecimal.valueOf(121.32)
                ),
                new Order(
                        UUID.randomUUID(),
                        OrderStatus.DELIVERED,
                        LocalDateTime.of(2021, 1, 1, 12, 0),
                        List.of(
                                new OrderItem(UUID.randomUUID(), 1, BigDecimal.valueOf(13.78)),
                                new OrderItem(UUID.randomUUID(), 1, BigDecimal.valueOf(1.11))
                        ),
                        BigDecimal.valueOf(14.89)
                )
        );
    }

    @Override
    public Optional<Order> getOrderById(UUID id) {
        return Optional.empty();
    }

    @Override
    public UUID createOrder(Order order) {
        return UUID.randomUUID();
    }

    @Override
    public void deleteOrder(UUID id) {
    }
}
