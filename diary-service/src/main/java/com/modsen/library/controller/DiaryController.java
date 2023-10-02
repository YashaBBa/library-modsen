package com.modsen.library.controller;


import com.modsen.library.dto.request.BookRequest;
import com.modsen.library.dto.response.BookResponse;
import com.modsen.library.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/diary")
public class DiaryController {
    private final DiaryService diaryService;


    @GetMapping(produces = {"application/json"}, value = "/available")
    public List<BookResponse> getAllAvailableBooks() {
        log.debug("Fetching all available books");
        List<BookResponse> bookList = diaryService.getAllAvailableBooks();
        return bookList;

    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public void updateBookRentStatus(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        log.debug("Update book with id: {}", bookRequest.getId());
        diaryService.setBookRentStatus(id, bookRequest);
    }

    @GetMapping(produces = {"application/json"})
    public List<BookResponse> getAllBooks() {
        log.debug("Fetching all books");
        List<BookResponse> bookResponses = diaryService.getAllBooks();
        return bookResponses;
    }
}
