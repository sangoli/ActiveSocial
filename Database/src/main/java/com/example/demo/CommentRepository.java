package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
	public List<Comment> findAll();
		public List<Comment> findAllByPostOwner(String postOwner);
		//public List<Comment> findAllByEmail(String email);
		public List<Comment> findAllByPostID(String postID);
		//public List<Comment> findAllByFacebookID(String facebookID);
		public Comment findByPostIDAndFriendIDAndFriendComment(String postID, String friendID, String friendComment);


		
	//Optional <Student> findByEmail(String email);
	//public Student findById(Integer id);
}
