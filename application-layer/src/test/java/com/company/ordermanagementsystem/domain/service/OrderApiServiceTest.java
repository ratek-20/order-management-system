package com.company.ordermanagementsystem.domain.service;

import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.domain.service.objectmother.OrderObjectMother;
import com.company.ordermanagementsystem.port.out.OrderDaoOutPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class OrderApiServiceTest {

    @InjectMocks
    private OrderApiService orderApiService;
    @Mock
    private OrderDaoOutPort orderDaoOutPort;

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
    public void testGetAllOrders() {
        List<Order> expected = Collections.singletonList(OrderObjectMother.aRandomOrder());
        when(orderDaoOutPort.getAllOrders()).thenReturn(expected);

        List<Order> actual = orderApiService.getAllOrders();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetOrderById() {
        UUID uuid = UUID.randomUUID();
        Order order = OrderObjectMother.aRandomOrder();
        Optional<Order> expected = Optional.of(order);
        when(orderDaoOutPort.getOrderById(uuid)).thenReturn(expected);

        Optional<Order> actual = orderApiService.getOrderById(uuid);

        assertEquals(expected, actual);
    }

    @Test
    public void testCreateOrder() {
        Order order = OrderObjectMother.aRandomOrder();
        UUID expected = UUID.randomUUID();
        when(orderDaoOutPort.createOrder(order)).thenReturn(expected);

        UUID actual = orderApiService.createOrder(order);

        verify(orderDaoOutPort).createOrder(order);
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteOrder() {
        UUID uuid = UUID.randomUUID();
        orderApiService.deleteOrder(uuid);

        verify(orderDaoOutPort).deleteOrder(uuid);
    }
}