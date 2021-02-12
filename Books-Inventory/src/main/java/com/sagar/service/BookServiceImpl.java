package com.sagar.service;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sagar.dao.BookDao;
import com.sagar.model.Book;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	private static final Logger LOG = Logger.getLogger(BookServiceImpl.class);

	@Autowired
	@Qualifier("booksJPA")
	private BookDao bookDao;
	@Autowired
	@Qualifier("ExcelJPA")
	private ExcelService excelService;

	@Override
	public List<Book> getAll() {
		//return new ArrayList<>();
		return bookDao.findAll();
	}

	@Override
	public void save(Book book) {
		bookDao.save(book);
	}

	@Override
	public void update(Book book) {
		bookDao.update(book);
	}

	@Override
	public void delete(int id) {
		bookDao.delete(id);
	}

	@Override
	public Book getById(int id) {
		Book book = bookDao.findById(id);
		LOG.info("Found book" + book.getName() + " by id = " + book.getId());
		return book;
	}

	/*
	 * @Override public Book getByISBN(int isbn) { return bookDao.findByISBN(isbn);
	 * }
	 */

	@Override
	public int exportToExcel() throws FileNotFoundException, SQLException {		
		return excelService.generateExcel();
	}
}
