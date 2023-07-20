package com.bird.app.service;

import com.bird.common.entity.UserLike;
import com.bird.common.enums.ArticleType;
import com.bird.common.repository.UserLikeRepository;
import com.bird.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserLikeService {
    private final UserLikeRepository userLikeRepository;

    public void createOrCancelLikesWIKI(UserLike userLike) {
        UserLike like = findUserLikeById(userLike);
        if(like == null){
            Long userId =  SecurityUtil.getCurrentUserId();
            userLike.setUserId(userId);
            userLike.setArticleType(ArticleType.WIKI);
            userLikeRepository.save(userLike);
        }else {
            userLikeRepository.deleteById(like.getId());
        }
    }




    private  UserLike findUserLikeById(UserLike userLike){
        Optional<UserLike> optionalUserLike =  userLikeRepository.findById(userLike.getId());
        return optionalUserLike.orElse(null);
    }
}
