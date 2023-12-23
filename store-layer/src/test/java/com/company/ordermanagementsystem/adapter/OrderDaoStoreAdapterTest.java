package com.company.ordermanagementsystem.adapter;

import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.domain.service.objectmother.OrderObjectMother;
import com.company.ordermanagementsystem.entity.OrderEntity;
import com.company.ordermanagementsystem.mapper.OrderEntityMapper;
import com.company.ordermanagementsystem.objectmother.OrderEntityObjectMother;
import com.company.ordermanagementsystem.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class OrderDaoStoreAdapterTest {

    @InjectMocks
    private OrderDaoStoreAdapter orderDaoStoreAdapter;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderEntityMapper orderEntityMapper;

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
    void itShouldGetAllOrders() {
        Order order = OrderObjectMother.aRandomOrder();
        OrderEntity orderEntity = OrderEntityObjectMother.aRandomOrderEntity();
        when(orderRepository.findAll()).thenReturn(List.of(orderEntity));
        when(orderEntityMapper.mapToModel(orderEntity)).thenReturn(order);
        List<Order> expected = List.of(order);

        List<Order> actual = orderDaoStoreAdapter.getAllOrders();

        assertEquals(expected, actual);
    }

    @Test
    void itShouldGetAnOrderById() {
        Order expected = OrderObjectMother.aRandomOrder();
        OrderEntity orderEntity = OrderEntityObjectMother.aRandomOrderEntity();
        when(orderRepository.findById(Mockito.any())).thenReturn(Optional.of(orderEntity));
        when(orderEntityMapper.mapToModel(orderEntity)).thenReturn(expected);
        UUID id = UUID.randomUUID();

        Optional<Order> actual = orderDaoStoreAdapter.getOrderById(id);

        assertEquals(expected, actual.get());
    }

    @Test
    void itShouldGetAnEmptyOptionalOrderById() {
        when(orderRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        UUID id = UUID.randomUUID();

        Optional<Order> actual = orderDaoStoreAdapter.getOrderById(id);

        assertTrue(actual.isEmpty());
    }

    @Test
    void itShouldCreateAnOrder() {
        Order order = OrderObjectMother.aRandomOrder();
        OrderEntity orderEntity = OrderEntityObjectMother.aRandomOrderEntity();
        when(orderEntityMapper.mapToEntity(order)).thenReturn(orderEntity);
        when(orderRepository.save(orderEntity)).thenReturn(orderEntity);
        UUID expected = orderEntity.getId();

        UUID actual = orderDaoStoreAdapter.createOrder(order);

        assertEquals(expected, actual);
    }

    @Test
    void itShouldDeleteAnOrder() {
        UUID id = UUID.randomUUID();

        orderDaoStoreAdapter.deleteOrder(id);

        verify(orderRepository).deleteById(id);
    }
}