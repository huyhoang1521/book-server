package com.example.book.repo;
import com.example.book.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);
}
