package com.sagar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.sagar.entity.Book;

@Repository
public class BookDaoCriteria {

	@Autowired
	EntityManager em;

	public List<Book> getBooksWithoutProps(String sortBy, String sortOrder, int pageSize, int pageIndex) {

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Book> cq = cb.createQuery(Book.class);

		Root<Book> book = cq.from(Book.class);

		cq.select(book);

		if (sortOrder.toLowerCase().equals("asc")) {
			cq.orderBy(cb.asc(book.get(sortBy)));
		} else {
			cq.orderBy(cb.desc(book.get(sortBy)));
		}

		TypedQuery<Book> query = em.createQuery(cq);

		return query.setFirstResult(pageSize * (pageIndex - 1)).setMaxResults(pageSize).getResultList();

	}

	public List<Book> getBooksWithSearch(String sortBy, String sortOrder, int pageSize, String any, int userId,
			int pageIndex) {

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Book> cq = cb.createQuery(Book.class);

		Root<Book> book = cq.from(Book.class);

		Path<Integer> usr_Id = book.join("user").get("id");

		ArrayList<Predicate> conditions = new ArrayList<>();
		conditions.add(cb.like(book.get("bookName"), "%" + any + "%"));
		conditions.add(cb.like(book.get("isbn"), "%" + any + "%"));
		conditions.add(cb.like(book.get("author"), "%" + any + "%"));

		Predicate findByUserId = cb.equal(usr_Id, userId);

		cq.select(book);

		if (sortOrder.toLowerCase().equals("asc")) {
			cq.where(cb.or(conditions.toArray(new Predicate[conditions.size()])), cb.and(findByUserId))
					.orderBy(cb.asc(book.get(sortBy)));
		} else {
			cq.where(cb.or(conditions.toArray(new Predicate[conditions.size()])), cb.and(findByUserId))
					.orderBy(cb.desc(book.get(sortBy)));
		}

		TypedQuery<Book> query = em.createQuery(cq);

		return query.setFirstResult(pageSize * (pageIndex - 1)).setMaxResults(pageSize).getResultList();

		// return query.getResultList();
	}

	/*
	 * public List<Book> getBooks(String sortBy, String sortOrder, int pageSize,
	 * String any, int userId, int pageIndex) {
	 * 
	 * CriteriaBuilder cb = em.getCriteriaBuilder(); CriteriaQuery<Book> cq =
	 * cb.createQuery(Book.class); Root<Book> book = cq.from(Book.class);
	 * 
	 * Path<Integer> usr_Id = book.join("user").get("id");
	 * 
	 * ArrayList<Predicate> conditions = new ArrayList<>();
	 * conditions.add(cb.like(book.get("bookName"), "%" + any + "%"));
	 * conditions.add(cb.like(book.get("isbn"), "%" + any + "%"));
	 * conditions.add(cb.like(book.get("author"), "%" + any + "%"));
	 * 
	 * Predicate findByUserId = cb.equal(usr_Id, userId);
	 * 
	 * cq.select(book);
	 * 
	 * if (sortOrder.toLowerCase().equals("asc")) {
	 * cq.where(cb.or(conditions.toArray(new Predicate[conditions.size()])),
	 * cb.and(findByUserId)) .orderBy(cb.asc(book.get(sortBy))); } else {
	 * cq.where(cb.or(conditions.toArray(new Predicate[conditions.size()])),
	 * cb.and(findByUserId)) .orderBy(cb.desc(book.get(sortBy))); }
	 * 
	 * TypedQuery<Book> q = em.createQuery(cq); Pageable page =
	 * PageRequest.of(pageIndex - 1, pageSize);
	 * 
	 * List<Book> books = q.getResultList();
	 * 
	 * Page<Book> result = new PageImpl<Book>(books, page, books.size()); return
	 * result.getContent();
	 * 
	 * }
	 */

}
