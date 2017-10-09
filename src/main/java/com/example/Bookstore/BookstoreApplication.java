package com.example.Bookstore;

import javafx.application.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner books(BookRepository repository, UserRepository urepository) {
		return (args) -> {
			repository.save(new Book("Mein kampf", "Adolf Hitler", 1925, 4545, 70));
			repository.save(new Book("The Art of the Deal", "Donald J. Trump", 1987, 1919, 500));

			User user1 = new User("user",
					"$2a$04$XKOacv.fDAbYIAMQpJqrC.TLkkccfM0E5XlDL.f728nsaYOJC3Ih6", "USER");
			User user2 = new User("admin",
					"$2a$04$5kVFc/24QnAn659O3KesPORGE.4umazfqnS0rUzESBK6rUJSC.Yyi", "ADMIN");
			
			
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}