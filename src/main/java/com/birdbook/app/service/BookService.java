package com.birdbook.app.service;

import com.birdbook.common.config.exception.ErrorReasonCode;
import com.birdbook.common.config.exception.NotFoundRequestException;
import com.birdbook.common.entity.Book;
import com.birdbook.common.repository.BookRepository;
import com.birdbook.common.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
