package com.company.ordermanagementsystem.mapper;

import com.company.ordermanagementsystem.domain.model.OrderItem;
import com.company.ordermanagementsystem.entity.OrderItemEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderItemEntityMapper {

    public OrderItem mapToModel(OrderItemEntity orderItemEntity) {
        return new OrderItem(orderItemEntity.getId(),
                orderItemEntity.getQuantity(),
                orderItemEntity.getUnitPrice());
    }

    public OrderItemEntity mapToEntity(OrderItem orderItem) {
        return new OrderItemEntity(orderItem.getProductId(),
                orderItem.getQuantity(),
                orderItem.getUnitPrice());
    }

}
