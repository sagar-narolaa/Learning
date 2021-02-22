
package com.sagar.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import com.sagar.entity.UserEntity;

import static com.sagar.propertiesLoader.PropertiesLoader.getQuery;

@Repository("UserDaoJDBC")
public class UserDaoImpl implements UserDao {

	@Autowired
	public JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public boolean signUp(String fname, String lname, String email, String pwd) {

		boolean exist = template.execute(getQuery("user_exist"), new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

				ps.setString(1, fname);
				ps.setString(2, lname);

				System.out.println(ps.toString());
				boolean status = ps.execute();

				return status;
			}
		});

		if (exist) {
			return !exist;
		} else {

			return template.execute(getQuery("insert_user"), new PreparedStatementCallback<Boolean>() {

				@Override
				public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

					ps.setString(1, fname);
					ps.setString(2, lname);
					ps.setString(3, email);
					ps.setString(4, pwd);
					System.out.println(ps.toString());
					boolean status = !ps.execute();

					return status;
				}
			});

		}

	}

	public int signIn(String email, String pwd) {

		int Status = template.execute(getQuery("verify_user"), new PreparedStatementCallback<Integer>() {

			@Override
			public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

				ps.setString(1, email);
				ps.setString(2, pwd);
				System.out.println(ps.toString());

				ResultSet rs = ps.executeQuery();

				int status = rs.next() ? 1 : 0;
				return status;
			}
		});

		return Status;
	}

	@Override
	public boolean signUp(UserEntity user) {
		return false;
	}

}
