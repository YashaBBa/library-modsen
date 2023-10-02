package com.modsen.library.dto.mapper.impl;

import com.modsen.library.domain.Book;
import com.modsen.library.dto.mapper.BookMapper;
import com.modsen.library.dto.request.BookRequest;
import com.modsen.library.dto.response.BookResponse;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements BookMapper {
    @Override
    public Book toBook(BookRequest diaryRequest) {
        return new Book().builder()
                .returnTheBookDate(diaryRequest.getReturnTheBookDate())
                .build();
    }

    @Override
    public BookResponse toBookResponse(Book diary) {
        return new BookResponse().builder()
                .id(diary.getId())
                .bookWasTaken(diary.getBookWasTaken())
                .returnTheBookDate(diary.getReturnTheBookDate())
                .build();
    }
}
