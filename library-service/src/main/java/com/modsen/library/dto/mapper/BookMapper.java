package com.modsen.library.dto.mapper;

import com.modsen.library.domain.Book;
import com.modsen.library.dto.request.BookRequest;
import com.modsen.library.dto.response.BookResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookResponse toBookResponse(Book book);

    Book toBook(BookRequest bookRequest);

}
