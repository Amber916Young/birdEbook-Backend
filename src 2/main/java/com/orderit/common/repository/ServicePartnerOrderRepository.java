package com.orderit.common.repository;

import com.orderit.common.entity.ServicePartnerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicePartnerOrderRepository extends JpaRepository<ServicePartnerOrder, Long> {
}
