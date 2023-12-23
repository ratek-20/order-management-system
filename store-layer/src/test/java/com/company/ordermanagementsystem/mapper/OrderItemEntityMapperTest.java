package com.company.ordermanagementsystem.mapper;

import com.company.ordermanagementsystem.domain.model.OrderItem;
import com.company.ordermanagementsystem.domain.service.objectmother.OrderItemObjectMother;
import com.company.ordermanagementsystem.entity.OrderEntity;
import com.company.ordermanagementsystem.entity.OrderItemEntity;
import com.company.ordermanagementsystem.objectmother.OrderEntityObjectMother;
import com.company.ordermanagementsystem.objectmother.OrderItemEntityObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderItemEntityMapperTest {

    private OrderItemEntityMapper orderItemEntityMapper;


    @BeforeEach
    public void setUp() {
        orderItemEntityMapper = new OrderItemEntityMapper();
    }

    @Test
    void itShouldMapToModel() {
        OrderItemEntity orderItemEntity = OrderItemEntityObjectMother.aRandomOrderItemEntity();
        UUID expectedId = orderItemEntity.getId();
        int expectedQuantity = orderItemEntity.getQuantity();
        BigDecimal expectedUnitPrice = orderItemEntity.getUnitPrice();

        OrderItem orderItem = orderItemEntityMapper.mapToModel(orderItemEntity);
        UUID actualId = orderItem.getProductId();
        int actualQuantity = orderItem.getQuantity();
        BigDecimal actualUnitPrice = orderItem.getUnitPrice();

        assertEquals(expectedId, actualId);
        assertEquals(expectedQuantity, actualQuantity);
        assertEquals(expectedUnitPrice, actualUnitPrice);
    }

    @Test
    void itShouldMapToEntity() {
        OrderItem orderItem = OrderItemObjectMother.aRandomOrderItem();
        UUID expectedId = orderItem.getProductId();
        int expectedQuantity = orderItem.getQuantity();
        BigDecimal expectedUnitPrice = orderItem.getUnitPrice();

        OrderItemEntity orderItemEntity = orderItemEntityMapper.mapToEntity(orderItem);
        UUID actualId = orderItemEntity.getId();
        int actualQuantity = orderItemEntity.getQuantity();
        BigDecimal actualUnitPrice = orderItemEntity.getUnitPrice();

        assertEquals(expectedId, actualId);
        assertEquals(expectedQuantity, actualQuantity);
        assertEquals(expectedUnitPrice, actualUnitPrice);
    }
}