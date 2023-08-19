package com.bird.app.mapper;

import com.bird.app.dto.TagsDTO;
import com.bird.app.dto.UserCollectDTO;
import com.bird.common.entity.Tags;
import com.bird.common.entity.UserCollect;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author: YY
 * @Date: 2023/8/11 17:35
 * @Version 1.0
 */
@Mapper
public interface UserCollectMapper {

    UserCollectDTO toDTO(UserCollect userCollect);

    UserCollect toEntity(UserCollectDTO userCollectDTO);


}
