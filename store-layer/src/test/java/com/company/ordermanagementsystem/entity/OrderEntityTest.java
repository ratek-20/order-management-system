package com.company.ordermanagementsystem.entity;

import com.company.ordermanagementsystem.domain.model.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderEntityTest {

    private OrderEntity orderEntity;

    @BeforeEach
    public void setup() {
        orderEntity = new OrderEntity(UUID.randomUUID(), OrderStatus.SHIPPED, LocalDateTime.now(), BigDecimal.valueOf(100.0));
    }

    @Test
    public void testAddItem() {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderEntity.addItem(orderItemEntity);

        assertTrue(orderEntity.getItems().contains(orderItemEntity));
    }

    @Test
    public void testRemoveItem() {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderEntity.addItem(orderItemEntity);
        orderEntity.removeItem(orderItemEntity);

        assertFalse(orderEntity.getItems().contains(orderItemEntity));
    }
}