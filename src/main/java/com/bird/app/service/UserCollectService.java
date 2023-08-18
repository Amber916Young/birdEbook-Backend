package com.bird.app.service;

import com.bird.app.dto.UserCollectDTO;
import com.bird.app.mapper.TagsMapper;
import com.bird.app.mapper.UserCollectMapper;
import com.bird.common.config.exception.ConflictRequestException;
import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.exception.NotFoundRequestException;
import com.bird.common.entity.Article;
import com.bird.common.entity.UserCollect;
import com.bird.common.enums.CollectType;
import com.bird.common.repository.ArticleRepository;
import com.bird.common.repository.UserCollectRepository;
import com.bird.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: YY
 * @Date: 2023/8/11 17:44
 * @Version 1.0
 * <p>
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserCollectService {

    private final UserCollectRepository userCollectRepository;

    private final ArticleRepository articleRepository;

    private final UserCollectMapper userCollectMapper;


    //增加
    public UserCollect createUserCollect(Long userId, String collectName, Long collectId, Long articleId) {

        List<UserCollect> userCollects =
                userCollectRepository.findByUserIdAndCollectName(userId, collectName);

        for (UserCollect userCollect : userCollects) {
            if (!userCollect.getId().equals(collectId)) {
                throw new NotFoundRequestException(ErrorReasonCode.Duplicated_Collect_Name);
            }
        }

        Optional<Article> article = articleRepository.findById(articleId);
        if (!article.isPresent()) {
            throw new NotFoundRequestException(ErrorReasonCode.Not_Found_Entity);
        }

        UserCollect userCollect = new UserCollect();
        userCollect.setUserId(userId);
        userCollect.setArticleId(articleId);
        userCollect.setCollectName(collectName);
        userCollect.setCollectType(CollectType.PUBLIC);
        userCollect.setCreateTime(ZonedDateTime.now());
        return userCollectRepository.save(userCollect);

    }


    //删
    public void deleteUserCollect(Long id, Long userId) {

        UserCollect userCollect = userCollectRepository.findByUserIdAndId(id, userId);
        if (userCollect == null) {
            throw new NotFoundRequestException(ErrorReasonCode.Not_Found_Entity);
        }

        userCollectRepository.delete(userCollect);
    }


    //修改
    //修改收藏夹名称
    public void updateCollectName(Long userId, String collectName, Long collectId) {

        List<UserCollect> userCollects =
                userCollectRepository.findByUserIdAndCollectName(userId, collectName);

        for (UserCollect userCollect : userCollects) {
            if (!userCollect.getId().equals(collectId)) {
                throw new NotFoundRequestException(ErrorReasonCode.Duplicated_Collect_Name);
            }

            userCollect.setCollectName(collectName);

            userCollectRepository.save(userCollect);
        }

    }

    //修改收藏夹类型

    public void updateCollectType(Long userId, Long Id, CollectType collectType) {
        UserCollect userCollect = userCollectRepository.findByUserIdAndId(userId, Id);
        if (userCollect == null) {
            throw new NotFoundRequestException(ErrorReasonCode.Not_Found_Entity);
        }
        userCollect.setCollectType(collectType);
        userCollectRepository.save(userCollect);
    }

    //查找
    //用户查询自己的收藏夹
    public List<UserCollectDTO> getUserCollectsByUserId(Long userId, CollectType collectType) {

        List<UserCollect> userCollects = userCollectRepository.findByUserId(userId);
        List<UserCollectDTO> allCollects = new ArrayList<>();

        for (UserCollect collect : userCollects) {
            if (collect.getCollectType() == collectType) {
                allCollects.add(userCollectMapper.toDTO(collect));
            }
        }

        return allCollects;
    }

    //用户查询别人公开的收藏夹

    public List<UserCollectDTO> getPublicUserCollectsByUserId(Long userId, Long targetUserId) {

        List<UserCollect> userCollects = userCollectRepository.findByUserId(targetUserId);

        List<UserCollectDTO> publicCollects = new ArrayList<>();

        for (UserCollect collect : userCollects) {
            if (collect.getCollectType() == CollectType.PUBLIC) {
                publicCollects.add(userCollectMapper.toDTO(collect));
            }
        }

        return publicCollects;
    }

}