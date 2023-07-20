package com.bird.app.mapper;

import com.bird.app.dto.LikeDTO;
import com.bird.common.entity.UserLike;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @ClassName:UserLikeMapper
 * @Auther: yyj
 * @Description:
 * @Date: 20/07/2023 21:13
 * @Version: v1.0
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserLikeMapper {

    LikeDTO toDTO(UserLike userLike);

    UserLike toEntity(LikeDTO likeDTO);

    List<LikeDTO> toDTOList(List<UserLike> userLikes);

}
