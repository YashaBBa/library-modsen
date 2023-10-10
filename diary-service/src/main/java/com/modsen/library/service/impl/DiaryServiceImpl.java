package com.modsen.library.service.impl;

import com.modsen.library.domain.Book;
import com.modsen.library.dto.mapper.BookMapper;
import com.modsen.library.dto.request.BookRequest;
import com.modsen.library.dto.response.BookResponse;
import com.modsen.library.repository.BookRepository;
import com.modsen.library.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiaryServiceImpl implements DiaryService {
    private final BookMapper bookMapper;
    private final BookRepository bookRepository;



    @Override
    public void addBookInDiary(Long id) {
        log.debug("Creating book with id : {}", id);
        Book book=new Book();
        book.setId(id);
        bookRepository.save(book);
    }

    @Override
    public void setBookRentStatus(Long id, BookRequest bookRequest) {
        log.debug("Setting book with id = {} a retail time", bookRequest.getId());

        bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Book book = bookMapper.toBook(bookRequest);
        book.setId(id);
        book.setBookWasTaken(LocalDateTime.now());
        bookRepository.save(book);
    }


    @Override
    public List<BookResponse> getAllAvailableBooks() {
        log.debug("Fetching all books that are available");
        List<Book> listOfBooks = bookRepository.findAllByBookWasTakenIsNullAndReturnTheBookDateIsNull();
        return listOfBooks.stream()
                .map(book -> bookMapper.toBookResponse(book))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookResponse> getAllBooks() {
        log.debug("Fetching all books that are available");
        List<Book> listOfBooks = bookRepository.findAll();
        return listOfBooks.stream()
                .map(book -> bookMapper.toBookResponse(book))
                .collect(Collectors.toList());

    }
}
