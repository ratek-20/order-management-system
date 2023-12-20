package com.company.ordermanagementsystem.domain.service;

import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.port.out.OrderDaoOutPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderApiServiceTest {

    private OrderApiService orderApiService;
    private OrderDaoOutPort orderDaoOutPort;

    @BeforeEach
    public void setup() {
        orderDaoOutPort = Mockito.mock(OrderDaoOutPort.class);
        orderApiService = new OrderApiService(orderDaoOutPort);
    }

    @Test
    public void testGetAllOrders() {
        List<Order> orders = Collections.singletonList(Mockito.mock(Order.class));
        when(orderDaoOutPort.getAllOrders()).thenReturn(orders);

        List<Order> result = orderApiService.getAllOrders();

        assertEquals(orders, result);
    }

    @Test
    public void testGetOrderById() {
        UUID uuid = UUID.randomUUID();
        Order order = Mockito.mock(Order.class);
        when(orderDaoOutPort.getOrderById(uuid)).thenReturn(Optional.of(order));

        Optional<Order> result = orderApiService.getOrderById(uuid);

        assertEquals(Optional.of(order), result);
    }

    @Test
    public void testCreateOrder() {
        Order order = Mockito.mock(Order.class);
        UUID uuid = UUID.randomUUID();
        when(orderDaoOutPort.createOrder(order)).thenReturn(uuid);

        UUID result = orderApiService.createOrder(order);

        verify(orderDaoOutPort).createOrder(order);
        assertEquals(uuid, result);
    }

    @Test
    public void testDeleteOrder() {
        UUID uuid = UUID.randomUUID();
        orderApiService.deleteOrder(uuid);

        verify(orderDaoOutPort).deleteOrder(uuid);
    }
}