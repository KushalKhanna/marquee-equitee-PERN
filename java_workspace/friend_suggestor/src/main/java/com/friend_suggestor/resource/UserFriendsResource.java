package com.friend_suggestor.resource;

import java.util.List;

import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.friend_suggestor.models.UserFriends;
import com.friend_suggestor.repository.UserFriendsRepository;

@RestController
@RequestMapping("/friends")
public class UserFriendsResource {

	@Autowired
	private UserFriendsRepository userFriendsRepository;
	
	@PostMapping("/add/{userId}/{friendId}")
	public ResponseEntity<JsonNode> sendFriendRequest(@PathVariable("userId") Integer userId, @PathVariable("friendId") Integer friendId) {
//		CHECK IF USER ID AND FRIEND ID PRESENT IN RESP DB'S
		final ObjectNode responseJson = JsonNodeFactory.instance.objectNode();
		UserFriends saveUserFriendsObject;
		try {
			saveUserFriendsObject = userFriendsRepository.userAFriendOfUserB(userId, friendId);
			if(saveUserFriendsObject != null) {
				responseJson.put("reason", "Already sent friend request!");
				if(saveUserFriendsObject.getIsAccepted() == 'Y') {
					responseJson.put("reason", "Already friends!");
				}
				throw new Exception();
			} else if(userFriendsRepository.userAFriendOfUserB(friendId, userId) != null) {
				UserFriends sentRequestEntry = userFriendsRepository.userAFriendOfUserB(friendId, userId);
				sentRequestEntry.setIsAccepted('Y');
				userFriendsRepository.save(sentRequestEntry);
				saveUserFriendsObject = new UserFriends();
				saveUserFriendsObject.setIsAccepted('Y');
				saveUserFriendsObject.setUserId(userId);
				saveUserFriendsObject.setFriendId(friendId);
				
			} else {
				saveUserFriendsObject = new UserFriends();
				saveUserFriendsObject.setIsAccepted('N');
				saveUserFriendsObject.setUserId(userId);
				saveUserFriendsObject.setFriendId(friendId);
			}	
			
			userFriendsRepository.save(saveUserFriendsObject);
			
			responseJson.put("status", "success");
			return new ResponseEntity<JsonNode>(responseJson, HttpStatus.OK);
		} catch (Exception e) {
			responseJson.put("status", "failure");
			if(responseJson.get("reason") == null) {
//				responseJson.put("reason", "Error in sending friend request!");
				responseJson.put("reason", e.getMessage());
			}
			e.printStackTrace();
			return new ResponseEntity<JsonNode>(responseJson, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/friend-requests/{userId}")
	public ResponseEntity<JsonNode> getPendingFriendRequests(@PathVariable Integer userId) {
		final ObjectNode responseJson = JsonNodeFactory.instance.objectNode();
		ObjectMapper mapper = new ObjectMapper();
		try {
//			String query = "SELECT * FROM user_friends WHERE user_id = ? && is_accepted = 'N'";
			List<UserFriends> pendingFriendRequestList = userFriendsRepository.getPendingFreindRequests(userId);
			if(!pendingFriendRequestList.isEmpty()) {
				ArrayNode pendingFriendList = mapper.valueToTree(pendingFriendRequestList);
				responseJson.put("friend_requests", pendingFriendList);
				return new ResponseEntity<JsonNode>(responseJson, HttpStatus.OK);
			}
			responseJson.put("status", "failure");
			responseJson.put("reason", "No pending friend requests!");
			return new ResponseEntity<JsonNode>(responseJson, HttpStatus.NOT_FOUND);
		} catch(TypeMismatchException e) {
			e.printStackTrace();
			responseJson.put("status", "failure");
			responseJson.put("reason", e.getMessage());
			return new ResponseEntity<JsonNode>(responseJson, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			responseJson.put("status", "failure");
			responseJson.put("reason", e.getMessage());
			return new ResponseEntity<JsonNode>(responseJson, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<JsonNode> getallFriends(@PathVariable Integer userId) {
		final ObjectNode responseJson = JsonNodeFactory.instance.objectNode();
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<UserFriends> userFriendList = userFriendsRepository.findByUserIdAndIsAcceptedEquals(userId, 'Y');
			if(!userFriendList.isEmpty()) {
				ArrayNode friendList = mapper.valueToTree(userFriendList);
				responseJson.put("friends", friendList);
				return new ResponseEntity<JsonNode>(responseJson, HttpStatus.OK);
			}
			responseJson.put("status", "failure");
			responseJson.put("reason", "No friends!");
			return new ResponseEntity<JsonNode>(responseJson, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			responseJson.put("status", "failure");
			responseJson.put("reason", e.getMessage());
			return new ResponseEntity<JsonNode>(responseJson, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
