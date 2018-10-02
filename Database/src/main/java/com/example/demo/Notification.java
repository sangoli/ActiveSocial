package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notification {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

  
   // @Column(unique=true)
    //private String email;
    
	private String postOwner;
    private String friendToFbId;
    private String friendToFbName;
    private String friendToFbEmail;

    private String notifyToFriend;
    private String friendViewed;
    private String postLink;
    private String postId;
    private String postOwnerEmail;
    public String getFriendToFbEmail() {
		return friendToFbEmail;
	}
	public void setFriendToFbEmail(String friendToFbEmail) {
		this.friendToFbEmail = friendToFbEmail;
	}
	private String commentNotif;
	public String getCommentNotif() {
		return commentNotif;
	}
	public String getFriendToFbName() {
		return friendToFbName;
	}
	public void setFriendToFbName(String friendToFbName) {
		this.friendToFbName = friendToFbName;
	}
	public void setCommentNotif(String commentNotif) {
		this.commentNotif = commentNotif;
	}
	public String getPostOwnerEmail() {
		return postOwnerEmail;
	}
	public void setPostOwnerEmail(String postOwnerEmail) {
		this.postOwnerEmail = postOwnerEmail;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public Integer getId() {
		return id;
	}
	public String getPostOwner() {
		return postOwner;
	}
	public void setPostOwner(String postOwner) {
		this.postOwner = postOwner;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getFriendToFbId() {
		return friendToFbId;
	}
	public void setFriendToFbId(String friendToFbId) {
		this.friendToFbId = friendToFbId;
	}
	public String getNotifyToFriend() {
		return notifyToFriend;
	}
	public void setNotifyToFriend(String notifyToFriend) {
		this.notifyToFriend = notifyToFriend;
	}
	public String getFriendViewed() {
		return friendViewed;
	}
	public void setFriendViewed(String friendViewed) {
		this.friendViewed = friendViewed;
	}
	public String getPostLink() {
		return postLink;
	}
	public void setPostLink(String postLink) {
		this.postLink = postLink;
	}


	
	

}
