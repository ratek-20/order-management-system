package com.company.ordermanagementsystem.exception;

import java.util.UUID;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(UUID id) {
        super("Order with id " + id + " not found");
    }
}

