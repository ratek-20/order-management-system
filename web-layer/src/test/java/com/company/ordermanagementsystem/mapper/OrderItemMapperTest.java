package com.company.ordermanagementsystem.mapper;

import com.company.ordermanagementsystem.domain.model.OrderItem;
import com.company.ordermanagementsystem.dto.OrderItemDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderItemMapperTest {

    private OrderItemMapper orderItemMapper;

    @BeforeEach
    public void setup() {
        orderItemMapper = new OrderItemMapper();
    }

    @Test
    public void testToOrderItem() {
        OrderItemDTO orderItemDTO = Mockito.mock(OrderItemDTO.class);
        Mockito.when(orderItemDTO.productId()).thenReturn(UUID.randomUUID());
        Mockito.when(orderItemDTO.quantity()).thenReturn(2);
        Mockito.when(orderItemDTO.unitPrice()).thenReturn(BigDecimal.valueOf(100.0));

        OrderItem orderItem = orderItemMapper.toOrderItem(orderItemDTO);

        assertEquals(orderItemDTO.productId(), orderItem.getProductId());
        assertEquals(orderItemDTO.quantity(), orderItem.getQuantity());
        assertEquals(orderItemDTO.unitPrice(), orderItem.getUnitPrice());
    }

    @Test
    public void testToOrderItemDTO() {
        OrderItem orderItem = Mockito.mock(OrderItem.class);
        Mockito.when(orderItem.getProductId()).thenReturn(UUID.randomUUID());
        Mockito.when(orderItem.getQuantity()).thenReturn(2);
        Mockito.when(orderItem.getUnitPrice()).thenReturn(BigDecimal.valueOf(100.0));

        OrderItemDTO orderItemDTO = orderItemMapper.toOrderItemDTO(orderItem);

        assertEquals(orderItem.getProductId(), orderItemDTO.productId());
        assertEquals(orderItem.getQuantity(), orderItemDTO.quantity());
        assertEquals(orderItem.getUnitPrice(), orderItemDTO.unitPrice());
    }
}