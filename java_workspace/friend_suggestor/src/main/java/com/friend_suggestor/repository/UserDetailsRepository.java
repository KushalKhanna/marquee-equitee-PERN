package com.friend_suggestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.friend_suggestor.models.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>{

	public boolean existsByUserName(String userName);
	
}
