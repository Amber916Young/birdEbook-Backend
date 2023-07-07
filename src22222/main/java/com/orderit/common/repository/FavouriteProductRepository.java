package com.orderit.common.repository;

import com.orderit.common.entity.FavouriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouriteProductRepository extends JpaRepository<FavouriteProduct, Long> {
    boolean existsByCompanyIdAndSupplierIdAndSupplierProductId(Long companyId, Long supplierId, Long supplierProductId);

    Optional<FavouriteProduct> getFavouriteProductBySupplierIdAndSupplierProductIdAndCompanyId(Long supplierId, Long supplierProductId, Long companyId);

    List<FavouriteProduct> getFavouriteProductBySupplierIdAndCompanyId(Long supplierId, Long companyId);
}
