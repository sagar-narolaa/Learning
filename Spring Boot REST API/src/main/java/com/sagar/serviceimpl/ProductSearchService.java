package com.sagar.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagar.dao.BookDao;
import com.sagar.dao.BookDaoCriteria;
import com.sagar.entity.Book;
import com.sagar.model.ProductSearchRequest;
import com.sagar.model.ProductSearchResponse;
import com.sagar.model.ProductSearchResponseWrapper;

import antlr.CharQueue;

@Service
public class ProductSearchService {

	@Autowired
	BookDaoCriteria bookDao;
	@Autowired
	BookDao bookDAO;

	public ProductSearchResponseWrapper getResponseIfJSONBlank() {
		return new ProductSearchResponseWrapper(entitiesToModels(bookDAO.findAll()));
	}

	public ProductSearchResponseWrapper getResponse(ProductSearchRequest request) {
		List<Book> books = null;

		if (request.getBookSearch().equals("")) {
			books = bookDao.getBooksWithoutProps(request.getSortBy(), request.getSortOrder(),
					request.getRecordsPerPage(), request.getPageIndex());
			return new ProductSearchResponseWrapper(entitiesToModels(books));
		} else {
			books = bookDao.getBooksWithSearch(request.getSortBy(), request.getSortOrder(), request.getRecordsPerPage(),
					request.getBookSearch(), request.getUserId(), request.getPageIndex());
		}
		System.out.println("==============================" + books.size());
		return new ProductSearchResponseWrapper(entitiesToModels(books));

	}

	private void performValidations(ProductSearchRequest request) throws ValidationException {
		if (request == null) {
			throw new ValidationException("Hey..Fill the data");
		}
		if (request.getUserId() < 0) {
			throw new ValidationException("id cannot be less than 0");
		}
	}

	private List<ProductSearchResponse> entitiesToModels(List<Book> books) {
		System.out.println(books.size());
		return books.stream().map(w -> {
			ProductSearchResponse response = new ProductSearchResponse();
			response.setId(w.getId());
			response.setBookName(w.getBookName());
			response.setAuthor(w.getAuthor());
			response.setIsbn(w.getIsbn());
			if (w.getUser() == null) {
				response.setCatagoryName("Not Set");
			} else {
				response.setCatagoryName(w.getUser().getFname());
			}
			return response;
		}).collect(Collectors.toList());
	}

}
