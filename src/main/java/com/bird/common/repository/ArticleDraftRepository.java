package com.bird.common.repository;

import com.bird.common.entity.ArticleDraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName:ArticleDraftRepository
 * @Auther: yyj
 * @Description:
 * @Date: 23/07/2023 21:25
 * @Version: v1.0
 */
@Repository
public interface ArticleDraftRepository extends JpaRepository<ArticleDraft, Long> {
    void findByArticleId(Long articleId);
    boolean isFindByArticleId(Long articleId);
}
