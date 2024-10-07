package com.example.shikbetumio.bookStore.service;

import com.example.shikbetumio.bookStore.entity.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {

	public Mono<Book> create(Book book);

	public Flux<Book> getAll();

	public Mono<Book> get(int bookId);

	public Mono<Book> update(int bookId, Book book);

	public Mono<Void> delete(int bookId);

	Flux<Book> searchBooks(String title);
}
