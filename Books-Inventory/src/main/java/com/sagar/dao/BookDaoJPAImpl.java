
package com.sagar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sagar.model.Book;

@Repository("booksJPA")
public class BookDaoJPAImpl implements BookDao {

	// EntityManagerFactory
	// emf=Persistence.createEntityManagerFactory("Book_Details");
	// @PersistenceContext()

	@PersistenceContext
	EntityManager em;
	// = emf.createEntityManager();

	@Override
	public List<Book> findAll() {
		Query query = em.createQuery("from Book");
		List<Book> books = query.getResultList();
		return books;
	}

	@Override
	public void save(Book book) { // em.getTransaction().begin();
		em.persist(book);
		// em.getTransaction().commit();
	}

	@Override
	public void update(Book book) {
		Book exitingBook = em.find(Book.class, book.getId());
		exitingBook.setAuthor(book.getAuthor());
		exitingBook.setISBN(book.getISBN());
		exitingBook.setName(book.getName());
	}
	
	
	@Override public void delete(int id) {
		Book book=em.find(Book.class,id);
		em.remove(book);
	}
  //em.getTransaction().begin(); 
  //em.getTransaction().commit(); }

	@Override
	public Book findById(int id) {
		return em.find(Book.class, id);
	}

	/*
	 * @Override public List<Object> getColumnData() { List<Book> books = findAll();
	 * List<Object> obj = new ArrayList<Object>(); books.forEach((book) ->
	 * obj.add(book)); return obj; }
	 * 
	 * @Override public List<Object> getColumnNames() { List<Object> obj = new
	 * ArrayList<Object>(); Book.getColumnNames().forEach((book) -> obj.add(book));
	 * return obj; }
	 */

}
