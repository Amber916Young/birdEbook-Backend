package com.bird.common.repository;

import com.bird.common.entity.ArticleAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikiActionRepository extends JpaRepository<ArticleAction, Long> {
}
