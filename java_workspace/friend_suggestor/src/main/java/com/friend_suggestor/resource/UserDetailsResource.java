package com.friend_suggestor.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.friend_suggestor.models.UserDetails;
import com.friend_suggestor.repository.UserDetailsRepository;

@RestController
@RequestMapping("/user")
public class UserDetailsResource {

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@PostMapping("/create")
	public ResponseEntity<JsonNode> newUser(@RequestBody UserDetails user) {
		
		UserDetails saveUserObject = new UserDetails();
		final ObjectNode responseJson = JsonNodeFactory.instance.objectNode();
		try {
			
			String username = user.getUserName();
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String email = user.getEmail();
			String password = user.getPassword();
			
			if(userDetailsRepository.existsByUserName(username)) {
				responseJson.put("reason", "Username already exists!");
				throw new Exception();
			}
			
			saveUserObject.setUserName(username);
			saveUserObject.setFirstName(firstName);
			saveUserObject.setLastName(lastName);
			saveUserObject.setEmail(email);
			saveUserObject.setPassword(password);
			
			userDetailsRepository.save(saveUserObject);
			
			responseJson.put("status", "success");
			responseJson.put("username", username);
			responseJson.put("firstName", firstName);
			responseJson.put("lastName", lastName);
			responseJson.put("email", email);
			
			return new ResponseEntity<JsonNode>(responseJson, HttpStatus.OK);
		} catch (Exception e) {
			responseJson.put("status", "failure");
			if(responseJson.get("reason") == null) {
				responseJson.put("reason", e.getMessage());
//				responseJson.put("reason", "Error in saving user details!");
			}
			e.printStackTrace();
			return new ResponseEntity<JsonNode>(responseJson, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/all-users")
	public List<UserDetails> getAllUsers() {
		try {
			return userDetailsRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
