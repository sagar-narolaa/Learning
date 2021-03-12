package com.sagar.controller;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sagar.entity.Book;
import com.sagar.model.ProductSearchRequest;
import com.sagar.service.BookService;
import com.sagar.service.ExcelService;

@RestController
@RequestMapping({"/book"})

public class BooksController {
    @Autowired
    private BookService bookService;
    @Autowired
    private ExcelService excelService;

    public BooksController() {
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
        
	/*
	 * @PostMapping("_list") public List<Book> getAllBooks(@RequestBody
	 * ProductSearchRequest request) {
	 * 
	 * if (sortingOrder == null) { return getAllBooks(); } return
	 * bookService.booksWithPageAndSort(pageIndex,sizeOfPage, sortingOrder, new
	 * String[] { sortBy }); }
	 */
    
    @GetMapping({ "/list" })
	public List<Book> getAllBooks1(@RequestParam(required = false) Integer pageIndex,@RequestParam(required = false) Integer sizeOfPage,@RequestParam(required = false) String sortingOrder, @RequestParam(required = false) String sortBy) {
		if (sortingOrder == null) {
			return getAllBooks();
		}
		return bookService.booksWithPageAndSort(pageIndex,sizeOfPage, sortingOrder, new String[] { sortBy });
	}

    @PostMapping({"/save"})
    public List<Book> saveBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return getAllBooks();
    }
    
    @PostMapping({"/saveMulti"})
    public List<Book> saveMultipleBooks(@RequestBody List<Book> books) {    	
        bookService.saveMultiBooks(books);
        return getAllBooks();
    }
    
    
    @PutMapping({"/update"})
    public List<Book> update(@RequestBody Book book) {
        bookService.updateBook(book);
        return getAllBooks();
    }

    @DeleteMapping({"/delete/{id}"})
    public boolean delete(@PathVariable("id") int id) {
        return bookService.deleteBook(id);
        
    }

    @GetMapping({"/excel"})
    public boolean generateExcel() throws FileNotFoundException, SQLException {
        return excelService.generateExcel();
    }
    
    @GetMapping({ "/find/{searchKeyword}" })
	public List<Book> findByAny(@PathVariable String searchKeyword) {
		return bookService.findByAnytg(searchKeyword);
	}
}