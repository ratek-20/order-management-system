package com.company.ordermanagementsystem.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {

    private UUID id;
    private UUID customerId;
    private OrderStatus status;
    private LocalDateTime orderDateTime;
    private List<OrderItem> items;
    private BigDecimal totalAmount;

    public Order(UUID customerId, OrderStatus status, LocalDateTime orderDateTime, List<OrderItem> items, BigDecimal totalAmount) {
        this.customerId = customerId;
        this.status = status;
        this.orderDateTime = orderDateTime;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id)
                && Objects.equals(customerId, order.customerId)
                && status == order.status
                && Objects.equals(orderDateTime, order.orderDateTime)
                && Objects.equals(items, order.items)
                && Objects.equals(totalAmount, order.totalAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, status, orderDateTime, items, totalAmount);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", status=" + status +
                ", orderDateTime=" + orderDateTime +
                ", items=" + items +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
