package com.bird.common.repository;

import com.bird.common.entity.TagsUseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName:TagsUseLogRepository
 * @Auther: yyj
 * @Description:
 * @Date: 17/07/2023 19:43
 * @Version: v1.0
 */
@Repository
public interface TagsUseLogRepository extends JpaRepository<TagsUseLog,Long> {

    void deleteByArticleId(Long articleId);

    List<TagsUseLog> findByArticleId(Long articleId);
}
