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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.sagar.entity.Book;
import com.sagar.entity.User;

@Repository
public class UserDaoCriteria {

	@Autowired
	EntityManager em;

	public User getUserById(int id) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		cq.select(user);
		cq.where(cb.equal(user.get("id"), id));
		TypedQuery<User> q = em.createQuery(cq);
		return q.getSingleResult();

	}

	public List<Book> getBooks(String sortBy, String sortOrder, int pageSize, String any, int userId,int pageIndex) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Book> cq = cb.createQuery(Book.class);
		Root<Book> book = cq.from(Book.class);

		Path<Integer> usr_Id = book.join("user").get("id");

		ArrayList<Predicate> conditions = new ArrayList<>();
		conditions.add(cb.like(book.get("bookName"), "%" + any + "%"));
		conditions.add(cb.like(book.get("isbn"), "%" + any + "%"));
		conditions.add(cb.like(book.get("author"), "%" + any + "%"));
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
		 	
		if(pageIndex==1) {
			return query.setFirstResult(pageIndex).setMaxResults(pageSize) .getResultList();
		}else {
			return query.setFirstResult(pageSize*(pageIndex-1)) .setMaxResults(pageSize) .getResultList();
		}		


	}

	public User getUserByAny(String any) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);

		ArrayList<Predicate> conditions = new ArrayList<>();
		conditions.add(cb.like(user.get("fname"), "%" + any + "%"));
		conditions.add(cb.like(user.get("lname"), "%" + any + "%"));
		conditions.add(cb.like(user.get("email"), "%" + any + "%"));

		cq.select(user);
		cq.where(cb.or(conditions.toArray(new Predicate[conditions.size()])));

		TypedQuery<User> q = em.createQuery(cq);
		return q.getSingleResult();
	}

}
