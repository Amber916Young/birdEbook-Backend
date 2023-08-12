package com.bird.common.repository.admin;

import com.bird.common.entity.AdminMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AdminMenuRepository extends JpaRepository<AdminMenu, Long> {
}