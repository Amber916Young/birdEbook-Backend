package com.bird.app.service;

import com.bird.app.dto.web.DetailPageDTO;
import com.bird.common.entity.Article;
import com.bird.common.repository.ArticleRepository;
import com.bird.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WebArticleService {
    private final ArticleRepository articleRepository;
    private final TagsUseLogService tagsUseLogService;
    private final CategoryUseLogService categoryUseLogService;

    /***
     * TODO
     *  store categoryId and tagIds to specific logDB,
     *  because need to record the usage number of each category and tags
     *  popular tags / category
     * */

    public Article createArticle(Article article) {
        Long userId = SecurityUtil.getCurrentUserId();
        String username = SecurityUtil.getCurrentUserName();
        article.setCreatedBy(username);
        article.setUserId(userId);

        Article newArticle =  articleRepository.save(article);
        Long articleId= newArticle.getId();

        tagsUseLogService.createWikiTagsLog(articleId, article.getTagIds());
        categoryUseLogService.createWikiCategoryTypeUseLog(articleId, article.getCategoryId());

        return newArticle;
    }

    //TODO
    public DetailPageDTO getArticleAndAllDetails(Long articleId) {

        return null;
    }
}
