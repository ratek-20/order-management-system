package com.company.ordermanagementsystem.kafka.in;

import com.company.ordermanagementsystem.domain.model.OrderStatus;
import com.company.ordermanagementsystem.port.in.OrderMessagingInPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderUpdateConsumerMock {

    private final OrderMessagingInPort orderMessagingInPort;

    @Autowired
    public OrderUpdateConsumerMock(OrderMessagingInPort orderMessagingInPort) {
        this.orderMessagingInPort = orderMessagingInPort;
    }

    public void consume(OrderUpdate orderUpdate) {
        orderMessagingInPort.process(orderUpdate.orderId(), OrderStatus.valueOf(orderUpdate.status()));
        // This method should be called when a message is received from the Kafka topic
    }

}
