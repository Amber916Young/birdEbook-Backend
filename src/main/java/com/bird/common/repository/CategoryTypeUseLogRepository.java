package com.bird.common.repository;

import com.bird.common.entity.CategoryUseLog;
import com.bird.common.enums.ArticleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName:CategoryTypeUseLogRepository
 * @Auther: yyj
 * @Description:
 * @Date: 17/07/2023 20:22
 * @Version: v1.0
 */
@Repository
public interface CategoryTypeUseLogRepository extends JpaRepository<CategoryUseLog,Long> {

    void deleteByArticleId(Long articleId);

    long countByCateId(Long categoryId);

    long countByCateIdAndArticleType(Long categoryId, ArticleType wiki);

    long countByArticleType(ArticleType wiki);
}
