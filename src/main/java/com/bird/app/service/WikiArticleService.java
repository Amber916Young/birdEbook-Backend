package com.bird.app.service;

import com.bird.app.dto.PageDTO;
import com.bird.app.dto.WikiArticleDTO;
import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.exception.NotFoundRequestException;
import com.bird.common.entity.WikiArticle;
import com.bird.common.repository.WikiArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public WikiArticle createArticle(WikiArticle wikiArticle) {

        //TODO aad action to DB
        wikiArticle.setUserId(-1L);
        return wikiArticleRepository.save(wikiArticle);
    }

    public WikiArticle getArticleById(Long id) {
        return wikiArticleRepository.findById(id).orElseThrow(() ->
                new NotFoundRequestException(ErrorReasonCode.Not_Found_Entity));
    }

    public WikiArticle updateArticleById( WikiArticle wikiArticle) {
        WikiArticle pre =  getArticleById(wikiArticle.getId());
        wikiArticle.setUserId(pre.getUserId());
        return wikiArticleRepository.save(wikiArticle);
    }

    public void deleteArticleById(Long id) {
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
