package com.sagar.dao;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;

import com.sagar.Service.ExportToExcel;
import com.sagar.model.Book;


@Service
public class Book_DAO {
	public JdbcTemplate template;
	
	private static String insert_book = null;
	private static String select_book_by_id = null;
	private static String get_all_books = null;
	private static String delete_book_by_id = null;
	private static String update_book = null;
	private static String convert_to_excel = null;
	private static String insert_user = null;
	private static String verify_user=null;
	private static String user_exist=null;
	
	public static void call() {
		Properties pr = new Properties();
		try {

			InputStream iss = Book_DAO.class.getResourceAsStream("/database_queries.sql");
			pr.load(iss);
			
			insert_book = pr.getProperty("insert");
			select_book_by_id = pr.getProperty("see_by_id");
			get_all_books = pr.getProperty("see_all_records");
			delete_book_by_id = pr.getProperty("delete_by_id");
			update_book = pr.getProperty("update_by_id");
			convert_to_excel = pr.getProperty("convert_to_excel");
			
			
			insert_user=pr.getProperty("insert_user");
			verify_user=pr.getProperty("verify_user");
			user_exist=pr.getProperty("user_exist");
			System.out.println(verify_user);

		} catch (Exception e) {
			System.err.println("Exception in Setting database queries");////////////////////
		}

	}
	
	public Book_DAO() {
		System.out.println("Book_DAO Constructor called");
		call();		
	}
	
	public void setTemplate(JdbcTemplate template) {
		this.template=template;
	}
	
	public void insertBook(Book book) throws SQLException {
		 template.execute(insert_book,new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {				
				ps.setString(1,book.getName());
				ps.setString(2, book.getISBN());
				ps.setString(3,book.getAuthor());
				return ps.execute();						
			}			
			
		 });				
	}

	public Book selectBook(int id) {
		return template.execute(select_book_by_id,new PreparedStatementCallback<Book>() {

			@Override
			public Book doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {				
				ps.setInt(1,id);
				ResultSet rs = ps.executeQuery();
				Book book=new Book();
				if(rs.next()) {
					book.setName(rs.getString(2));
					book.setId(rs.getInt(1));
					book.setISBN(rs.getString(3));
					book.setAuthor(rs.getString(4));
				}
				
				return book;				
			}
			
		 });		
	}

	public  List<Book> selectAllBooks() {
		System.out.println(get_all_books);
		return template.execute(get_all_books,new PreparedStatementCallback<List<Book>>() {
			
			@Override
			public List<Book> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {				
		
				ResultSet rs = ps.executeQuery();
				
				Book book=null;
				List<Book> books=new ArrayList<>();
				
				while(rs.next()) {
					book=new Book();
					book.setName(rs.getString(2));
					book.setId(rs.getInt(1));
					book.setISBN(rs.getString(3));
					book.setAuthor(rs.getString(4));
					books.add(book);
					book=null;
				}
				
				return books;				
			}
			
		 });	
		
/*		return template.query(get_all_books,new RowMapper<Book>(){    
	        public Book mapRow(ResultSet rs, int row) throws SQLException {    
	            Book e=new Book();    
	            e.setId(rs.getInt(1));    
	            e.setName(rs.getString(2));    
	            e.setISBN(rs.getString(3));
	            e.setAuthor(rs.getString(4));
	            return e;    
	        }    
	    });*/
		
	}
	
	public boolean deleteBook(int id) throws SQLException {
		 return template.execute(delete_book_by_id,new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {				
				ps.setInt(1,id);
				return ps.execute();
			}
			
		 });				 	
		 
	}

	public boolean updateBook(Book book) throws SQLException {
		
		return template.execute(update_book,new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {				
				ps.setString(1, book.getName());
				ps.setString(2, book.getISBN());
				ps.setString(3, book.getAuthor());
				ps.setInt(4, book.getId());
				return ps.executeUpdate()>0;
			}
			
		 });
		
	}

	public int convertToExcel() throws FileNotFoundException {
		
		return template.execute(convert_to_excel,new PreparedStatementCallback<Integer>() {

			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {				
				ResultSet rsResultSet = ps.executeQuery();
				ResultSetMetaData rsMetaDeta = rsResultSet.getMetaData();
				ExportToExcel ex = new ExportToExcel(rsResultSet, "books", rsMetaDeta);				
				try {
					return ex.generateExcel();
				} catch (FileNotFoundException | SQLException e) {			
					e.printStackTrace();
					return -1;
				}
			}
			
		 });
	}

	public boolean signUp(String fname, String lname, String email, String pwd) {
			
		boolean exist= template.execute(user_exist,new PreparedStatementCallback<Boolean>() {
			
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {				
				
				ps.setString(1, fname);
				ps.setString(2, lname);

				System.out.println( ps.toString() );
				boolean status = ps.execute();
				//System.out.println("Signup Sucessfull");
				return status;				
			}			
		 });
		
		if(exist) {
			return !exist;
		}else {
			
			return template.execute(insert_user,new PreparedStatementCallback<Boolean>() {
				
				@Override
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {				
					
					ps.setString(1, fname);
					ps.setString(2, lname);
					ps.setString(3, email);
					ps.setString(4, pwd);
					System.out.println( ps.toString() );
					boolean status = !ps.execute();
					//System.out.println("Signup Sucessfull");
					return status;				
				}			
			 });
			
		}

	}

	public int signIn(String email, String pwd) {
		/*
		 * template.execute("select EMAIL from users where EMAIL=?");
		 * template.execute("select PASSWORD from users where EMAIL=?");
		 */
		System.out.println(verify_user);
		//"select EMAIL from users where EMAIL="+"\""+email+"\""
		int Status=template.execute(verify_user,new PreparedStatementCallback<Integer>() {
			
			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				
				ps.setString(1,email);
				ps.setString(2,pwd);	
				System.out.println(ps.toString());
				
				 ResultSet rs= ps.executeQuery();

				 int status=rs.next()?1:0;
				 return status;
			}			
		 });
		
		return Status;		
}
}
