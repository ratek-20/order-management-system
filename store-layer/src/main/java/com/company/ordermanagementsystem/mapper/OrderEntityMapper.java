package com.company.ordermanagementsystem.mapper;

import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityMapper {

    private final OrderItemEntityMapper orderItemEntityMapper;

    @Autowired
    public OrderEntityMapper(OrderItemEntityMapper orderItemEntityMapper) {
        this.orderItemEntityMapper = orderItemEntityMapper;
    }

    public OrderEntity mapToEntity(Order orderModel) {
        OrderEntity orderEntity = new OrderEntity(
                orderModel.getCustomerId(),
                orderModel.getStatus(),
                orderModel.getCreatedAt(),
                orderModel.getTotalAmount()
        );
        orderModel.getItems().forEach(
                orderItem -> orderEntity.addItem(
                        orderItemEntityMapper.mapToEntity(orderItem)
                )
        );
        return orderEntity;
    }

    public Order mapToModel(OrderEntity orderEntity) {
        Order orderModel = new Order(
                orderEntity.getCustomerId(),
                orderEntity.getStatus(),
                orderEntity.getCreatedAt(),
                orderEntity.getItems().stream()
                        .map(orderItemEntityMapper::mapToModel)
                        .toList(),
                orderEntity.getTotalAmount()
        );
        orderModel.setId(orderEntity.getId());
        return orderModel;

    }

}
