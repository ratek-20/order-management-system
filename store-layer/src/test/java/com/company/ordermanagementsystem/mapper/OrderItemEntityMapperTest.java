package com.company.ordermanagementsystem.mapper;

import com.company.ordermanagementsystem.domain.model.OrderItem;
import com.company.ordermanagementsystem.entity.OrderItemEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderItemEntityMapperTest {

    private OrderItemEntityMapper orderItemEntityMapper;

    @BeforeEach
    public void setup() {
        orderItemEntityMapper = new OrderItemEntityMapper();
    }

    @Test
    public void testMapToModel() {
        OrderItemEntity orderItemEntity = Mockito.mock(OrderItemEntity.class);
        Mockito.when(orderItemEntity.getId()).thenReturn(UUID.randomUUID());
        Mockito.when(orderItemEntity.getQuantity()).thenReturn(2);
        Mockito.when(orderItemEntity.getUnitPrice()).thenReturn(BigDecimal.valueOf(100.0));

        OrderItem orderItem = orderItemEntityMapper.mapToModel(orderItemEntity);

        assertEquals(orderItemEntity.getId(), orderItem.getProductId());
        assertEquals(orderItemEntity.getQuantity(), orderItem.getQuantity());
        assertEquals(orderItemEntity.getUnitPrice(), orderItem.getUnitPrice());
    }

    @Test
    public void testMapToEntity() {
        OrderItem orderItem = Mockito.mock(OrderItem.class);
        Mockito.when(orderItem.getProductId()).thenReturn(UUID.randomUUID());
        Mockito.when(orderItem.getQuantity()).thenReturn(2);
        Mockito.when(orderItem.getUnitPrice()).thenReturn(BigDecimal.valueOf(100.0));

        OrderItemEntity orderItemEntity = orderItemEntityMapper.mapToEntity(orderItem);

        assertEquals(orderItem.getProductId(), orderItemEntity.getId());
        assertEquals(orderItem.getQuantity(), orderItemEntity.getQuantity());
        assertEquals(orderItem.getUnitPrice(), orderItemEntity.getUnitPrice());
    }
}