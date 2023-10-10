package com.modsen.library.controller;

import com.modsen.library.dto.request.BookRequest;
import com.modsen.library.dto.response.BookResponse;
import com.modsen.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;


    @GetMapping(produces = {"application/json"})
    public List<BookResponse> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @GetMapping(produces = {"application/json"}, value = "/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        log.debug("Fetching book by id : {}", id);
        return libraryService.getBookById(id);
    }

    @GetMapping(produces = {"application/json"}, value = "/isbn/{ISBN}")
    public BookResponse getBookByISBN(@PathVariable String ISBN) {
        log.debug("Fetching book by ISBN : {}", ISBN);
        return libraryService.getBookByISBN(ISBN);
    }

    @PostMapping
    public ResponseEntity<String> createBook(@Valid @RequestBody BookRequest bookRequest) {
        log.debug("Creating book from dto : {}", bookRequest);
        libraryService.createBook(bookRequest);

        return ResponseEntity.ok("New book was created");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        log.debug("Deleting book with id: {}", id);
        libraryService.deleteBook(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        log.debug("Updating book with id: {}\nwith params: {}", id, bookRequest);
        libraryService.updateBook(id, bookRequest);
    }


}
