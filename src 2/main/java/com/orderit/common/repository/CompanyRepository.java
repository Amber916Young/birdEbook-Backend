package com.orderit.common.repository;

import com.orderit.common.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyEmail(String companyEmail);
    boolean existsByCompanyEmail(String companyEmail);

    boolean existsByCompanyEmailAndCompanyName(String companyEmail, String companyName);

    boolean existsByCompanyName(String companyName);
}
