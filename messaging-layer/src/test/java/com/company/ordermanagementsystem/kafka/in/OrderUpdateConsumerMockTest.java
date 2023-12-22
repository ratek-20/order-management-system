package com.company.ordermanagementsystem.kafka.in;

import com.company.ordermanagementsystem.domain.model.OrderStatus;
import com.company.ordermanagementsystem.kafka.in.objectmother.OrderUpdateObjectMother;
import com.company.ordermanagementsystem.port.in.OrderMessagingInPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

class OrderUpdateConsumerMockTest {

    @InjectMocks
    private OrderUpdateConsumerMock orderUpdateConsumerMock;

    @Mock
    private OrderMessagingInPort orderMessagingInPortMock;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void itShouldConsumeTheOrderUpdateEvent() {
        OrderUpdate orderUpdate = OrderUpdateObjectMother.aRandomOrderUpdate();
        orderUpdateConsumerMock.consume(orderUpdate);
        verify(orderMessagingInPortMock).process(orderUpdate.orderId(), OrderStatus.valueOf(orderUpdate.status()));
    }
}