package com.birdbook.app.controller;

import com.birdbook.app.dto.BookDTO;
import com.birdbook.app.mapper.BookMapper;
import com.birdbook.app.service.BookService;
import com.birdbook.common.entity.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
@Slf4j
public class BookController {
    private final BookMapper bookMapper;
    private final BookService bookService;


    @GetMapping(produces = "application/json")
    public ResponseEntity<BookDTO> getBooksAndChapters() {
        Book book  = bookService.getBooksAndChapters();
        return new ResponseEntity<>(bookMapper.toDTO(book), HttpStatus.OK);
    }

    @DeleteMapping(produces = "application/json")
    public ResponseEntity<BookDTO> deleteBookAndChapters(@Valid @PathVariable("id") long id) {
        bookService.deleteBookAndChapters(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping(produces = "application/json")
    public ResponseEntity<BookDTO> updateCompanyProfile(@Valid @RequestBody BookDTO bookDTO) {
        Book book = bookService.updateBookProfile(bookDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping( produces = "application/json")
    public ResponseEntity<?> addUserToCompany(@Valid @RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookMapper
                .toDTO(bookService.createBook(
                        bookMapper.toEntity(bookDTO))),
                HttpStatus.OK);
    }

}
