package com.bird.app.service;

import com.bird.common.entity.Article;
import com.bird.common.entity.CategoryUseLog;
import com.bird.common.enums.ArticleType;
import com.bird.common.repository.CategoryTypeUseLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @ClassName:CategoryTypeUseLogService
 * @Auther: yyj
 * @Description:
 * @Date: 17/07/2023 20:17
 * @Version: v1.0
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryUseLogService {
    private final CategoryTypeUseLogRepository categoryTypeUseLogRepository;


    public void createWikiCategoryTypeUseLog(Article article) {
        CategoryUseLog categoryUseLog = new CategoryUseLog();
        categoryUseLog.setArticleType(ArticleType.WIKI);
        categoryUseLog.setArticle(article);
        categoryTypeUseLogRepository.save(categoryUseLog);
    }

    public void deleteCategoryTypeUseLogByArticleId(Long articleId) {
        categoryTypeUseLogRepository.deleteByArticleId(articleId);
    }
}
