package com.example.book.service;

import java.util.Collection;

import com.example.book.model.Book;

public interface BookService {
    Book create(Book book);
    Collection<Book> list(int limit);
    Book get(long id);
    Book update(Book book);
    Boolean delete(Long id);
}
