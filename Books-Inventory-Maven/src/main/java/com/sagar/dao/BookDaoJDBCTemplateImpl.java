
package com.sagar.dao;

import static com.sagar.propertiesLoader.PropertiesLoader.getQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import com.sagar.model.Book;

@Repository("booksJDBC")
public class BookDaoJDBCTemplateImpl implements BookDao{

	@Autowired
	public JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public List<Book> findAll() {
		return template.execute(getQuery("get_all_books"), new PreparedStatementCallback<List<Book>>() {

			@Override
			public List<Book> doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

				ResultSet rs = ps.executeQuery();

				Book book = null;
				List<Book> books = new ArrayList<>();

				while (rs.next()) {
					book = new Book();
					book.setName(rs.getString(2));
					book.setId(rs.getInt(1));
					book.setISBN(rs.getString(3));
					book.setAuthor(rs.getString(4));
					books.add(book);
					book = null;
				}

				return books;
			}

		});
	}

	@Override
	public void save(Book book) {
		template.execute(getQuery("insert_book"), new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, book.getName());
				ps.setString(2, book.getISBN());
				ps.setString(3, book.getAuthor());
				return ps.execute();
			}

		});

	}

	@Override
	public void update(Book book) {
		template.execute(getQuery("update_book"), new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setString(1, book.getName());
				ps.setString(2, book.getISBN());
				ps.setString(3, book.getAuthor());
				ps.setInt(4, book.getId());
				return ps.executeUpdate() > 0;
			}

		});

	}

	@Override
	public void delete(int id) {
		template.execute(getQuery("delete_book_by_id"), new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, id);
				return ps.execute();
			}

		});

	}

	@Override
	public Book findById(int id) {
		return template.execute(getQuery("select_book_by_id"), new PreparedStatementCallback<Book>() {

			@Override
			public Book doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				Book book = new Book();
				if (rs.next()) {
					book.setName(rs.getString(2));
					book.setId(rs.getInt(1));
					book.setISBN(rs.getString(3));
					book.setAuthor(rs.getString(4));
				}

				return book;
			}

		});

	}

	/*
	 * @Override public Book findByISBN(int isbn) { return
	 * template.execute(getQuery("select_book_by_isbn"), new
	 * PreparedStatementCallback<Book>() {
	 * 
	 * @Override public Book doInPreparedStatement(PreparedStatement ps) throws
	 * SQLException, DataAccessException { ps.setInt(1, isbn); ResultSet rs =
	 * ps.executeQuery(); Book book = new Book(); if (rs.next()) {
	 * book.setName(rs.getString(2)); book.setId(rs.getInt(1));
	 * book.setISBN(rs.getString(3)); book.setAuthor(rs.getString(4)); }
	 * 
	 * return book; }
	 * 
	 * }); }
	 */

	/*
	 * @Override public List<List<Object>> getColumnData() {
	 * 
	 * List<List<Object>> tableData=new ArrayList<List<Object>>();
	 * 
	 * return template.execute(getQuery("get_all_books"), new
	 * PreparedStatementCallback<List<List<Object>> >() {
	 * 
	 * @Override public List<List<Object>> doInPreparedStatement(PreparedStatement
	 * ps) throws SQLException, DataAccessException { ResultSet resultSet =
	 * ps.executeQuery();
	 * 
	 * while(resultSet.next()) { ArrayList<Object> tempp=new ArrayList<>();
	 * 
	 * for(int i=1;i<=resultSet.getMetaData().getColumnCount();i++) {
	 * tempp.add(resultSet.getString(i));//////////////we can add index here also }
	 * tableData.add(tempp); }
	 * 
	 * return tableData;
	 * 
	 * } }); }
	 * 
	 * @Override public List<Object> getColumnNames() { return
	 * template.execute(getQuery("get_all_books"), new
	 * PreparedStatementCallback<List<Object>>() {
	 * 
	 * @Override public List<Object> doInPreparedStatement(PreparedStatement ps)
	 * throws SQLException, DataAccessException {
	 * 
	 * ResultSet rs = ps.executeQuery(); ResultSetMetaData columnNames =
	 * rs.getMetaData(); List<Object> columnNamesList = new ArrayList<Object>();
	 * 
	 * for (int i = 1; i <= columnNames.getColumnCount(); i++) {
	 * columnNamesList.add(columnNames.getColumnName(i)); }
	 * 
	 * return columnNamesList; }
	 * 
	 * }); }
	 */
}
