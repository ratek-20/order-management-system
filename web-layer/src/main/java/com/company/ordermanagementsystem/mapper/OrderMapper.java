package com.company.ordermanagementsystem.mapper;


import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.domain.model.OrderStatus;
import com.company.ordermanagementsystem.dto.CreateOrderRequest;
import com.company.ordermanagementsystem.dto.OrderDTO;
import com.company.ordermanagementsystem.dto.OrderItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    @Autowired
    public OrderMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    public Order toOrder(CreateOrderRequest createOrderRequest) {
        return new Order(createOrderRequest.customerId(),
                OrderStatus.PENDING,
                LocalDateTime.now(),
                createOrderRequest.items().stream()
                        .map(orderItemMapper::toOrderItem)
                        .toList(),
                createOrderRequest.items().stream()
                        .map(OrderItemDTO::unitPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    public OrderDTO toOrderDTO(Order order) {
        return new OrderDTO(order.getId(),
                order.getCustomerId(),
                order.getStatus().name(),
                order.getOrderDateTime(),
                order.getItems().stream()
                        .map(orderItemMapper::toOrderItemDTO)
                        .toList(),
                order.getTotalAmount().doubleValue());
    }
}
