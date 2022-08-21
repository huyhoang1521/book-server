package com.example.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import com.example.book.model.Book;
import com.example.book.repo.BookRepo;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class BookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

    @Bean
	CommandLineRunner run(BookRepo bookRepo) {
		return args -> {
			bookRepo.save(new Book(null, "The Great Gatsby", "F. Scott Fitzgerald", 6.99, "The Great Gatsby, F. Scott Fitzgerald’s third book, stands as the supreme achievement of his career.", "Historical Fiction", ""));
			bookRepo.save(new Book(null, "Hamlet", "William Shakespeare", 9.95, "Hamlet is Shakespeare's most popular, and most puzzling, play.", "Drama", ""));
			bookRepo.save(new Book(null, "Pride and Prejudice", "Jane Austen", 8.99, "Few have failed to be charmed by the witty and independent spirit of Elizabeth Bennet in Austen’s beloved classic Pride and Prejudice.", "Romance", ""));
			bookRepo.save(new Book(null, "To Kill a Mockingbird", "Harper Lee", 12.99, "The unforgettable novel of a childhood in a sleepy Southern town and the crisis of conscience that rocked it.", "Southern Gothic fiction", ""));
			bookRepo.save(new Book(null, "Don Quixote", "Miguel de Cervantes", 20.99, "Called the first modern novel, this marvelous book has stood the test of time to become irrevocably intertwined with the fabric of society.", "Satire", ""));
			bookRepo.save(new Book(null, "War and Peace", "Leo Tolstoy", 19.99, "A classic novel by Leo Tolstoy, which is regarded as a central work of world literature.", "Romance", ""));
			bookRepo.save(new Book(null, "Wuthering Heights", "Leo Tolstoy", 10.99, "Wuthering Heights tells the story of a romance between two youngsters: Catherine Earnshaw and an orphan boy, Heathcliff.", "Gothic Fiction", ""));
			bookRepo.save(new Book(null, "The Catcher in the Rye", "J. D. Salinger", 10.99, "The hero-narrator of The Catcher in the Rye is an ancient child of sixteen, a native New Yorker named Holden Caufield.", "Adult Fiction", ""));
			bookRepo.save(new Book(null, "Catch-22", "Joseph Heller", 16.99, "Joseph Heller's masterpiece about a bomber squadron in the Second World War's Italian theater features a gallery of magnificently strange characters seething with comic energy.", "Satire", ""));
			bookRepo.save(new Book(null, "Beloved", "Toni Morrison", 14.99, "Sethe, its protagonist, was born a slave and escaped to Ohio, but eighteen years later she is still not free. ", "Historic Fiction", ""));
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
