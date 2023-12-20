package com.company.ordermanagementsystem.kafka.in;

import java.util.UUID;

public record OrderUpdate(UUID orderId, String status) {
}
