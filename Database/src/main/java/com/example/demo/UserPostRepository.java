package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserPostRepository extends CrudRepository<UserPost, Integer> {
	public List<UserPost> findAll();
		public UserPost findByEmail(String email);
		public List<UserPost> findAllByEmail(String email);
		public UserPost findByFacebookID(String facebookID);
		public List<UserPost> findAllByFacebookID(String facebookID);
		public UserPost findByEmailAndId(String email,Integer id);


		
	//Optional <Student> findByEmail(String email);
	//public Student findById(Integer id);
}
