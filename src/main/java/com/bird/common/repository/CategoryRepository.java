package com.bird.common.repository;

import com.bird.common.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName:CategoryTypeRepository
 * @Auther: yyj
 * @Description:
 * @Date: 12/07/2023 22:42
 * @Version: v1.0
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
