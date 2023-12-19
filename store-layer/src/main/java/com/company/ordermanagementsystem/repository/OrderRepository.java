package com.company.ordermanagementsystem.repository;

import com.company.ordermanagementsystem.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
}
