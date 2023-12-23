package com.company.ordermanagementsystem.mapper;

import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.domain.model.OrderItem;
import com.company.ordermanagementsystem.domain.model.OrderStatus;
import com.company.ordermanagementsystem.domain.service.objectmother.OrderItemObjectMother;
import com.company.ordermanagementsystem.domain.service.objectmother.OrderObjectMother;
import com.company.ordermanagementsystem.entity.OrderEntity;
import com.company.ordermanagementsystem.entity.OrderItemEntity;
import com.company.ordermanagementsystem.objectmother.OrderEntityObjectMother;
import com.company.ordermanagementsystem.objectmother.OrderItemEntityObjectMother;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class OrderEntityMapperTest {

    @InjectMocks
    private OrderEntityMapper orderEntityMapper;

    @Mock
    private OrderItemEntityMapper orderItemEntityMapper;

    private AutoCloseable autoCloseable;

    @BeforeEach
    public void setup() {
        autoCloseable = openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void itShouldMapToEntity() {
        Order order = OrderObjectMother.aRandomOrder();
        OrderItem orderItem = OrderItemObjectMother.aRandomOrderItem();
        order.setItems(Collections.singletonList(orderItem));
        OrderItemEntity orderItemEntity = OrderItemEntityObjectMother.aRandomOrderItemEntity();
        when(orderItemEntityMapper.mapToEntity(orderItem)).thenReturn(orderItemEntity);
        UUID expectedCustomerId = order.getCustomerId();
        OrderStatus expectedStatus = order.getStatus();
        LocalDateTime expectedCreatedAt = order.getCreatedAt();
        BigDecimal expectedTotalAmount = order.getTotalAmount();

        OrderEntity orderEntity = orderEntityMapper.mapToEntity(order);
        UUID actualCustomerId = orderEntity.getCustomerId();
        OrderStatus actualStatus = orderEntity.getStatus();
        LocalDateTime actualCreatedAt = orderEntity.getCreatedAt();
        BigDecimal actualTotalAmount = orderEntity.getTotalAmount();

        assertEquals(expectedCustomerId, actualCustomerId);
        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedCreatedAt, actualCreatedAt);
        assertEquals(expectedTotalAmount, actualTotalAmount);
        assertTrue(orderEntity.getItems().contains(orderItemEntity));
        assertEquals(1, orderEntity.getItems().size());
    }

    @Test
    void itShouldMapToModel() {
        OrderEntity orderEntity = OrderEntityObjectMother.aRandomOrderEntity();
        OrderItemEntity orderItemEntity = OrderItemEntityObjectMother.aRandomOrderItemEntity();
        orderEntity.setItems(Collections.singletonList(orderItemEntity));
        OrderItem orderItem = OrderItemObjectMother.aRandomOrderItem();
        when(orderItemEntityMapper.mapToModel(orderItemEntity)).thenReturn(orderItem);
        UUID expectedCustomerId = orderEntity.getCustomerId();
        OrderStatus expectedStatus = orderEntity.getStatus();
        LocalDateTime expectedCreatedAt = orderEntity.getCreatedAt();
        BigDecimal expectedTotalAmount = orderEntity.getTotalAmount();

        Order order = orderEntityMapper.mapToModel(orderEntity);
        UUID actualCustomerId = order.getCustomerId();
        OrderStatus actualStatus = order.getStatus();
        LocalDateTime actualCreatedAt = order.getCreatedAt();
        BigDecimal actualTotalAmount = order.getTotalAmount();

        assertEquals(expectedCustomerId, actualCustomerId);
        assertEquals(expectedStatus, actualStatus);
        assertEquals(expectedCreatedAt, actualCreatedAt);
        assertEquals(expectedTotalAmount, actualTotalAmount);
        assertTrue(order.getItems().contains(orderItem));
        assertEquals(1, order.getItems().size());
    }
}