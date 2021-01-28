package com.sagar.dao;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.sagar.ExportToExcel.ExportToExcel;
import com.sagar.model.Book;

public class BookDAO {

	private static String insert_book = null;
	private static String select_book_by_id = null;
	private static String get_all_books = null;
	private static String delete_book_by_id = null;
	private static String update_book = null;
	private static String convert_to_excel = null;

	public BookDAO() {
	}

	public static void call() {
		Properties pr = new Properties();
		try {

			InputStream iss = BookDAO.class.getResourceAsStream("/database_queries.properties");
			pr.load(iss);
			System.out.println("=========================================================" + pr.getProperty("insert"));
			System.out.println(
					"=========================================================" + pr.getProperty("convert_to_excel"));

			insert_book = pr.getProperty("insert");
			select_book_by_id = pr.getProperty("see_by_id");
			get_all_books = pr.getProperty("see_all_records");
			delete_book_by_id = pr.getProperty("delete_by_id");
			update_book = pr.getProperty("update_by_id");
			convert_to_excel = pr.getProperty("convert_to_excel");

		} catch (Exception e) {
			System.err.println("Exception in Setting database queries");////////////////////
		}

	}

	protected static Connection getConnection() {
		call();
		Connection connection = null;

		String driver = null;
		String url = null;
		String username = null;
		String pwd = null;

		try {

			InputStream iss = BookDAO.class.getResourceAsStream("/database.properties");
			Properties pr = new Properties();
			pr.load(iss);
			System.out.println("======================================================Database.prop============="
					+ pr.getProperty("db_url"));

			/*
			 * Properties pr=new Properties(); FileReader fr=new
			 * FileReader("/src/main/resources/database.properties");
			 * 
			 * pr.load(fr);
			 */
			// System.out.println(pr.getProperty("db_driver"));

			driver = pr.getProperty("db_driver");
			url = pr.getProperty("db_url");
			username = pr.getProperty("db_username");
			pwd = pr.getProperty("db_pwd");

			System.out.println("Setting Database.prop file succesfull");
		} catch (Exception e) {
			System.out.println("Exception in setting database.prop file");
		}

		try {
			Class.forName(driver);
			System.out.println("Class " + driver + " Loaded Successfully");
			connection = DriverManager.getConnection(url, username, pwd);
			// "jdbc:mysql://localhost:3306/books_inventory","root","admin"
		} catch (Exception e) {
			System.out.println("Exception in getting connection");
			e.printStackTrace();
		}

		return connection;
	}

	public void insertBook(Book book) throws SQLException {
		System.err.println("into the insert book");
		System.out.println(insert_book);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(insert_book)) {
			preparedStatement.setString(1, book.getName());
			preparedStatement.setString(2, book.getISBN());
			preparedStatement.setString(3, book.getAuthor());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Book selectBook(int id) {
		Book book = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(select_book_by_id);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id1 = Integer.parseInt(rs.getString("ID"));
				String name = rs.getString("Book_Name");
				String ISBN = rs.getString("ISBN");
				String Author = rs.getString("Author");
				book = new Book(id1, name, ISBN, Author);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	public static List<Book> selectAllBooks() {
		List<Book> books = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(get_all_books);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("ID"));
				String name = rs.getString("Book_Name");
				String ISBN = rs.getString("ISBN");
				String Author = rs.getString("Author");
				books.add(new Book(id, name, ISBN, Author));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

	public boolean deleteBook(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(delete_book_by_id);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateBook(Book book) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(update_book);) {
			statement.setString(1, book.getName());
			statement.setString(2, book.getISBN());
			statement.setString(3, book.getAuthor());
			statement.setInt(4, book.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public static int convertToExcel() throws FileNotFoundException {
		Connection connection = BookDAO.getConnection();
		try {
			PreparedStatement pr = connection.prepareStatement(convert_to_excel);
			ResultSet rsResultSet = pr.executeQuery();
			ResultSetMetaData rsMetaDeta = rsResultSet.getMetaData();
			ExportToExcel ex = new ExportToExcel(rsResultSet, "books", rsMetaDeta);
			int status = ex.generateExcel();
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

	}

	public boolean signUp(String fname, String lname, String email, String pwd) {
		Connection connection = BookDAO.getConnection();
		System.out.println("First Name Is===========================================" + fname);
		try {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO users (FNAME,LNAME,EMAIL,PASSWORD) VALUES (?, ?, ?, ?)");
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, email);
			ps.setString(4, pwd);

			boolean status = ps.execute();
			System.out.println("Signup Sucessfull");
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public int signIn(String email, String pwd) {
		Connection connection = BookDAO.getConnection();

		try {
			System.out.println("=====================================" + email);
			System.out.println("=====================================" + pwd);
			PreparedStatement EmailCheck = connection.prepareStatement("select EMAIL from users where EMAIL=?");
			PreparedStatement PwdCheck = connection.prepareStatement("select PASSWORD from users where EMAIL=?");
			
			EmailCheck.setString(1, email);
			PwdCheck.setString(1, email);		
			

			ResultSet emailResult = EmailCheck.executeQuery();	
			ResultSet pwdResult =PwdCheck.executeQuery();	
			
			
			if( emailResult.next() && pwdResult.next() ) {
				return 1;	//email and pwd true
			}else if(emailResult.next()==true || pwdResult.next()==false){
				return 0;    //wrong pwd
			}else {
				return -1;	//both wrong
			}


		} catch (SQLException e) {
			e.printStackTrace();
			return -1;

		}		

	}
}