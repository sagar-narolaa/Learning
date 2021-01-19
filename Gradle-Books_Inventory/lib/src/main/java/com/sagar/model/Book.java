package com.sagar.model;

public class Book {
    protected int id;
    protected String name;
    protected String ISBN;
    protected String author;

    public Book() {}

    public Book(String name,String ISBN,String Author) {
        super();
     this.name=name;
     this.ISBN=ISBN;
     this.author=Author;
    }

    public Book(int id,String name,String ISBN,String Author) {
        super();
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

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}