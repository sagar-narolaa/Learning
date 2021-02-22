
package com.sagar.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sagar.dao.UserDao;
import com.sagar.entity.UserEntity;
import com.sagar.model.User;

@Service

@Transactional
public class AuthenticationService {

	@Autowired
	@Qualifier("UserDaoJPA")
	private UserDao userDao;

	public boolean signUp(@ModelAttribute UserEntity user, Model model) {

		String Fname = user.getFname();
		String Lname = user.getLname();
		String Email = user.getEmail();
		String pwd = user.getPwd();

		boolean status = userDao.signUp(user);
		return status;
	}

	public int login(String Email, String Pwd) {

		String email = Email;
		String pwd = Pwd;

		int status = userDao.signIn(email, pwd);

		return status;

	}

}
