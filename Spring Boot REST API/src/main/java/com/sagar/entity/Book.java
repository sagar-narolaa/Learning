package com.sagar.entity;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	protected String bookName;
	protected String isbn;
	protected String author;
	
	//@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnoreProperties("books")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static List<String> getColumnNames() {
		return Arrays.asList("ID", "NAME", "ISBN", "AUTHOR");
	}

	public int getId() {
		return this.id;
	}

	public String getBookName() {
		return this.bookName;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setBookName(final String bookName) {
		this.bookName = bookName;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

}