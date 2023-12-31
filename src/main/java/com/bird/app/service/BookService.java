package com.bird.app.service;

import com.bird.app.dto.BookDTO;
import com.bird.common.config.exception.ErrorReasonCode;
import com.bird.common.config.exception.NotFoundRequestException;
import com.bird.common.entity.Book;
import com.bird.common.repository.BookRepository;
import com.bird.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author birdyyoung
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public Book getBooksAndChapters() {
        Long userId = SecurityUtil.getCurrentUserId();
        return getBooksAndChapterByUserId(userId);
    }

    private Book getBooksAndChapterByUserId(Long userId) {
        return bookRepository.findByUserId(userId).orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Company_Not_Found));
    }

    public void deleteBookAndChapters(long id) {
        bookRepository.deleteById(id);
    }

    public Book createBook(Book book) {
        book.setUserId(SecurityUtil.getCurrentUserId());
        return bookRepository.save(book);
    }

    public Book updateBookProfile(BookDTO bookDTO) {
        Optional<Book> bookOptional = bookRepository.findById(bookDTO.getId());
        Book book = bookOptional.orElseThrow(() -> new NotFoundRequestException(ErrorReasonCode.Not_Found_Entity));
        book.setCategory(bookDTO.getCategory());
        book.setTags(bookDTO.getTags());
        book.setPublishTime(bookDTO.getPublishTime());
        return bookRepository.save(book);
    }
}
