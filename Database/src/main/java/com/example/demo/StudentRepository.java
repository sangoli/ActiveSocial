package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
	public List<Student> findAll();
		public Student findByEmail(String email);
		public Student findByFacebookID(String facebookID);
	//Optional <Student> findByEmail(String email);
	//public Student findById(Integer id);
}
