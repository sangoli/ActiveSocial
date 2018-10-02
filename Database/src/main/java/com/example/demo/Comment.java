package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String postOwner;
    private String postOwnerEmail;

   // @Column(unique=true)
    private String postID;
    private String friendID;
    public String getPostOwnerEmail() {
		return postOwnerEmail;
	}
	public void setPostOwnerEmail(String postOwnerEmail) {
		this.postOwnerEmail = postOwnerEmail;
	}
	private String friendComment;
    private String postOwnerReply;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPostOwner() {
		return postOwner;
	}
	public void setPostOwner(String postOwner) {
		this.postOwner = postOwner;
	}
	public String getPostID() {
		return postID;
	}
	public void setPostID(String postID) {
		this.postID = postID;
	}
	public String getFriendID() {
		return friendID;
	}
	public void setFriendID(String friendID) {
		this.friendID = friendID;
	}
	public String getFriendComment() {
		return friendComment;
	}
	public void setFriendComment(String friendComment) {
		this.friendComment = friendComment;
	}
	public String getPostOwnerReply() {
		return postOwnerReply;
	}
	public void setPostOwnerReply(String postOwnerReply) {
		this.postOwnerReply = postOwnerReply;
	}




	
	

}
