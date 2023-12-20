package com.company.ordermanagementsystem.domain.service;

import com.company.ordermanagementsystem.domain.exception.OrderNotFoundException;
import com.company.ordermanagementsystem.domain.model.Order;
import com.company.ordermanagementsystem.domain.model.OrderStatus;
import com.company.ordermanagementsystem.port.in.OrderMessagingInPort;
import com.company.ordermanagementsystem.port.out.OrderApiOutPort;
import com.company.ordermanagementsystem.port.out.OrderDaoOutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderMessagingProcessor implements OrderMessagingInPort {

    private final OrderDaoOutPort orderDaoOutPort;
    private final OrderApiOutPort orderApiOutPort;

    @Autowired
    public OrderMessagingProcessor(OrderDaoOutPort orderDaoOutPort, OrderApiOutPort orderApiOutPort) {
        this.orderDaoOutPort = orderDaoOutPort;
        this.orderApiOutPort = orderApiOutPort;
    }

    @Override
    public void process(UUID orderId, OrderStatus status) {
        Order order = orderDaoOutPort.getOrderById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));

        order.setStatus(status);
        orderDaoOutPort.updateOrder(order);

        if (status == OrderStatus.CONFIRMED) {
            order.getItems().forEach(item -> orderApiOutPort.updateInventory(item.getProductId(), item.getQuantity()));
        }

    }
}
