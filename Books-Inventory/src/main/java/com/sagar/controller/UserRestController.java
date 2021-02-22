package com.sagar.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.sagar.model.User;
import com.sagar.service.UserRestService;

@Path("/users")
public class UserRestController {

	@Autowired
	private UserRestService userService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public List<User> getAllUsers() {
		System.out.println("============controller called=========");
		return userService.getAllUsers();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/save")
	public List<User> saveUser(User user) {
		userService.saveUser(user);
		return getAllUsers();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public List<User> update(User user) {
		userService.updateUser(user);
		return getAllUsers();
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{id}")
	public List<User> delete(@PathParam("id") int id) {
		userService.deleteUser(id);
		return getAllUsers();
	}

}
