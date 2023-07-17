package com.bird.app.service;

import com.bird.app.dto.WikiActionDTO;
import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.exception.NotFoundRequestException;
import com.bird.common.entity.WikiAction;
import com.bird.common.entity.WikiArticle;
import com.bird.common.enums.OperationType;
import com.bird.common.repository.WikiActionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author birdyyoung
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WikiActionService {

    @Autowired
    private WikiActionRepository wikiActionRepository;

    public WikiAction createWikiAction(WikiAction wikiAction) {
        return  wikiActionRepository.save(wikiAction);
    }
    public void createWikiActionByArticleId(Long articleId, String operation) {
        WikiAction wikiAction = new WikiAction();
        wikiAction.setWikiId(articleId);
        wikiAction.setUserId(-1L);
        wikiAction.setOperationType(operation);
        wikiAction.setUsername("testUser");
        wikiActionRepository.save(wikiAction);
    }

    public WikiAction getWikiActionById(Long id) {
        return wikiActionRepository.findById(id).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.WikiAction_Cannot_Found));
    }


    public void deleteWikiAction(Long id) {
        wikiActionRepository.findById(id).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.WikiAction_Already_Delete));
        wikiActionRepository.deleteById(id);
    }

    public Page<WikiAction> getAllWikiActionList(int pageNumber, int pageSize, String queryStr) {
        int pageNo = pageNumber == 0 ? 0: pageNumber-1;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return wikiActionRepository.findAll(pageable);
    }


}
