package com.lgeratech.demo.controllers;

import com.lgeratech.demo.models.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@PostMapping(value = "/createUser")
	public ResponseEntity<String> createUser(@RequestBody User user){
		Object[] params = new Object[] { user.getName(), user.getAge(), user.getGender()};
	    jdbcTemplate.update("INSERT INTO user(name, age, gender) VALUES (?,?,?)", params);
		return new ResponseEntity<String>("User has been added succesfully", HttpStatus.OK);
	}
	@GetMapping(value = "/retrieveUsers")
	public ResponseEntity<List<User>> retrieveUserList(){
		List<User> users = jdbcTemplate.query("SELECT id, name, age, gender FROM user",
				(rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("gender")));
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	@PutMapping(value = "/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody User user){
	  	Object[] params = new Object[] { user.getName(), user.getAge(), user.getGender(), user.getId()};
	    jdbcTemplate.update("UPDATE user set name = ?, age = ?, gender = ? where id = ?", params);
		return new ResponseEntity<String>("User has been updated succesfully", HttpStatus.OK);
	}
	@DeleteMapping(value = "/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id){
	 	Object[] params = new Object[] {id};
	    jdbcTemplate.update("DELETE FROM user WHERE id = ?", params);
		return new ResponseEntity<String>("User has been deleted succesfully", HttpStatus.OK);
	}
}