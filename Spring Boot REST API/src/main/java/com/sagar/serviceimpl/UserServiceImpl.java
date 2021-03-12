package com.sagar.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sagar.cache.SimpleCache;
import com.sagar.dao.UserDao;
import com.sagar.dao.UserDaoCriteria;
import com.sagar.entity.User;
import com.sagar.service.UserService;

@Service
public class UserServiceImpl implements UserService/* , UserDetailsService */ {

	@Autowired
	UserDao userDao;
	@Autowired
	UserDaoCriteria userDao1;

	@Autowired
	SimpleCache<Integer, User> cache;

	public UserServiceImpl() {
	}

	public List<User> getAllUsers() {
		List<User> users = cache.getAll();
		return users.size() == 0 ? userDao.findAll() : users;

	}

	public Optional<User> saveUser(User user) {
		userDao.save(user);
		User userr=userDao.findByFname(user.getFname()).get();
		cache.put(userr.getId(),userr);
		//userDao.save(user);
		return Optional.ofNullable(cache.get(userr.getId()).get());
	}

	public void updateUser(User user) {
		cache.put(user.getId(), user);
		userDao.save(user);
	}

	public void deleteUser(int id) {
		cache.remove(id);
		userDao.deleteById(id);
	}

	@Override
	public void deleteAllUsers() {
		cache.removeAll();
		userDao.deleteAllInBatch();
	}

	@Override
	public User getUserById(int id) {
		return cache.get(id).get();
	}

	public void saveMultipleUsers(List<User> users) {
		//Map<Integer, User> map = users.stream().collect(Collectors.toMap(User::getId, (user) -> user));
		users.forEach((user)->cache.put(user.getId(), user));
		
		userDao.saveAll(users);
	}

	public void deleteMultipleUsers(List<User> users) {
		userDao.deleteInBatch(users);
	}

	public List<User> usersWithPagination(int index, int size) {
		Pageable usersWithLimit = PageRequest.of(index, size);
		Page<User> users = userDao.findAll(usersWithLimit);
		return users.getContent();
	}

	public List<User> usersWithPageAndSort(int pageIndex, int size, String order, String... sortBy) {
		Pageable usersWithSort = order.toUpperCase().equals("ASC")
				? PageRequest.of(pageIndex - 1, size, Sort.by(sortBy).ascending())
				: PageRequest.of(pageIndex - 1, size, Sort.by(sortBy).descending());

		List<User> users = userDao.findAll(usersWithSort).getContent();
		return users;
	}

	public List<User> findByAnytg(String param) {
		List<User> users = userDao.findByAny(param);
		return users;
	}

	public List<User> findByLname(String lname) {
		return userDao.findByLname(lname);
	}

	public Optional<User> findByFname(String fname) {
		return userDao.findByFname(fname);
	}

	@Override
	public User getUserByAny(String any) {

		return userDao1.getUserByAny(any);
	}

	/*
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException { UserDetailss user=
	 * userDao1.findByUserName(username).get(0); return new UserDetail(user); }
	 */
}