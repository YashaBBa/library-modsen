package com.modsen.library.service.impl;

import com.modsen.library.domain.Book;
import com.modsen.library.dto.mapper.BookMapper;
import com.modsen.library.dto.request.BookRequest;
import com.modsen.library.dto.response.BookResponse;
import com.modsen.library.repository.BookRepository;
import com.modsen.library.service.LibraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final KafkaSenderImpl kafkaSender;
    @Value("${spring.kafka.topic}")
    private String topic;

    @Override
    public List<BookResponse> getAllBooks() {
        log.debug("Fetching all books");
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream()
                .map(book -> bookMapper.toBookResponse(book))
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse getBookById(Long id) {
        log.debug("Fetching book by id: {}", id);
        Book book = bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return bookMapper.toBookResponse(book);
    }

    @Override
    public void createBook(BookRequest bookRequest) {
        log.debug("Creating new book from dto: {}", bookRequest);
        Book book = bookMapper.toBook(bookRequest);
        bookRepository.save(book);

        kafkaSender.sendMessage(book.getId(), topic);


    }

    @Override
    public BookResponse getBookByISBN(String ISBN) {
        log.debug("Fetching book by ISBN: {}", ISBN);
        Book book = bookRepository.findByISBN(ISBN).orElseThrow(EntityNotFoundException::new);
        return bookMapper.toBookResponse(book);
    }

    @Override
    public void updateBook(Long id, BookRequest bookRequest) {
        log.debug("Updating book with id: {}\nwith params: {}", id, bookRequest);
        bookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Book book = bookMapper.toBook(bookRequest);
        book.setId(id);
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        log.debug("Deleting user with id: {}", id);
        bookRepository.deleteById(id);
    }


}
