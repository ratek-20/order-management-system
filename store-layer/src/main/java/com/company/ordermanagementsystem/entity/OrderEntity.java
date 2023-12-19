package com.company.ordermanagementsystem.entity;

import com.company.ordermanagementsystem.domain.model.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "`order`")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "customer_id", nullable = false, columnDefinition = "UUID")
    private UUID customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('PENDING', 'CONFIRMED', 'SHIPPED', 'DELIVERED', 'CANCELLED')")
    private OrderStatus status;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "total_amount", nullable = false, columnDefinition = "NUMERIC(10,2)")
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items = new ArrayList<>();

    public OrderEntity(UUID customerId, OrderStatus status, LocalDateTime createdAt, BigDecimal totalAmount) {
        this.customerId = customerId;
        this.status = status;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
    }

    public void addItem(OrderItemEntity item) {
        items.add(item);
        item.setOrderEntity(this);
    }

    public void removeItem(OrderItemEntity item) {
        items.remove(item);
        item.setOrderEntity(null);
    }

}
