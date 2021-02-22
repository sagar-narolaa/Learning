
package com.sagar.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sagar.model.Book;
import com.sagar.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;


	@GetMapping("/generateExcel")
	private String generateExcel(RedirectAttributes redirectAttributes)
			throws SQLException, IOException, ServletException {
		int excelGenerated = bookService.exportToExcel();
		redirectAttributes.addAttribute("excelGenerated", excelGenerated);
		return "redirect:/list/excelGenerate";
	}

	@RequestMapping("/list")
	private String listBook(Model model) throws SQLException, IOException, ServletException {
		List<Book> listBooks = bookService.getAll();
		model.addAttribute("listBooks", listBooks);
		model.addAttribute("excelGenerated", 0);
		return "book-list";
	}

	@RequestMapping("/list/excelGenerate")
	private String listBook(Model model, @RequestParam("excelGenerated") int excelStatus)
			throws SQLException, IOException, ServletException {
		List<Book> listBooks = bookService.getAll();
		model.addAttribute("listBooks", listBooks);
		int excel_status = excelStatus;
		model.addAttribute("excelGenerated", excel_status);
		return "book-list";
	}

	@GetMapping("/add")
	private String showNewForm() throws ServletException, IOException {
		return "book-form";
	}

	@GetMapping("/edit/{id}")
	private String showEditForm(@PathVariable("id") int id, Model model)
			throws SQLException, ServletException, IOException {
		System.out.println("edit called");
		Book existingBook = bookService.getById(id);
		model.addAttribute("book", existingBook);
		return "book-form";
	}

	@PostMapping("/add")
	private String insertBook(@RequestParam("name") String namee, @RequestParam("ISBN") String isbn,

			@RequestParam("Author") String author) throws SQLException, IOException {
		Book newBook = new Book(namee, isbn, author);
		bookService.save(newBook);
		return "redirect:/list";
	}
	
	@PostMapping("/edit/update")
	private String updateBook(@ModelAttribute Book book) throws SQLException, IOException {
		bookService.update(book);
		return "redirect:/list";
	}

	@GetMapping("/delete/{id}")
	private String deleteBook(@PathVariable("id") int id, Model model)
			throws SQLException, IOException, ServletException {
		bookService.delete(id);
		return "redirect:/list";
	}

}
