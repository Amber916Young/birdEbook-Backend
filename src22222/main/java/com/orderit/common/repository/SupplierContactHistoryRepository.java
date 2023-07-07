package com.orderit.common.repository;

import com.orderit.common.entity.SupplierContactHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierContactHistoryRepository extends JpaRepository<SupplierContactHistory, Long> {
}
