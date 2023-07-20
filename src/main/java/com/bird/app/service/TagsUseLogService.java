package com.bird.app.service;

import com.bird.common.entity.TagsUseLog;
import com.bird.common.enums.ArticleType;
import com.bird.common.repository.TagsUseLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author birdyyoung
 * @ClassName:TagsUseLogService
 * @Description:
 * @Date: 17/07/2023 16:46
 * @Version: v1.0
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TagsUseLogService {
    private final TagsUseLogRepository tagsUseLogRepository;


    public void createWikiTagsLog(Long articleId, String tags) {
        List<TagsUseLog> logs = new ArrayList<>();
        String[] tagsStr = tags.split(",");

        Arrays.stream(tagsStr).iterator().forEachRemaining(
                tagId -> {
                    TagsUseLog tagsUseLog = new TagsUseLog();
//                    tagsUseLog.setArticle(articleId);
                    tagsUseLog.setTagId(Long.valueOf(tagId));
                    logs.add(tagsUseLog);
                }
        );

        tagsUseLogRepository.saveAll(logs);


    }

    public void deleteAllTagsUseLogByArticleId(Long articleId) {
        List<TagsUseLog> logs = tagsUseLogRepository.findByArticleId(articleId);

        tagsUseLogRepository.deleteAllInBatch(logs);
    }
    private void deleteTagsUseLogByArticleId(Long articleId) {
        tagsUseLogRepository.deleteByArticleId(articleId);
    }
}
