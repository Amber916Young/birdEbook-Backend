package com.bird.app.service;

import com.bird.app.dto.web.DetailPageDTO;
import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.exception.NotFoundRequestException;
import com.bird.common.entity.Article;
import com.bird.common.entity.TagsUseLog;
import com.bird.common.repository.ArticleRepository;
import com.bird.common.utils.SecurityUtil;
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
        Long userId = -1L;
//        SecurityUtil.getCurrentUserId() SecurityUtil.getCurrentUserLogin()
        String username = "test";

        article.getTagsUseLogList().forEach(item -> setupTagsUseLogs(article, item));
        article.getCategoryUseLog().setArticle(article);
        article.setCreatedBy(username);
        article.setUserId(userId);
        return articleRepository.save(article);
    }



    public Article updateArticleById(Article article) {
        Long articleId= article.getId();
        Article articleInDB =getArticleById(articleId);

        tagsUseLogService.deleteTagsUseLogByArticleId(articleId);
        categoryUseLogService.deleteCategoryTypeUseLogByArticleId(articleId);


        article.setCreatedBy(articleInDB.getCreatedBy());
        article.setUserId(articleInDB.getUserId());
        article.setCreateTime(articleInDB.getCreateTime());
        Article updatedArticle = articleRepository.save(article);

//        updatedArticle.setTagsUseLogList(new HashSet<>(tagsUseLogService.findByArticleId(articleId)));
        return updatedArticle;
    }

    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow(() ->
                new NotFoundRequestException(ErrorReasonCode.Not_Found_Entity));
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
    public DetailPageDTO getArticleAndAllDetails(Long articleId) {

        return null;
    }


    private void setupTagsUseLogs(Article article, TagsUseLog item) {
        item.setArticle(article);
        item.setArticleType(article.getArticleType());
    }
}
