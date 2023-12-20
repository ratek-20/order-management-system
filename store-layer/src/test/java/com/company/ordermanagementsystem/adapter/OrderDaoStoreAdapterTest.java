package com.company.ordermanagementsystem.adapter;

import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.entity.OrderEntity;
import com.company.ordermanagementsystem.mapper.OrderEntityMapper;
import com.company.ordermanagementsystem.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderDaoStoreAdapterTest {

    private OrderDaoStoreAdapter orderDaoStoreAdapter;
    private OrderRepository orderRepository;
    private OrderEntityMapper orderEntityMapper;

    @BeforeEach
    public void setup() {
        orderRepository = Mockito.mock(OrderRepository.class);
        orderEntityMapper = Mockito.mock(OrderEntityMapper.class);
        orderDaoStoreAdapter = new OrderDaoStoreAdapter(orderRepository, orderEntityMapper);
    }

    @Test
    public void testCreateOrder() {
        Order order = Mockito.mock(Order.class);
        when(orderEntityMapper.mapToEntity(order)).thenReturn(new OrderEntity());
        when(orderRepository.save(Mockito.any())).thenReturn(new OrderEntity());

        UUID result = orderDaoStoreAdapter.createOrder(order);

        verify(orderRepository).save(Mockito.any());
    }

    @Test
    public void testGetOrderById() {
        UUID uuid = UUID.randomUUID();
        Order order = Mockito.mock(Order.class);
        when(orderRepository.findById(uuid)).thenReturn(Optional.of(new OrderEntity()));
        when(orderEntityMapper.mapToModel(Mockito.any())).thenReturn(order);

        Optional<Order> result = orderDaoStoreAdapter.getOrderById(uuid);

        assertNotNull(Optional.of(result));
    }
}