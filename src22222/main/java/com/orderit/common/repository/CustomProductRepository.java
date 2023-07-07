package com.orderit.common.repository;

import com.orderit.common.entity.CustomProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomProductRepository  extends JpaRepository<CustomProduct, Long> {
    List<CustomProduct> getCustomProductBySupplierIdAndCompanyId(Long supplierId, Long companyId);
}
