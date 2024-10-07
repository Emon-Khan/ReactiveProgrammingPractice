package com.example.shikbetumio.bookStore.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.shikbetumio.bookStore.entity.Book;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {
	
}
