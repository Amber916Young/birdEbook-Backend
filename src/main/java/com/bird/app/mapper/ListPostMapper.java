package com.bird.app.mapper;
import com.bird.app.dto.ArticleDTO;
import com.bird.app.dto.ListPostDTO;
import com.bird.common.entity.Article;
import com.bird.common.entity.ListPost;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface ListPostMapper {

    ListPostDTO toDTO(ListPost listPost);

    ListPost toEntity(ListPostDTO listPostDTO);

    List< ListPostDTO> toDTOList(List<ListPost> listPosts);
}
