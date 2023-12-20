package com.company.ordermanagementsystem.controller;

import com.company.ordermanagementsystem.controller.objectmother.CreateOrderRequestObjectMother;
import com.company.ordermanagementsystem.controller.objectmother.OrderDTOObjectMother;
import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.domain.service.objectmother.OrderObjectMother;
import com.company.ordermanagementsystem.dto.CreateOrderRequest;
import com.company.ordermanagementsystem.dto.OrderDTO;
import com.company.ordermanagementsystem.exception.OrderNotFoundException;
import com.company.ordermanagementsystem.mapper.OrderMapper;
import com.company.ordermanagementsystem.port.in.OrderApiInPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class OrderControllerTest {

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private OrderApiInPort orderApiInPort;

    private OrderController orderController;

    private AutoCloseable autoCloseable;

    @BeforeEach
    public void setUp() {
        autoCloseable = openMocks(this);
        orderController = new OrderController(orderMapper, orderApiInPort);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void testGetAllOrders() {
        OrderDTO orderDTO = OrderDTOObjectMother.aRandomOrderDTO();
        Order order = OrderObjectMother.aRandomOrder();
        when(orderApiInPort.getAllOrders()).thenReturn(Collections.singletonList(order));
        when(orderMapper.toOrderDTO(any(Order.class))).thenReturn(orderDTO);

        ResponseEntity<List<OrderDTO>> response = orderController.getAllOrders();

        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void testGetOrder() throws OrderNotFoundException {
        UUID id = UUID.randomUUID();
        OrderDTO orderDTO = OrderDTOObjectMother.aRandomOrderDTO();
        when(orderApiInPort.getOrderById(id)).thenReturn(Optional.of(OrderObjectMother.aRandomOrder()));
        when(orderMapper.toOrderDTO(any(Order.class))).thenReturn(orderDTO);

        ResponseEntity<OrderDTO> response = orderController.getOrder(id);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(orderDTO, response.getBody());
    }

    @Test
    public void testCreateOrder() {
        CreateOrderRequest createOrderRequest = CreateOrderRequestObjectMother.aRandomCreateOrderRequest();
        UUID id = UUID.randomUUID();
        when(orderMapper.toOrder(createOrderRequest)).thenReturn(OrderObjectMother.aRandomOrder());
        when(orderApiInPort.createOrder(any(Order.class))).thenReturn(id);

        ResponseEntity<UUID> response = orderController.createOrder(createOrderRequest);

        assertEquals(201, response.getStatusCodeValue());
        assertEquals(id, response.getBody());
    }

    @Test
    public void testDeleteOrder() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = orderController.deleteOrder(id);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        verify(orderApiInPort, times(1)).deleteOrder(id);
    }
}