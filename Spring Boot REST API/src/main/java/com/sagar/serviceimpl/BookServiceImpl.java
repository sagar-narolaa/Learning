package com.sagar.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sagar.cache.SimpleCache;
import com.sagar.dao.BookDao;
import com.sagar.entity.Book;
import com.sagar.service.BookService;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired 
    private SimpleCache<Integer,Book> cache;

    public BookServiceImpl() {
    }

    @Override
	public void saveMultiBooks(List<Book> books) {
    	books.forEach((book)-> cache.put(book.getId(), book));
    	bookDao.saveAll(books);	
	}

	public List<Book> getAllBooks() {
    	List<Book> books=cache.getAll();
    	return books.size()==0?bookDao.findAll():books;        
    }

    public void saveBook(Book book) {
    	
    	bookDao.save(book);
		Book boook=bookDao.findByBookName(book.getBookName()).get();
		cache.put(boook.getId(),boook);
    }
    

    public void updateBook(Book book) {
    	cache.put(book.getId(), book);
        bookDao.save(book);       
    }

    public boolean deleteBook(int id) {
    	cache.remove(id);
    	try {
    		bookDao.deleteById(id);
    		return true;
    	}catch (Exception e) {
			return false;
    	}
    }

	@Override
	public List<Book> booksWithPageAndSort(Integer pageIndex, Integer sizeOfPage, String sortingOrder,String[] sortBy) {
		Pageable booksWithSort = sortingOrder.toUpperCase().equals("ASC")
				? PageRequest.of(pageIndex - 1, sizeOfPage, Sort.by(sortBy).ascending())
				: PageRequest.of(pageIndex - 1, sizeOfPage, Sort.by(sortBy).descending());

		List<Book> books = bookDao.findAll(booksWithSort).getContent();
		return books;
	}

	@Override
	public List<Book> findByAnytg(String searchKeyword) {		
		return bookDao.findByAny(searchKeyword);
	}
}
