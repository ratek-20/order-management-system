package com.company.ordermanagementsystem.entity;

import com.company.ordermanagementsystem.objectmother.OrderEntityObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderEntityTest {

    private OrderEntity orderEntity;

    @BeforeEach
    public void setup() {
        orderEntity = OrderEntityObjectMother.aRandomOrderEntity();
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