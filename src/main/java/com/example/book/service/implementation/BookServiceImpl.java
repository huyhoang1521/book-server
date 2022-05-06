package com.example.book.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;

import com.example.book.model.Book;
import com.example.book.repo.BookRepo;
import com.example.book.service.BookService;

import java.util.Collection;
import java.util.Random;

import static java.lang.Boolean.TRUE;
import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;

    @Override
    public Book create(Book book) {
        log.info("Saving new book: {}", book.getTitle());
        book.setImageUrl(setBookImageUrl());
        return bookRepo.save(book);
    }

    @Override
    public Collection<Book> list(int limit) {
        log.info("Fetching all books");
        return bookRepo.findAll(of(0, limit)).toList();
    }

    @Override
    public Book get(long id) {
        log.info("Fetching book by id: {}", id);
        return bookRepo.findById(id).get();
    }

    @Override
    public Book update(Book book) {
        log.info("Updating book: {}", book.getTitle());
        return bookRepo.save(book);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting book by ID: {}", id);
        bookRepo.deleteById(id);
        return TRUE;
    }

    private String setBookImageUrl() {
        String[] imageNames = { "book1.png", "book2.png", "book3.png", "book4.png" };
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/book/image/" + imageNames[new Random().nextInt(4)]).toUriString();
    }
}
