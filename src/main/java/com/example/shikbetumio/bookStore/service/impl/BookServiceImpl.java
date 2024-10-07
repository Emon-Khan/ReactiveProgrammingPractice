package com.example.shikbetumio.bookStore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shikbetumio.bookStore.entity.Book;
import com.example.shikbetumio.bookStore.repository.BookRepository;
import com.example.shikbetumio.bookStore.service.BookService;

import java.time.Duration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public Mono<Book> create(Book book) {
		Mono<Book> createdBook = bookRepository.save(book);
		return createdBook;
	}

	@Override
	public Flux<Book> getAll() {

		return bookRepository.findAll().delayElements(Duration.ofSeconds(2)).log().map(data -> {
			data.setName(data.getName().toUpperCase());
			return data;
		});
	}

	@Override
	public Mono<Book> get(int bookId) {
		Mono<Book> item = bookRepository.findById(bookId);
		return item;
	}

	@Override
	public Mono<Book> update(int bookId, Book book) {
		Mono<Book> oldBook = bookRepository.findById(bookId);
		return oldBook.flatMap(bookItem -> {
			bookItem.setDescription(book.getDescription());
			bookItem.setAuthor(book.getAuthor());
			bookItem.setName(book.getName());
			bookItem.setPublisher(book.getPublisher());
			return bookRepository.save(bookItem);
		});

	}

	@Override
	public Mono<Void> delete(int bookId) {
		return bookRepository.findById(bookId).flatMap(book -> bookRepository.delete(book));
	}

	@Override
	public Flux<Book> searchBooks(String title) {
		return bookRepository.searchBookByTitle("%" + title + "%");
	}

}
