package com.example.book.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import javax.validation.Valid;

import com.example.book.model.Response;
import com.example.book.model.Book;
import com.example.book.service.implementation.BookServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookResource {
    private final BookServiceImpl bookService;

    @GetMapping("/list")
    public ResponseEntity<Response> getBooks() {
        return ResponseEntity.ok(
            Response.builder()
                .timestamp(now())
                .data(Map.of("books", bookService.list(30)))
                .message("Books retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveBook(@RequestBody @Valid Book book) {
        return ResponseEntity.ok(
            Response.builder()
                .timestamp(now())
                .data(Map.of("book", bookService.create(book)))
                .message("Book created")
                .status(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getBook(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
            Response.builder()
                .timestamp(now())
                .data(Map.of("book", bookService.get(id)))
                .message("Book retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteBook(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
            Response.builder()
                .timestamp(now())
                .data(Map.of("deleted", bookService.delete(id)))
                .message("Book deleted")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }

    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getBookImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/images/" + fileName ));
    }
}
