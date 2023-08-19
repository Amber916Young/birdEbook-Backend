package com.bird.app.service;

import com.bird.app.dto.UserCollectDTO;
import com.bird.app.mapper.UserCollectMapper;
import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.exception.NotFoundRequestException;
import com.bird.common.entity.Article;
import com.bird.common.entity.UserCollect;
import com.bird.common.enums.CollectType;
import com.bird.common.repository.ArticleRepository;
import com.bird.common.repository.UserCollectRepository;
import com.bird.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.*;

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
    public UserCollect createUserCollect(UserCollect userCollect) {
        Long userId = SecurityUtil.getCurrentUserId();
        String collectName = userCollect.getCollectName();
        Optional<UserCollect> userCollects =
                userCollectRepository.findByUserIdAndCollectName(userId, collectName);
        if (userCollects.isPresent()) {
            throw new NotFoundRequestException(ErrorReasonCode.Duplicated_Collect_Name);
        }
        userCollect.setUserId(userId);
        userCollect.setCollectName(collectName);
        userCollect.setCollectType(CollectType.PUBLIC);
        return userCollectRepository.save(userCollect);

    }


    //删
    public void deleteUserCollect(Long id) {
        Long userId = SecurityUtil.getCurrentUserId();
        UserCollect userCollect = userCollectRepository
                .findByUserIdAndId(id, userId).orElseThrow(() ->
                        new NotFoundRequestException(ErrorReasonCode.Not_Found_Entity));

        userCollectRepository.delete(userCollect);
    }


    //修改
    //修改收藏夹名称
    public void updateCollectName(UserCollect userCollect) {
        Long userId = SecurityUtil.getCurrentUserId();
        String collectName = userCollect.getCollectName();

        Optional<UserCollect> userCollects =
                userCollectRepository.findByUserIdAndCollectName(userId, collectName);
        if (userCollects.isPresent()) {
            throw new NotFoundRequestException(ErrorReasonCode.Duplicated_Collect_Name);
        }
        userCollect.setCollectName(collectName);
        userCollect.setCreateTime(userCollects.get().getCreateTime());
        userCollect.setModifyTime(ZonedDateTime.now());
        userCollectRepository.save(userCollect);

    }

    //修改收藏夹类型

    public void updateCollectType(UserCollect userCollect) {
        Long userId = SecurityUtil.getCurrentUserId();
        UserCollect preUserCollect= userCollectRepository
                .findByUserIdAndId(userCollect.getId(), userId).orElseThrow(() ->
                new NotFoundRequestException(ErrorReasonCode.Not_Found_Entity));

        userCollect.setCreateTime(preUserCollect.getCreateTime());
        userCollect.setModifyTime(ZonedDateTime.now());
        userCollectRepository.save(userCollect);
    }

    //查找
    //用户查询自己的收藏夹
    public List<UserCollect> getUserCollectsByUserId() {
        Long userId = SecurityUtil.getCurrentUserId();
        return userCollectRepository.findByUserId(userId);
    }

    //用户查询别人公开的收藏夹

    public List<UserCollect> getPublicUserCollectsByUserId(Long targetUserId) {
        return userCollectRepository.findByUserIdAndCollectType(targetUserId, CollectType.PUBLIC);
    }

}