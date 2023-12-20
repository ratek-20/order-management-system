package com.company.ordermanagementsystem.mapper;

import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.domain.model.OrderStatus;
import com.company.ordermanagementsystem.domain.service.objectmother.OrderObjectMother;
import com.company.ordermanagementsystem.dto.CreateOrderRequest;
import com.company.ordermanagementsystem.dto.OrderDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderMapperTest {

    private OrderMapper orderMapper;
    private OrderItemMapper orderItemMapper;

    @BeforeEach
    public void setup() {
        orderItemMapper = Mockito.mock(OrderItemMapper.class);
        orderMapper = new OrderMapper(orderItemMapper);
    }

    @Test
    public void testToOrder() {
        CreateOrderRequest createOrderRequest = Mockito.mock(CreateOrderRequest.class);
        Mockito.when(createOrderRequest.customerId()).thenReturn(UUID.randomUUID());
        Mockito.when(createOrderRequest.items()).thenReturn(Collections.emptyList());

        Order order = orderMapper.toOrder(createOrderRequest);

        assertEquals(createOrderRequest.customerId(), order.getCustomerId());
    }

    @Test
    public void testToOrderDTO() {
        Order order = OrderObjectMother.aRandomOrder();

        order.setId(UUID.randomUUID());
        order.setCustomerId(UUID.randomUUID());
        order.setStatus(OrderStatus.SHIPPED);

        OrderDTO orderDTO = orderMapper.toOrderDTO(order);

        assertEquals(order.getId(), orderDTO.id());
        assertEquals(order.getCustomerId(), orderDTO.customerId());
        assertEquals(order.getStatus().name(), orderDTO.status());
        assertNotNull(orderDTO.createdAt());
        assertEquals(order.getItems().size(), orderDTO.items().size());
        assertEquals(order.getTotalAmount().doubleValue(), orderDTO.totalAmount());
    }

}