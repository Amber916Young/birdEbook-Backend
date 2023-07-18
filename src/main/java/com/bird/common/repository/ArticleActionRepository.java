package com.bird.common.repository;

import com.bird.common.entity.ArticleAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleActionRepository extends JpaRepository<ArticleAction, Long> {
}
