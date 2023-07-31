package com.bird.common.repository;/*
 */

import com.bird.common.entity.ListPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;

@Repository
public interface ListPostRepository extends JpaRepository<ListPost, Long> {

    Page<ListPost> findAllOrderByCreateTimeDesc(Pageable pageable);
}
