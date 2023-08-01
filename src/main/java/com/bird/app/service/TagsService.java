package com.bird.app.service;

import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.exception.NotFoundRequestException;
import com.bird.common.entity.Tags;
import com.bird.common.repository.TagsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @ClassName:TagsService
 * @Auther: yyj
 * @Description:
 * @Date: 11/07/2023 22:32
 * @Version: v1.0
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TagsService {
    @Autowired
    private TagsRepository tagsRepository;

    private final TagsUseLogService tagsUseLogService;


    public Page<Tags> getAllTagsList(int pageNumber, int pageSize, String queryStr) {
        int pageNo = pageNumber == 0 ? 0: pageNumber-1;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return tagsRepository.findAll(pageable);
    }

    public Tags createTags(Tags tags) {
        return  tagsRepository.save(tags);
    }

    public Tags getTagsById(Long id) {
        return tagsRepository.findById(id).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Tags_Cannot_Found));
    }


    /**
     * TODO
     * @param id
     */
    public void deleteTags(Long id) {
        tagsRepository.deleteById(id);
    }

    public Tags updateTags(Tags tags) {
        Tags updateTag = getTagsById(tags.getId());
        updateTag.setIcon(tags.getIcon());
        updateTag.setName(tags.getName());
        return tagsRepository.save(updateTag);
    }

    public List<Tags> getAllTagsListWithoutPage() {
        return tagsRepository.findAll();
    }
}
