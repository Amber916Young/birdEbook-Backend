package com.bird.common.repository;

import com.bird.common.entity.WikiAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikiActionRepository extends JpaRepository<WikiAction, Long> {
}
