package com.sagar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sagar.entity.User;
import com.sagar.service.UserService;
import com.sagar.serviceimpl.ProductSearchService;

@RestController
@RequestMapping({ "/user" })
public class UserController {
	@Autowired
	private UserService userService;
  @Autowired
  ProductSearchService service;
	public UserController() {
	}

	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping({ "/list" })
	public List<User> getAllUsers1(@RequestParam(required = false) Integer pageIndex,
			@RequestParam(required = false) Integer sizeOfPage, @RequestParam(required = false) String sortingOrder,
			@RequestParam(required = false) String sortBy) {
		if (sortingOrder == null) {
			return getAllUsers();
		}
		return userService.usersWithPageAndSort(pageIndex, sizeOfPage, sortingOrder, new String[] { sortBy });
	}
	
	
	
	

	@PostMapping({ "/save" })
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user).get();		
		//return getAllUsers();
	}

	@PostMapping({ "/saveMulti" })
	public List<User> saveListofUsers(@RequestBody List<User> list) {
		userService.saveMultipleUsers(list);
		return getAllUsers();
	}

	@DeleteMapping({ "/deleteMulti" })
	public List<User> deleteListofUsers(@RequestBody List<User> list) {
		userService.deleteMultipleUsers(list);
		return getAllUsers();
	}

	@PutMapping({ "/update" })
	public List<User> update(@RequestBody User user) {
		userService.updateUser(user);
		return getAllUsers();
	}

	@DeleteMapping({ "/delete/{id}" })
	public List<User> delete(@PathVariable("id") int id) {
		userService.deleteUser(id);
		return getAllUsers();
	}
	
	@DeleteMapping({ "/deleteAll" })
	public String deleteAll() {
		userService.deleteAllUsers();
		return "Erased All Users";
	}
	
	

	@GetMapping({ "/pagintion/{index}/{sizeOfPage}" })
	public List<User> allUsersWithPage(@PathVariable int index, @PathVariable int sizeOfPage) {
		return userService.usersWithPagination(index, sizeOfPage);
	}

	/*
	 * @GetMapping({ "/findByAny/{searchKeyword}" }) public List<User>
	 * findByAny(@PathVariable String searchKeyword) { return
	 * userService.findByAnytg(searchKeyword); }
	 */

	@GetMapping({ "/findByFname/{fname}" })
	public List<User> findByFname(@PathVariable String fname) {
		return userService.findByAnytg(fname);
	}

	@GetMapping("/findByAny/{any}")
	public User getBooksFromUserName(@PathVariable String any) {
		return userService.getUserByAny(any);
	}
	@GetMapping("/findById/{id}")
	public User getBooksFromUserId(@PathVariable int id) {
		return userService.getUserById(id);
	}
}
