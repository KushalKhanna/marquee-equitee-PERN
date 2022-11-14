package com.friend_suggestor.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_friends")
public class UserFriends {

	@Id
	@SequenceGenerator(name="seq-gen-user-friends",sequenceName="MY_SEQ_GEN_USER_FRIENDS",initialValue = 1, allocationSize=1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen-user-friends")
//	@SequenceGenerator(name = "UserFriends_generator", sequenceName = "friends_seq", allocationSize=50)
    @Column(name = "id")
	private Integer id;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "friend_id")
	private Integer friendId;
	
	@Column(name = "is_accepted")
	private char isAccepted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFriendId() {
		return friendId;
	}

	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}

	public char getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(char isAccepted) {
		this.isAccepted = isAccepted;
	}
	
	
	
}
