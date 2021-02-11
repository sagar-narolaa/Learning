package com.sagar.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Book {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)	
    protected int id;
	@Column(name="Book_Name")
    protected String name;
	@Column(name="ISBN")
    protected String ISBN;
	@Column(name="Author")
    protected String author;

    public Book() {System.out.println("default constructor");}

    public Book(String name,String ISBN,String Author) {
        super();
        System.out.println("3 args constructor");
     this.name=name;
     this.ISBN=ISBN;
     this.author=Author;
    }

    public Book(int id,String name,String ISBN,String Author) {    	
        super();
        System.out.println("4 args constructor");
        this.id = id;
        this.name=name;
        this.ISBN=ISBN;
        this.author=Author;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public static List<String> getColumnNames(){
		return Arrays.asList("ID","NAME","ISBN","AUTHOR");
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", ISBN=" + ISBN + ", author=" + author + "]";
	}
	
	
}