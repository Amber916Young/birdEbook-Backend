package com.bird.app.mapper;

import com.bird.app.dto.BookDTO;
import com.bird.common.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface BookMapper {
    BookDTO toDTO(Book book);

    Book toEntity(BookDTO bookDTO);

    List<BookDTO> toDTOList(List<Book> books);
}
