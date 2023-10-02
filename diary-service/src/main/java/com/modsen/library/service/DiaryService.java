package com.modsen.library.service;


import com.modsen.library.dto.request.BookRequest;
import com.modsen.library.dto.response.BookResponse;

import java.util.List;

public interface DiaryService {
    void addBookInDiary(Long id);

    void setBookRentStatus(Long id, BookRequest bookRequest);

    List<BookResponse> getAllAvailableBooks();

    List<BookResponse> getAllBooks();
}
