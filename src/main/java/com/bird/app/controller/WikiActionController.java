package com.bird.app.controller;

import com.bird.app.dto.WikiActionDTO;
import com.bird.app.service.WikiActionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName:WikiActionController
 * @Auther: yyj
 * @Description:
 * @Date: 06/07/2023 21:55
 * @Version: v1.0
 */
@RestController
@RequestMapping("/api/wikis/actions")
@RequiredArgsConstructor
@Slf4j
public class WikiActionController {

    private final WikiActionService wikiActionService;




    // Create
    @PostMapping
    public ResponseEntity<WikiActionDTO> createWikiAction(@RequestBody WikiActionDTO wikiActionDTO) {
        WikiActionDTO createdWikiAction = wikiActionService.createWikiAction(wikiActionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWikiAction);
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<WikiActionDTO> getWikiActionById(@PathVariable("id") Long id) {
        WikiActionDTO wikiActionDTO = wikiActionService.getWikiActionById(id);
        if (wikiActionDTO != null) {
            return ResponseEntity.ok(wikiActionDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<WikiActionDTO> updateWikiAction(@PathVariable("id") Long id,
                                                          @RequestBody WikiActionDTO wikiActionDTO) {
        WikiActionDTO updatedWikiAction = wikiActionService.updateWikiAction(id, wikiActionDTO);
        if (updatedWikiAction != null) {
            return ResponseEntity.ok(updatedWikiAction);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWikiAction(@PathVariable("id") Long id) {
        boolean deleted = wikiActionService.deleteWikiAction(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
