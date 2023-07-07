package com.orderit.common.repository;

import com.orderit.common.entity.FavouriteSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouriteSupplierRepository extends JpaRepository<FavouriteSupplier, Long> {
    boolean existsByCompanyIdAndSupplierId(Long companyId, Long supplierId);

    Optional<FavouriteSupplier> getFavouriteSupplierForCurrentCompanyByCompanyIdAndSupplierId(Long companyId, Long supplierId);

    List<FavouriteSupplier> getFavouriteSuppliersByCompanyId(Long companyId);
}
