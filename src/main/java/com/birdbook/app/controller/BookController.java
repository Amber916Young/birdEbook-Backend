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

//    @PutMapping(value = "/profile",produces = "application/json")
//    public ResponseEntity<BookDTO> updateCompanyProfile(@Valid @RequestBody CompanyProfileDTO companyProfileDTO) {
//        Book book = bookService.updateBookProfile(companyProfileDTO);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @PostMapping(value = "/users", produces = "application/json")
//    public ResponseEntity<CompanyUserDTO> addUserToCompany(@Valid @RequestBody CompanyUserDTO companyUserDTO) {
//        return new ResponseEntity<>(companyUserMapper
//                .toDTO(userService.createCompanyUser(
//                        companyUserMapper.toEntity(companyUserDTO))),
//                HttpStatus.OK);
//    }

}
