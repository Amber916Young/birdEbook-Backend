package com.orderit.common.repository;

import com.orderit.app.dto.CompanyUserDTO;
import com.orderit.common.entity.CompanyUser;
import com.orderit.common.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CompanyUser, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdIsNot(String email, long id);

    Optional<CompanyUser> findByEmail(String email);

    Optional<CompanyUser> findByResetKey(String resetKey);

    List<CompanyUser> findCompanyUserByCompanyIdAndRole(Long companyId, Role role);
}
