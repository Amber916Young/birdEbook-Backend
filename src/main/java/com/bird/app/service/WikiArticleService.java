package com.bird.app.service;

import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.exception.NotFoundRequestException;
import com.bird.common.entity.WikiArticle;
import com.bird.common.enums.OperationType;
import com.bird.common.repository.WikiArticleRepository;
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
public class WikiArticleService {

    private final WikiArticleRepository wikiArticleRepository;
    private final WikiActionService wikiActionService;
    private final TagsUseLogService tagsUseLogService;
    private final CategoryTypeUseLogService categoryTypeUseLogService;

    public WikiArticle createArticle(WikiArticle wikiArticle) {
        //TODO Get from session
        wikiArticle.setCreatedBy("testUser");
        WikiArticle newWiki =  wikiArticleRepository.save(wikiArticle);

        Long articleId= newWiki.getId();

        tagsUseLogService.createWikiTagsLog(articleId,wikiArticle.getTags());
        categoryTypeUseLogService.createWikiCategoryTypeUseLog(articleId,wikiArticle.getCategoryType());
        wikiActionService.createWikiActionByArticleId(articleId,OperationType.INSERT.name());

        return newWiki;
    }

    public WikiArticle getArticleById(Long id) {
        return wikiArticleRepository.findById(id).orElseThrow(() ->
                new NotFoundRequestException(ErrorReasonCode.Not_Found_Entity));
    }

    public WikiArticle updateArticleById( WikiArticle wikiArticle) {
        Long articleId= wikiArticle.getId();

        WikiArticle pre =  getArticleById(articleId);
        wikiArticle.setCreatedBy(pre.getCreatedBy());
        wikiArticle.setCreateTime(pre.getCreateTime());

        tagsUseLogService.deleteTagsUseLogByArticleId(articleId);
        categoryTypeUseLogService.deletecategoryTypeUseLogByArticleId(articleId);
        tagsUseLogService.createWikiTagsLog(articleId,wikiArticle.getTags());
        categoryTypeUseLogService.createWikiCategoryTypeUseLog(articleId,wikiArticle.getCategoryType());

        wikiActionService.createWikiActionByArticleId(wikiArticle.getId(),OperationType.UPDATE.name());

        return wikiArticleRepository.save(wikiArticle);
    }

    public void deleteArticleById(Long id) {
        wikiActionService.createWikiActionByArticleId(id,OperationType.DELETE.name());
        tagsUseLogService.deleteTagsUseLogByArticleId(id);
        categoryTypeUseLogService.deletecategoryTypeUseLogByArticleId(id);
        wikiArticleRepository.deleteById(id);
    }

    public Page<WikiArticle> getAllWikiArticleList(int pageNumber,
                                                   int pageSize,
                                                   String queryStr) {
//        Sort sort = new Sort(Sort.Direction.ASC, "id");
        int pageNo = pageNumber == 0 ? 0: pageNumber-1;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return wikiArticleRepository.findAll(pageable);
    }
}
