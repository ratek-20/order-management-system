package com.company.ordermanagementsystem.domain.service;

import com.company.ordermanagementsystem.domain.exception.OrderNotFoundException;
import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.domain.model.OrderItem;
import com.company.ordermanagementsystem.domain.model.OrderStatus;
import com.company.ordermanagementsystem.domain.service.objectmother.OrderItemObjectMother;
import com.company.ordermanagementsystem.port.out.OrderApiOutPort;
import com.company.ordermanagementsystem.port.out.OrderDaoOutPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class OrderMessagingProcessorTest {

    @InjectMocks
    private OrderMessagingProcessor orderMessagingProcessor;

    @Mock
    private OrderDaoOutPort orderDaoOutPort;

    @Mock
    private OrderApiOutPort orderApiOutPort;

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
    public void itShouldTestTheHappyPath() {
        UUID uuid = UUID.randomUUID();
        Order order = mock(Order.class);
        OrderItem item = OrderItemObjectMother.aRandomOrderItem();
        when(orderDaoOutPort.getOrderById(uuid)).thenReturn(Optional.of(order));
        when(order.getItems()).thenReturn(Collections.singletonList(item));

        orderMessagingProcessor.process(uuid, OrderStatus.CONFIRMED);

        verify(orderDaoOutPort).updateOrder(order);
        verify(orderApiOutPort).updateInventory(item.getProductId(), item.getQuantity());
    }

    @Test
    void itShouldThrowAnOrderNotFoundException() {
        UUID uuid = UUID.randomUUID();
        when(orderDaoOutPort.getOrderById(uuid)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderMessagingProcessor.process(uuid, OrderStatus.CONFIRMED));
    }

    @Test
    void itShouldNotUpdateTheInventory() {
        UUID uuid = UUID.randomUUID();
        Order order = mock(Order.class);
        when(orderDaoOutPort.getOrderById(uuid)).thenReturn(Optional.of(order));
        when(order.getItems()).thenReturn(Collections.emptyList());

        orderMessagingProcessor.process(uuid, OrderStatus.PENDING);

        verify(orderDaoOutPort).updateOrder(order);
        verify(orderApiOutPort, never()).updateInventory(any(UUID.class), anyInt());
    }
}