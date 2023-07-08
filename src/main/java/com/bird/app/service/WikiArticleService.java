package com.bird.app.service;

import com.bird.app.dto.WikiArticleDTO;
import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.exception.NotFoundRequestException;
import com.bird.common.entity.Book;
import com.bird.common.entity.WikiArticle;
import com.bird.common.repository.WikiArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


        return wikiArticleRepository.save(wikiArticle);
    }

    public WikiArticle getArticleById(Long id) {
        return wikiArticleRepository.findById(id).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Not_Found_Entity));
    }

    public WikiArticle updateArticleById(Long id, WikiArticleDTO wikiArticleDTO) {
        WikiArticle wikiArticle = new WikiArticle();
        return wikiArticleRepository.save(wikiArticle);
    }

    public void deleteArticleById(Long id) {
        wikiArticleRepository.deleteById(id);
    }

    public List<WikiArticle> getAllWikiArticleList() {
        return wikiArticleRepository.findAll();
    }
}
