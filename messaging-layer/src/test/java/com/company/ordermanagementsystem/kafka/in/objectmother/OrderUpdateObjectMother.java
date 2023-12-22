package com.company.ordermanagementsystem.kafka.in.objectmother;

import com.company.ordermanagementsystem.kafka.in.OrderUpdate;

import java.util.UUID;

public class OrderUpdateObjectMother {

    public static OrderUpdate aRandomOrderUpdate() {
        return new OrderUpdate(UUID.randomUUID(), "CONFIRMED");
    }
}
