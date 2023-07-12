package com.bird.common.repository;

import com.bird.common.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName:TagsRepository
 * @Auther: yyj
 * @Description:
 * @Date: 12/07/2023 20:57
 * @Version: v1.0
 */
@Repository
public interface TagsRepository extends JpaRepository<Tags,Long> {


}
