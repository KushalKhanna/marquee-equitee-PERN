package com.friend_suggestor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.friend_suggestor.models.UserFriends;

public interface UserFriendsRepository extends JpaRepository<UserFriends, Integer>{

	
	@Query(value = "SELECT * FROM user_friends u WHERE u.user_id = :userId and is_accepted = 'N'", nativeQuery = true)
	public List<UserFriends> getPendingFreindRequests(@Param("userId") Integer userId);
	
	@Query(value = "SELECT * FROM user_friends u WHERE u.user_id = :userId and u.friend_id = :friendId", nativeQuery = true)
	public UserFriends userAFriendOfUserB(Integer userId, Integer friendId);
	
	public List<UserFriends>findByUserIdAndIsAcceptedEquals(Integer userId, char is_accepted);
}
