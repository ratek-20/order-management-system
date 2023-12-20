package com.company.ordermanagementsystem.mapper;

import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.domain.model.OrderStatus;
import com.company.ordermanagementsystem.entity.OrderEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OrderEntityMapperTest {

    private OrderEntityMapper orderEntityMapper;
    private OrderItemEntityMapper orderItemEntityMapper;

    @BeforeEach
    public void setup() {
        orderItemEntityMapper = Mockito.mock(OrderItemEntityMapper.class);
        orderEntityMapper = new OrderEntityMapper(orderItemEntityMapper);
    }

    @Test
    public void testMapToEntity() {
        Order order = Mockito.mock(Order.class);
        when(order.getCustomerId()).thenReturn(UUID.randomUUID());
        when(order.getStatus()).thenReturn(OrderStatus.SHIPPED);
        when(order.getCreatedAt()).thenReturn(LocalDateTime.now());
        when(order.getTotalAmount()).thenReturn(BigDecimal.valueOf(100.0));
        when(order.getItems()).thenReturn(Collections.emptyList());

        OrderEntity orderEntity = orderEntityMapper.mapToEntity(order);

        assertEquals(order.getCustomerId(), orderEntity.getCustomerId());
        assertEquals(order.getStatus(), orderEntity.getStatus());
        assertEquals(order.getCreatedAt(), orderEntity.getCreatedAt());
        assertEquals(order.getTotalAmount(), orderEntity.getTotalAmount());
    }

    @Test
    public void testMapToModel() {
        OrderEntity orderEntity = Mockito.mock(OrderEntity.class);
            when(orderEntity.getCustomerId()).thenReturn(UUID.randomUUID());
        when(orderEntity.getStatus()).thenReturn(OrderStatus.CANCELLED);
        when(orderEntity.getCreatedAt()).thenReturn(LocalDateTime.now());
        when(orderEntity.getTotalAmount()).thenReturn(BigDecimal.valueOf(100.0));
        when(orderEntity.getItems()).thenReturn(Collections.emptyList());

        Order order = orderEntityMapper.mapToModel(orderEntity);

        assertEquals(orderEntity.getCustomerId(), order.getCustomerId());
        assertEquals(orderEntity.getStatus(), order.getStatus());
        assertEquals(orderEntity.getCreatedAt(), order.getCreatedAt());
        assertEquals(orderEntity.getTotalAmount(), order.getTotalAmount());
    }
}