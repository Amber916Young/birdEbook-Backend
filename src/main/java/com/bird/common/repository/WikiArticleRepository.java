package com.bird.common.repository;

import com.bird.common.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName:WikiArticleRepository
 * @Auther: yyj
 * @Description:
 * @Date: 01/07/2023 20:49
 * @Version: v1.0
 */
@Repository
public interface WikiArticleRepository extends JpaRepository<Article, Long> {
}
