package com.bird.common.repository;

import com.bird.common.entity.Article;
import com.bird.common.enums.ArticleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName:WikiArticleRepository
 * @Auther: yyj
 * @Description:
 * @Date: 01/07/2023 20:49
 * @Version: v1.0
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findAll(Specification<Article> keywordSpec, Pageable pageable);


    @Query(value = "SELECT * FROM Article  WHERE id IN (SELECT cul.article_Id FROM category_use_log  cul WHERE cul.cate_id = :categoryId)", nativeQuery = true)
    Page<Article> findAllByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);


    long countByArticleType(ArticleType wiki);

    List<Article> findAll(Long articleId);
}