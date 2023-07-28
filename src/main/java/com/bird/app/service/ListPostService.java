package com.bird.app.service;
import com.bird.app.dto.ListPostDTO;
import com.bird.app.mapper.ListPostMapper;
import com.bird.common.entity.ListPost;
import com.bird.common.repository.ListPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class ListPostService {

    private final ListPostRepository listPostRepository;
    private final ListPostMapper listPostMapper;


    public Page<ListPostDTO> getPageListPost(int page, int pageSize,Sort sort) {

        // 判断 page 是否合法（大于等于1）
        if (page < 1) {
            throw new IllegalArgumentException("页码必须大于等于1");
        }

        //默认一页显示10条
        pageSize = 10;

        // 默认按照日期排序

        sort = Sort.by(Sort.Direction.DESC, "createTime");

        //创建分页

        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);


        Page<ListPost> listPostPage = listPostRepository.findAll(pageable);

        return listPostPage.map(listPostMapper::toDTO);
    }

}