package com.company.ordermanagementsystem.mapper;

import com.company.ordermanagementsystem.domain.model.OrderItem;
import com.company.ordermanagementsystem.dto.OrderItemDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItem toOrderItem(OrderItemDTO orderItemDTO) {
        return new OrderItem(orderItemDTO.productId(), orderItemDTO.quantity(), orderItemDTO.unitPrice());
    }

    public OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
        return new OrderItemDTO(orderItem.getProductId(), orderItem.getQuantity(), orderItem.getUnitPrice());
    }
}
