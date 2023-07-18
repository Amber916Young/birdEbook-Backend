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
import org.springframework.stereotype.Service;

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
    private final CategoryTypeUseLogService categoryTypeUseLogService;

    public Article createArticle(Article article) {
        //TODO Get from session
        article.setCreatedBy("testUser");
        Article newWiki =  articleRepository.save(article);

        Long articleId= newWiki.getId();

        tagsUseLogService.createWikiTagsLog(articleId, article.getTagIds());
        categoryTypeUseLogService.createWikiCategoryTypeUseLog(articleId, article.getCategoryId());
        articleActionService.createWikiActionByArticleId(articleId,OperationType.INSERT);

        return newWiki;
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
        categoryTypeUseLogService.deletecategoryTypeUseLogByArticleId(articleId);
        tagsUseLogService.createWikiTagsLog(articleId, article.getTagIds());
        categoryTypeUseLogService.createWikiCategoryTypeUseLog(articleId, article.getCategoryId());

        articleActionService.createWikiActionByArticleId(article.getId(),OperationType.UPDATE);

        return articleRepository.save(article);
    }

    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }

    public Page<Article> getAllWikiArticleList(int pageNumber,
                                               int pageSize,
                                               String queryStr) {
//        Sort sort = new Sort(Sort.Direction.ASC, "id");
        int pageNo = pageNumber == 0 ? 0: pageNumber-1;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return articleRepository.findAll(pageable);
    }
}
