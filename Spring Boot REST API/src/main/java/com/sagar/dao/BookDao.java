package com.sagar.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sagar.entity.Book;

public interface BookDao extends JpaRepository<Book, Integer> {

	@Query("FROM Book b WHERE b.author=?1 or b.bookName=?1 or b.isbn=?1")
	List<Book> findByAny(String any);
	
	Optional<Book> findByBookName(String bookName);

}
