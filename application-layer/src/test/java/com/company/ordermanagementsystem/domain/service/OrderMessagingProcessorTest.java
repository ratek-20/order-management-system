package com.company.ordermanagementsystem.domain.service;

import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.domain.model.OrderItem;
import com.company.ordermanagementsystem.domain.model.OrderStatus;
import com.company.ordermanagementsystem.port.out.OrderApiOutPort;
import com.company.ordermanagementsystem.port.out.OrderDaoOutPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderMessagingProcessorTest {

    private OrderMessagingProcessor orderMessagingProcessor;
    private OrderDaoOutPort orderDaoOutPort;
    private OrderApiOutPort orderApiOutPort;

    @BeforeEach
    public void setup() {
        orderDaoOutPort = Mockito.mock(OrderDaoOutPort.class);
        orderApiOutPort = Mockito.mock(OrderApiOutPort.class);
        orderMessagingProcessor = new OrderMessagingProcessor(orderDaoOutPort, orderApiOutPort);
    }

    @Test
    public void testProcess() {
        UUID uuid = UUID.randomUUID();
        Order order = Mockito.mock(Order.class);
        OrderItem item = Mockito.mock(OrderItem.class);
        when(order.getItems()).thenReturn(Collections.singletonList(item));
        when(orderDaoOutPort.getOrderById(uuid)).thenReturn(java.util.Optional.of(order));

        orderMessagingProcessor.process(uuid, OrderStatus.CONFIRMED);

        verify(orderDaoOutPort).updateOrder(order);
        verify(orderApiOutPort).updateInventory(item.getProductId(), item.getQuantity());
    }
}