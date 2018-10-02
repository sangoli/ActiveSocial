package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
	//public List<Notification> findAll();
		//public Notification findByEmail(String email);
		//public List<Notification> findAllByEmail(String email);
		public Notification findByPostOwner(String postOwner);
		public List<Notification> findAllByPostId(String postId);

		public List<Notification> findAllByfriendToFbId(String friendToFbId);
		public Notification findByFriendToFbIdAndPostOwnerAndPostId(String friendToFbId,String postOwner,String postId);
		public List<Notification> findAllByfriendToFbIdAndNotifyToFriend(String friendToFbId,String notifyToFriend);
		public List<Notification> findAllByfriendToFbIdAndNotifyToFriendAndFriendViewed(String friendToFbId,String notifyToFriend,String friendViewed);
public List<Notification> findByPostOwnerAndFriendToFbIdAndPostId(String postOwner,String friendToFbId,String postId);
public List<Notification> findAllByPostOwnerEmailAndFriendToFbIdAndPostId(String postOwnerEmail,String friendToFbId,String postId);


		public List<Notification> findAllByPostOwner(String postOwner);
		public List<Notification> findAllByPostOwnerAndCommentNotif(String postOwner,String commentNotif);
		public List<Notification> findAllByPostOwnerEmailAndCommentNotif(String postOwnerEmail,String commentNotif);



	//Optional <Student> findByEmail(String email);
	//public Student findById(Integer id);
}
