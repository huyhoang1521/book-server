package com.example.book.service;

import org.springframework.data.domain.Page;

import com.example.book.model.Book;

public interface BookService {
    Book create(Book book);
    Page<Book> list(int page, int limit);
    Book get(long id);
    Book update(Book book);
    Boolean delete(Long id);
}
