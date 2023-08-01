package com.bird.app.service;

import com.bird.app.dto.DetailArticleDTO;
import com.bird.app.dto.ListPostDTO;
import com.bird.app.dto.PageDTO;
import com.bird.app.dto.web.HomeArticlesDTO;
import com.bird.app.dto.web.HomeListArticlesDTO;
import com.bird.app.mapper.ArticleMapper;
import com.bird.app.mapper.CategoryMapper;
import com.bird.app.mapper.TagsMapper;
import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.exception.NotFoundRequestException;
import com.bird.common.entity.*;
import com.bird.common.enums.ArticleType;
import com.bird.common.repository.ArticleDraftRepository;
import com.bird.common.repository.ArticleRepository;
import com.bird.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * @author birdyyoung
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleDraftRepository articleDraftRepository;
    private final ArticleActionService articleActionService;
    private final TagsUseLogService tagsUseLogService;
    private final TagsService tagsService;
    private final CategoryService categoryService;
    private final CategoryUseLogService categoryUseLogService;
    private final ArticleMapper articleMapper;
    private final CategoryMapper categoryMapper;
    private final TagsMapper tagsMapper;

    public Article createArticle(Article article) {
        Long userId = SecurityUtil.getCurrentUserId();
        String username = SecurityUtil.getCurrentUserLogin();
        article.getTagsUseLogList().forEach(tagsUseLog -> {
            tagsUseLog.setArticle(article);
            tagsUseLog.setArticleType(ArticleType.WIKI);
        });
        article.getCategoryUseLog().setArticleType(ArticleType.WIKI);
        article.getCategoryUseLog().setArticle(article);
        article.setCreatedBy(username);
        article.setUserId(userId);
        return articleRepository.save(article);
//        List<TagsUseLog> tagsUseLogList = new ArrayList<>();
//        List<Tags> tagsList = new ArrayList<>();
//        DetailArticleDTO articleDTO = new DetailArticleDTO();
//        articleDTO.setArticle(articleMapper.toDTO(newArticle));
//        articleDTO.setTagsList(tagsMapper.toDTOList(tagsList));
//        articleDTO.setCategory(categoryMapper.toDTO(categoryService.getCategoryTypeById(cateId)));


    }


    public void updateArticleById(Article article) {
        Long articleId = article.getId();
        /**
         * Delete
         * **/
        tagsUseLogService.deleteAllTagsUseLogByArticleId(articleId);
        categoryUseLogService.deleteCategoryTypeUseLogByArticleId(articleId);

        Article articleInDB = getArticleById(articleId);

        article.getTagsUseLogList().forEach(tagsUseLog -> {
            tagsUseLog.setArticle(articleInDB);
            tagsUseLog.setArticleType(ArticleType.WIKI);
        });
        article.getCategoryUseLog().setArticleType(ArticleType.WIKI);
        article.getCategoryUseLog().setArticle(articleInDB);
        article.setCreatedBy(articleInDB.getCreatedBy());
        article.setUserId(articleInDB.getUserId());
        article.setCreateTime(articleInDB.getCreateTime());
        article.setModifyTime(ZonedDateTime.now());

        articleRepository.save(article);

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
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");

        int pageNo = pageNumber == 0 ? 0 : pageNumber - 1;
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        if (queryStr != null && !queryStr.isEmpty()) {
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


    // draft
    public void singleUpdateArticle(ArticleDraft articleDraft) {
        Long userId = SecurityUtil.getCurrentUserId();
        String username = SecurityUtil.getCurrentUserLogin();
        articleDraft.setCreatedBy(username);
        articleDraft.setUserId(userId);
        articleDraftRepository.save(articleDraft);
    }


    public void deleteArticleDraftById(Long id) {
        articleDraftRepository.deleteById(id);
    }

    public List<DetailArticleDTO> getArticleByPageDTO(PageDTO pageDTO) {
        return null;
    }


    public DetailArticleDTO getArticleAndAllDetails(Long articleId) {

        return null;
    }

    private void createAndSaveArticle(DetailArticleDTO detailArticleDTO, Article article, List<TagsUseLog> tagsUseLogList,
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


    public Page<HomeListArticlesDTO> getPageListPost(int pageNumber,
                                                     int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        pageNumber = pageNumber <= 0 ? 0 : pageNumber - 1;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Article> listPostPage = articleRepository.findAll(pageable);
        Map<Long, Tags> tagsMap = new HashMap<>();
        Map<Long, Category> categoryMap = new HashMap<>();

        return listPostPage.map(article -> {
            HomeListArticlesDTO dto = new HomeListArticlesDTO();
            HomeArticlesDTO homeArticlesDTO = new HomeArticlesDTO();
            Long cateId = article.getCategoryUseLog().getId();
            Category category = new Category();
            if (categoryMap.containsKey(cateId)) {
                category = categoryMap.get(cateId);
            } else {
                category = categoryService.getCategoryTypeById(cateId);
            }

            List<Tags> tagsList = new ArrayList<>();
            article.getTagsUseLogList().forEach(tagsUseLog -> {
                Long tagId = tagsUseLog.getTagId();
                Tags tags = new Tags();
                if (tagsMap.containsKey(tagId)) {
                    tags = tagsMap.get(tagId);
                } else {
                    tags = tagsService.getTagsById(tagId);
                }
                tagsList.add(tags);
            });

            homeArticlesDTO.setArticle(articleMapper.toWebDTO(article));
            homeArticlesDTO.setTagsList(tagsMapper.toDTOList(tagsList));
            dto.setArticles(Collections.singletonList(homeArticlesDTO));
            dto.setCategory(categoryMapper.toDTO(category));
            return dto;
        });
    }
}
