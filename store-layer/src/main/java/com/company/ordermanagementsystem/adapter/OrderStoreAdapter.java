package com.company.ordermanagementsystem.adapter;

import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.mapper.OrderEntityMapper;
import com.company.ordermanagementsystem.port.out.OrderOutPort;
import com.company.ordermanagementsystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OrderStoreAdapter implements OrderOutPort {

    private final OrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Autowired
    public OrderStoreAdapter(OrderRepository orderRepository, OrderEntityMapper orderEntityMapper) {
        this.orderRepository = orderRepository;
        this.orderEntityMapper = orderEntityMapper;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderEntityMapper::mapToModel)
                .toList();
    }

    @Override
    public Optional<Order> getOrderById(UUID id) {
        return orderRepository.findById(id).map(orderEntityMapper::mapToModel);
    }

    @Override
    public UUID createOrder(Order order) {
        return orderRepository.save(orderEntityMapper.mapToEntity(order)).getId();
    }

    @Override
    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }
}
