package com.orderit.common.repository;

import com.orderit.common.entity.ServicePartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicePartnerRepository extends JpaRepository<ServicePartner, Long> {

}
