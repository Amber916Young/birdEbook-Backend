package com.bird.app.service;

import com.bird.app.dto.WikiActionDTO;
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
    public WikiActionDTO createWikiAction(WikiActionDTO wikiActionDTO) {


        WikiActionDTO createdWikiAction = wikiActionRepository.save(wikiActionDTO);
        return createdWikiAction;
    }

    // Read
    public WikiActionDTO getWikiActionById(Long id) {
        Optional<WikiActionDTO> optionalWikiAction = wikiActionRepository.findById(id);
        return optionalWikiAction.orElse(null);
    }

    // Update
    public WikiActionDTO updateWikiAction(Long id, WikiActionDTO wikiActionDTO) {
        // Check if the WikiAction with the given id exists
        Optional<WikiActionDTO> optionalExistingWikiAction = wikiActionRepository.findById(id);
        if (optionalExistingWikiAction.isPresent()) {
            // Perform any necessary validations or business logic
            // ...

            wikiActionDTO.setId(id);
            WikiActionDTO updatedWikiAction = wikiActionRepository.save(wikiActionDTO);
            return updatedWikiAction;
        } else {
            return null;
        }
    }

    // Delete
    public boolean deleteWikiAction(Long id) {
        // Check if the WikiAction with the given id exists
        Optional<WikiActionDTO> optionalExistingWikiAction = wikiActionRepository.findById(id);
        if (optionalExistingWikiAction.isPresent()) {
            wikiActionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
