package com.company.ordermanagementsystem.controller.objectmother;

import com.company.ordermanagementsystem.dto.CreateOrderRequest;

import java.util.List;
import java.util.UUID;

public class CreateOrderRequestObjectMother {

    public static CreateOrderRequest aRandomCreateOrderRequest() {
        return new CreateOrderRequest(
                UUID.randomUUID(),
                List.of()
        );
    }
}
