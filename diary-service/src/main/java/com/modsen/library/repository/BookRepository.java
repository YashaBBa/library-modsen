package com.modsen.library.repository;

import com.modsen.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByBookWasTakenIsNullAndReturnTheBookDateIsNull();
}
