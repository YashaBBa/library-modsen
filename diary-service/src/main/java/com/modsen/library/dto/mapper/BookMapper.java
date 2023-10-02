package com.modsen.library.dto.mapper;

import com.modsen.library.domain.Book;
import com.modsen.library.dto.request.BookRequest;
import com.modsen.library.dto.response.BookResponse;


public interface BookMapper {
    Book toBook(BookRequest diaryRequest);

    BookResponse toBookResponse(Book diary);
}
