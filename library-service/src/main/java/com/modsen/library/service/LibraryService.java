package com.modsen.library.service;

import com.modsen.library.dto.Diary;
import com.modsen.library.dto.request.BookRequest;
import com.modsen.library.dto.response.BookResponse;

import java.util.List;

public interface LibraryService {
    Diary getAllBooks();

    BookResponse getBookById(Long id);

    void createBook(BookRequest bookRequest);

    BookResponse getBookByISBN(String ISBN);

    void updateBook(Long id, BookRequest bookRequest);

    void deleteBook(Long id);

}
