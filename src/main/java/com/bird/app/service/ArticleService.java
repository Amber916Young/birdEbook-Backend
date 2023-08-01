package com.bird.app.service;

import com.bird.app.dto.DetailArticleDTO;
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
import com.bird.common.utils.page.PageResult;
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
import java.util.stream.Collectors;

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


    public PageResult getPageListPost(int pageNumber,
                                      int pageSize,Long categoryId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        pageNumber = pageNumber <= 0 ? 0 : pageNumber - 1;

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);


        Page<Article> articlePage ;
        Category category = new Category();
        if(categoryId == 0){
            category.setId(0L);
            category.setName("全部");
            articlePage = articleRepository.findAll(pageable);
        }else {
            category = categoryService.getCategoryTypeById(categoryId);
            sort = Sort.by(Sort.Direction.DESC, "create_time");
            pageable = PageRequest.of(pageNumber, pageSize, sort);
            articlePage = articleRepository.findAllByCategoryId(categoryId,pageable);
        }


        long totalsize = categoryUseLogService.findCountCategoryUseLogByCategoryId(categoryId);

        Map<Long, Tags> tagsMap = new HashMap<>();
        List<Article> articles = articlePage.getContent();

        List<HomeArticlesDTO> articlesWithTags = articles.stream()
                .map(article -> {
                    List<Tags> tagsList = article.getTagsUseLogList().stream()
                            .map(tagsUseLog -> {
                                Long tagId = tagsUseLog.getTagId();
                                return tagsMap.computeIfAbsent(tagId, tagsService::getTagsById);
                            })
                            .collect(Collectors.toList());

                    HomeArticlesDTO homeArticlesDTO = new HomeArticlesDTO();
                    homeArticlesDTO.setTagsList(tagsMapper.toDTOList(tagsList));
                    homeArticlesDTO.setArticle(articleMapper.toWebDTO(article));
                    return homeArticlesDTO;
                })
                .collect(Collectors.toList());

        HomeListArticlesDTO listArticles = new HomeListArticlesDTO();
        listArticles.setArticles(articlesWithTags);
        listArticles.setCategory(categoryMapper.toDTO(category));

        PageResult pageResult = new PageResult();
        pageResult.setPageSize(pageSize);
        pageResult.setPageNum(pageNumber);
        pageResult.setContent(listArticles);
        pageResult.setTotalPages(totalsize);

        return pageResult;
    }
}
