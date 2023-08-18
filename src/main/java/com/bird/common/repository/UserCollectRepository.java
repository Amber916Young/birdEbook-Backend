package com.bird.common.repository;

import com.bird.app.dto.UserCollectDTO;
import com.bird.common.entity.UserCollect;
import com.bird.common.enums.CollectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: YY
 * @Date: 2023/8/11 17:20
 * @Version 1.0
 * <p>
 * <p>
 *
 */

@Repository
public interface UserCollectRepository extends JpaRepository<UserCollect, Long> {

    List<UserCollect>  findByUserIdAndCollectName(Long userId, String collectName);

    UserCollect findByUserIdAndId(Long userId, Long id);

    List<UserCollect> findByUserId(Long userId);
}
