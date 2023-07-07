package com.orderit.common.repository;

import com.orderit.common.entity.SupplierOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SupplierOrderItemRepository extends JpaRepository<SupplierOrderItem, Long> {
    Set<SupplierOrderItem> getOrderItemsBySupplierOrderId(Long id);
}
