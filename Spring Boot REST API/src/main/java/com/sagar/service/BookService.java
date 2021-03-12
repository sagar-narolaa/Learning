package com.sagar.service;

import java.util.List;

import com.sagar.entity.Book;
import com.sagar.entity.User;

public interface BookService {
    List<Book> getAllBooks();

    void saveBook(Book user);

    void updateBook(Book user);

    boolean deleteBook(int id);

	List<Book> booksWithPageAndSort(Integer pageIndex, Integer sizeOfPage, String sortingOrder, String[] strings);

	List<Book> findByAnytg(String searchKeyword);

	void saveMultiBooks(List<Book> books);
}
