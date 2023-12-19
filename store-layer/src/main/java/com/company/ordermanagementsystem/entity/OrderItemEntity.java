package com.company.ordermanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderItemEntity {

    @Id
    private UUID id;

    @Column(name = "quantity", nullable = false, columnDefinition = "INTEGER")
    private int quantity;

    @Column(name = "unit_price", nullable = false, columnDefinition = "NUMERIC(10,2)")
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, referencedColumnName = "id", columnDefinition = "UUID")
    private OrderEntity orderEntity;

    public OrderItemEntity(UUID id, int quantity, BigDecimal unitPrice) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

}
