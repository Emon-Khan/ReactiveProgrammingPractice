package com.example.shikbetumio.bookStore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.shikbetumio.bookStore.entity.Book;
import com.example.shikbetumio.bookStore.repository.BookRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class RepositoryTest {
	@Autowired
	private BookRepository bookRepository;

	@Test
	public void findMethodTest() {
//		Mono<Book> bookFound = bookRepository.findByName("Spring Boot to the rescue");
//		StepVerifier.create(bookFound).expectNextCount(4).verifyComplete();
//		
//		Flux<Book> bookFound = bookRepository.findByAuthor("Joey Tribiani");
//		StepVerifier.create(bookFound).expectNextCount(4).verifyComplete();

		bookRepository.findByNameAndAuthor("Spring Boot to the rescue", "Joey Tribiani").as(StepVerifier::create)
				.expectNextCount(1).verifyComplete();
	}

	@Test
	public void queryMethodTest() {

		bookRepository.getAllBooksByNameAndAuthor("Spring Boot to the rescue", "Joey Tribiani").as(StepVerifier::create)
				.expectNextCount(1).verifyComplete();
	}

	@Test
	public void searchTest() {

		bookRepository.searchBookByTitle("%Action%").as(StepVerifier::create).expectNextCount(1).verifyComplete();
	}

}
