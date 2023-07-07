package com.bird.app.service;

import com.bird.app.dto.WikiActionDTO;
import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.exception.NotFoundRequestException;
import com.bird.common.entity.WikiAction;
import com.bird.common.repository.WikiActionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Create
    public WikiAction createWikiAction(WikiActionDTO wikiActionDTO) {


        WikiActionDTO createdWikiAction = wikiActionRepository.save(wikiActionDTO);
        return createdWikiAction;
    }

    // Read
    public WikiActionDTO getWikiActionById(Long id) {
        Optional<WikiActionDTO> optionalWikiAction = wikiActionRepository.findById(id);
        return optionalWikiAction.orElse(null);
    }


    public void deleteWikiAction(Long id) {
        wikiActionRepository.findById(id).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.WikiAction_Already_Delete));
        wikiActionRepository.deleteById(id);
    }
}
