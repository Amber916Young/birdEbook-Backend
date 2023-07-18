package com.bird.app.service;

import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.exception.NotFoundRequestException;
import com.bird.common.entity.ArticleAction;
import com.bird.common.enums.OperationType;
import com.bird.common.repository.ArticleActionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ArticleActionService {

    @Autowired
    private ArticleActionRepository articleActionRepository;

    public ArticleAction createWikiAction(ArticleAction articleAction) {
        return  articleActionRepository.save(articleAction);
    }
    public void createWikiActionByArticleId(Long articleId, OperationType operation) {
        ArticleAction articleAction = new ArticleAction();
        articleAction.setArticleId(articleId);
        articleAction.setUserId(-1L);
        articleAction.setOperationType(operation);
        articleAction.setCreatedBy("testUser");
        articleActionRepository.save(articleAction);
    }

    public ArticleAction getWikiActionById(Long id) {
        return articleActionRepository.findById(id).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.WikiAction_Cannot_Found));
    }


    public void deleteWikiAction(Long id) {
        articleActionRepository.findById(id).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.WikiAction_Already_Delete));
        articleActionRepository.deleteById(id);
    }

    public Page<ArticleAction> getAllWikiActionList(int pageNumber, int pageSize, String queryStr) {
        int pageNo = pageNumber == 0 ? 0: pageNumber-1;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return articleActionRepository.findAll(pageable);
    }


}
