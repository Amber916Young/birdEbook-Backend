package com.bird.app.service;

import com.bird.app.dto.DetailArticleDTO;
import com.bird.app.mapper.ArticleMapper;
import com.bird.app.mapper.CategoryMapper;
import com.bird.app.mapper.TagsMapper;
import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.exception.NotFoundRequestException;
import com.bird.common.entity.Article;
import com.bird.common.entity.CategoryUseLog;
import com.bird.common.entity.Tags;
import com.bird.common.entity.TagsUseLog;
import com.bird.common.repository.ArticleRepository;
import com.bird.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author birdyyoung
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleActionService articleActionService;
    private final TagsUseLogService tagsUseLogService;
    private final TagsService tagsService;
    private final CategoryService categoryService;
    private final CategoryUseLogService categoryUseLogService;
    private final ArticleMapper articleMapper;
    private final CategoryMapper categoryMapper;
    private final TagsMapper tagsMapper;

    public void createArticle(DetailArticleDTO detailArticleDTO) {
        Long userId = SecurityUtil.getCurrentUserId();
        String username =  SecurityUtil.getCurrentUserLogin();
        Article article = articleMapper.toEntity(detailArticleDTO.getArticle());
        List<TagsUseLog> tagsUseLogList = new ArrayList<>();
        List<Tags> tagsList = new ArrayList<>();
        CreateAndSaveArticle(detailArticleDTO,article,tagsUseLogList,tagsList);

        Long cateId = detailArticleDTO.getCategory().getId();

        article.getCategoryUseLog().setArticle(article);
        article.setCreatedBy(username);
        article.setUserId(userId);
        Article newArticle = articleRepository.save(article);

//        DetailArticleDTO articleDTO = new DetailArticleDTO();
//        articleDTO.setArticle(articleMapper.toDTO(newArticle));
//        articleDTO.setTagsList(tagsMapper.toDTOList(tagsList));
//        articleDTO.setCategory(categoryMapper.toDTO(categoryService.getCategoryTypeById(cateId)));


    }


    public DetailArticleDTO updateArticleById(DetailArticleDTO detailArticleDTO) {
        Article article = articleMapper.toEntity(detailArticleDTO.getArticle());
        Long articleId = article.getId();
        /**
         * Delete
         * **/
        tagsUseLogService.deleteAllTagsUseLogByArticleId(articleId);
        categoryUseLogService.deleteCategoryTypeUseLogByArticleId(articleId);

        Article articleInDB = getArticleById(articleId);

        List<TagsUseLog> tagsUseLogList = new ArrayList<>();
        List<Tags> tagsList = new ArrayList<>();
        CreateAndSaveArticle(detailArticleDTO,article,tagsUseLogList,tagsList);


        Long cateId = detailArticleDTO.getCategory().getId();

        article.getCategoryUseLog().setArticle(articleInDB);
        article.setCreatedBy(articleInDB.getCreatedBy());
        article.setUserId(articleInDB.getUserId());
        article.setCreateTime(articleInDB.getCreateTime());
        article.setModifyTime(ZonedDateTime.now());

        Article updateArticle = articleRepository.save(article);

        DetailArticleDTO articleDTO = new DetailArticleDTO();
        articleDTO.setArticle(articleMapper.toDTO(updateArticle));
        articleDTO.setTagsList(tagsMapper.toDTOList(tagsList));
        articleDTO.setCategory(categoryMapper.toDTO(categoryService.getCategoryTypeById(cateId)));


        return articleDTO;
    }

    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow(() ->
                new NotFoundRequestException(ErrorReasonCode.Not_Found_Entity));
    }

    public Page<Article> getAllArticlesList(int pageNumber,
                                            int pageSize,
                                            String queryStr) {
//        Sort = new Sort(Sort.Direction.ASC, "id");
        int pageNo = pageNumber == 0 ? 0 : pageNumber - 1;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        if (queryStr != null && !queryStr.isEmpty()) {
            // Create a specification for the keyword query
            Specification<Article> keywordSpec = (root, query, criteriaBuilder) -> {
                Predicate predicate = criteriaBuilder.disjunction(); // Using 'or' operator

                predicate = criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + queryStr.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + queryStr.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("articleType")), "%" + queryStr.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("status")), "%" + queryStr.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("createdBy")), "%" + queryStr.toLowerCase() + "%")
                );

                return predicate;
            };
            // Apply the keyword specification to the repository query
            return articleRepository.findAll(keywordSpec, pageable);
        } else {
            return articleRepository.findAll(pageable);
        }
    }

    public DetailArticleDTO getArticleAndAllDetails(Long articleId) {

        return null;
    }

    private void CreateAndSaveArticle(DetailArticleDTO detailArticleDTO, Article article, List<TagsUseLog> tagsUseLogList,
                                      List<Tags> tagsList) {
        /**
         * Tags
         * **/
        detailArticleDTO.getTagsList().forEach(tagsDTO -> {
            Long tagId = tagsDTO.getId();
            TagsUseLog tagsUseLog = new TagsUseLog();
            tagsUseLog.setArticle(article);
            tagsUseLog.setTagId(tagId);
            tagsUseLogList.add(tagsUseLog);
            Tags tags = tagsService.getTagsById(tagId);
            tagsList.add(tags);
        });
        article.setTagsUseLogList(tagsUseLogList);


        /**
         * Category
         * **/
        Long cateId = detailArticleDTO.getCategory().getId();
        CategoryUseLog categoryUseLog = new CategoryUseLog();
        categoryUseLog.setArticle(article);
        categoryUseLog.setCateId(cateId);
        article.setCategoryUseLog(categoryUseLog);
    }


}
