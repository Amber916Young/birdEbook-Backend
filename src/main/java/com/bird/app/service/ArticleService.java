package com.bird.app.service;

import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.exception.NotFoundRequestException;
import com.bird.common.entity.Article;
import com.bird.common.enums.OperationType;
import com.bird.common.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

/**
 * @author birdyyoung
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleActionService articleActionService;
    private final TagsUseLogService tagsUseLogService;
    private final CategoryUseLogService categoryUseLogService;

    public Article createArticle(Article article) {
        //TODO Get from session
        article.setCreatedBy("testUser");
        Article newArticle =  articleRepository.save(article);

        Long articleId= newArticle.getId();

        tagsUseLogService.createWikiTagsLog(articleId, article.getTagIds());
        categoryUseLogService.createWikiCategoryTypeUseLog(articleId, article.getCategoryId());
        articleActionService.createWikiActionByArticleId(articleId,OperationType.INSERT);

        return newArticle;
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow(() ->
                new NotFoundRequestException(ErrorReasonCode.Not_Found_Entity));
    }

    public Article updateArticleById(Article article) {
        Long articleId= article.getId();

        Article pre =  getArticleById(articleId);
        article.setCreatedBy(pre.getCreatedBy());
        article.setCreateTime(pre.getCreateTime());

        tagsUseLogService.deleteTagsUseLogByArticleId(articleId);
        categoryUseLogService.deletecategoryTypeUseLogByArticleId(articleId);
        tagsUseLogService.createWikiTagsLog(articleId, article.getTagIds());
        categoryUseLogService.createWikiCategoryTypeUseLog(articleId, article.getCategoryId());

        articleActionService.createWikiActionByArticleId(article.getId(),OperationType.UPDATE);

        return articleRepository.save(article);
    }

    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }

    public Page<Article> getAllArticlesList(int pageNumber,
                                               int pageSize,
                                               String queryStr) {
//        Sort = new Sort(Sort.Direction.ASC, "id");
        int pageNo = pageNumber == 0 ? 0: pageNumber-1;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        if (queryStr != null && !queryStr.isEmpty()) {
            // Create a specification for the keyword query
            Specification<Article> keywordSpec = (root, query, criteriaBuilder) -> {
                Predicate predicate = criteriaBuilder.disjunction(); // Using 'or' operator

                predicate = criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + queryStr.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + queryStr.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("articleType")), "%" + queryStr.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("status")), "%" + queryStr.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("createdBy")), "%" + queryStr.toLowerCase() + "%")
                );

                return predicate;
            };
            // Apply the keyword specification to the repository query
            return articleRepository.findAll(keywordSpec, pageable);
        } else {
            return articleRepository.findAll(pageable);
        }
    }
}
