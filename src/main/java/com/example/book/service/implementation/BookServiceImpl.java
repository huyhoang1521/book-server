package com.example.book.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.example.book.model.Book;
import com.example.book.repo.BookRepo;
import com.example.book.service.BookService;

import java.util.List;
import java.util.Random;

import static java.lang.Boolean.TRUE;
import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j

public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;
    EntityManager entityManager;

    @Override
    public Book create(Book book) {
        log.info("Saving new book: {}", book.getTitle());
        book.setImageUrl(setBookImageUrl());
        return bookRepo.save(book);
    }

    public Page<Book> list(int page, int limit) {
        log.info("Fetching all books");
        //return bookRepo.findAll(of(page, limit));
        return bookRepo.findAll(of(page, limit, Sort.by(Sort.Direction.DESC, "id")));
    }

    public List<Book> list2(int page, int limit) {
        return bookRepo.findByAuthor("F. Scott Fitzgerald");
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
