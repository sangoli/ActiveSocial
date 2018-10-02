package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Base64.Decoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.util.IOUtils;

@Controller
public class PostManager {
	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private NotificationRepository notifRepo;
	//private AmazonClient amazonClient;
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private UserPostRepository postRepo;
	@GetMapping(value="/recordAudio")
	public ModelAndView renderFirstPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("base64Upload");
		return mv;
	}
	private AmazonClient amazonClient;
	@Autowired
    PostManager(AmazonClient amazonClient) {
       this.amazonClient = amazonClient;
   }
	
	@PostMapping(value="/base64Audio")
	public ModelAndView saveAudio(@RequestParam (name="recording1") String recording1,@RequestParam (name="desc") String post_text,HttpServletRequest req,@RequestPart(value = "file") MultipartFile imageFile) throws Exception
{
		//System.out.print(recording1);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("success");

if(recording1.isEmpty()) {
	throw new Exception("Empty");
	
}
Decoder decoder=Base64.getDecoder();
byte[] decoderbyte=decoder.decode(recording1.split(",")[1]);
FileOutputStream fos;
try {
fos= new FileOutputStream("Myaudio.webm");
fos.write(decoderbyte);
fos.close();
}
catch(IOException e){
	e.printStackTrace();
}
File file = new File("Myaudio.webm");
System.out.println(file.exists());

String imageLink=this.amazonClient.uploadFile(imageFile);
System.out.print(imageLink);

//FileInputStream input = new FileInputStream(file);
//MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
String audioLink=this.amazonClient.uploadAudioFile(new FileInputStream("Myaudio.webm"),"newaudio");
System.out.print(audioLink);
UserPost post=new UserPost();
post.setText(post_text);
post.setEmail(req.getSession().getAttribute("userId").toString());
post.setFacebookID(req.getSession().getAttribute("fbId").toString());
System.out.print(req.getSession().getAttribute("userId").toString());
System.out.print(req.getSession().getAttribute("fbId").toString());


post.setImageurl(imageLink);

post.setAudiourl(audioLink);

postRepo.save(post);

String[] splitted=	req.getSession().getAttribute("myFriendsListFbId").toString().split("/");
String[] friendsname=	req.getSession().getAttribute("myFriendsListFbName").toString().split("/");



//List<Notification> notif=notifRepo.findAllByPostOwner(req.getSession().getAttribute("fbId").toString());
//System.out.print(notif);

for(int i=0;i<splitted.length;i++) {
	Notification notif=new Notification();
	notif.setPostOwner(req.getSession().getAttribute("fbId").toString());
	notif.setPostOwnerEmail(req.getSession().getAttribute("userId").toString());
	notif.setFriendToFbId(splitted[i]);
	notif.setNotifyToFriend("notify");
	notif.setFriendViewed("unseen");
	notif.setPostId(post.getId().toString());
	notifRepo.save(notif);
	}
/*
for(int i=0;i<notif.size();i++)
//t=notif.get(index)
	{
notif.get(i).setPostId(post.getId().toString());
notif.get(i).setNotifyToFriend("notify");
notif.get(i).setFriendViewed("unseen");
notifRepo.save(notif.get(i));
}*/

//mv.addObject("audioLink",audioLink);
//();
return mv;
}
	
	@GetMapping(value="/posts")
	public ModelAndView getUserPosts(HttpServletRequest req) {
		System.out.print(req.getSession().getAttribute("userId").toString());

		List<UserPost> user_posts=postRepo.findAllByEmail(req.getSession().getAttribute("userId").toString());
		//for(i=0;i<user_posts.size())
		System.out.print(user_posts);
		List<Comment> com=commentRepo.findAllByPostOwner(req.getSession().getAttribute("fbId").toString());
		System.out.print(com);

		ModelAndView mv= new ModelAndView();
		mv.addObject("posts",user_posts);
		mv.addObject("comments",com);
		mv.setViewName("viewUserPosts");
		return mv;

	}
	@PostMapping(value="/friendsProfile")
	public ModelAndView viewFriendsProfile(@RequestParam (name="frndId") String frndId,HttpServletRequest req) throws Exception
{
		//UserPost post=new UserPost();
		//List<UserPost> user_posts=postRepo.findAllByEmail(req.getSession().getAttribute("userId").toString());
		Student p=studentRepo.findByFacebookID(frndId);
		System.out.print(frndId);
		System.out.print(p);
		

		
		req.getSession().setAttribute("frndId",frndId);
		ModelAndView mv= new ModelAndView();
		mv.addObject("friends",p);
		mv.setViewName("frndProfile");
		return mv;


}
	@GetMapping(value="/viewFriendPosts")
	public ModelAndView getFriendPosts(HttpServletRequest req) {
		System.out.print(req.getSession().getAttribute("frndId").toString());

		List<UserPost> friend_posts=postRepo.findAllByFacebookID(req.getSession().getAttribute("frndId").toString());
		List<Comment> com=commentRepo.findAllByPostOwner(req.getSession().getAttribute("frndId").toString());

		//for(i=0;i<user_posts.size())
		System.out.print(friend_posts);
		ModelAndView mv= new ModelAndView();
		mv.addObject("posts_friend",friend_posts);
		mv.addObject("comments_on_friendsposts",com);

		mv.setViewName("viewFriendPosts");
		return mv;

	}
	
	@PostMapping(value="/postComment")
	public ModelAndView postComment(@RequestParam (name="postId") String postId,HttpServletRequest req,@RequestParam (name="comment") String commentText) throws Exception
{
		//UserPost post=new UserPost();
		//int result = Integer.parseInt(postId);			

		//List<UserPost> user_posts=postRepo.findAllByEmail(req.getSession().getAttribute("userId").toString());
		Comment com=new Comment();
		com.setPostID(postId);
		com.setFriendComment(commentText);
		com.setFriendID(req.getSession().getAttribute("userId").toString());
		com.setPostOwner(req.getSession().getAttribute("frndId").toString());
		
		
		commentRepo.save(com);
		List<Notification> comnotif=notifRepo.findByPostOwnerAndFriendToFbIdAndPostId(req.getSession().getAttribute("frndId").toString(),req.getSession().getAttribute("fbId").toString() , postId);
		for(int i=0;i<comnotif.size();i++)
				{
			comnotif.get(i).setFriendToFbEmail(req.getSession().getAttribute("userId").toString());
			comnotif.get(i).setCommentNotif("commentnotify");
			notifRepo.save(comnotif.get(i));
			
			}
		
		ModelAndView mv= new ModelAndView();
		//mv.addObject("friends",p);
		mv.setViewName("success");
		return mv;


}
	@PostMapping(value="/replyToComment")
	public ModelAndView replyComment(@RequestParam (name="reply") String reply,@RequestParam (name="cpostId") String cpostId,HttpServletRequest req,@RequestParam (name="cfriendId") String cfriendId,@RequestParam (name="commentText") String commentText) throws Exception
{
		//UserPost post=new UserPost();
		//int result = Integer.parseInt(postId);			

		//List<UserPost> user_posts=postRepo.findAllByEmail(req.getSession().getAttribute("userId").toString());
		Comment creply=commentRepo.findByPostIDAndFriendIDAndFriendComment(cpostId, cfriendId, commentText);
				
		
		System.out.print(creply);
		creply.setPostOwnerEmail(req.getSession().getAttribute("userId").toString());
		creply.setPostOwnerReply(reply);

		ModelAndView mv= new ModelAndView();
		//mv.addObject("friends",p);
		mv.setViewName("success");
		return mv;


}
	
	@GetMapping(value="/admin")
	public ModelAndView renderAdminPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("adminLogin");
		return mv;
	}
	
	@PostMapping(value="/adminRedirect")
	public ModelAndView handleadminRedirect(
			@RequestParam (name="myId") String myId,
			@RequestParam (name="myName") String myName,
			@RequestParam (name="myFriends") String myFriends,
			@RequestParam (name="myEmail") String myEmail,HttpServletRequest req,@RequestParam (name="myFriendsName") String myFriendsName)

{
		req.getSession().setAttribute("admin",myEmail);
		ModelAndView mv= new ModelAndView();
		System.out.println(myEmail);
		if(myEmail.equalsIgnoreCase("santutherockstar@gmail.com")) {
		mv.setViewName("adminProfile");	
		}
		else {
			mv.setViewName("index");
			
		}
		return mv;
}

	@GetMapping(value="/viewAllUsers")
	public ModelAndView getAllStudents(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		if(!req.getSession().getAttribute("admin").toString().isEmpty())
		{	List<Student> allusers=studentRepo.findAll();
		System.out.println(allusers);
		mv.addObject("users",allusers);
		mv.setViewName("viewAllUsers");}
		else {
			mv.setViewName("error");
		}
		return mv;
	}
	@GetMapping(value="/viewAllPosts")
	public ModelAndView getAllPosts(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		if(!req.getSession().getAttribute("admin").toString().isEmpty()) {
		List<UserPost> allposts=postRepo.findAll();
		System.out.println(allposts);

		mv.addObject("allposts",allposts);
		mv.setViewName("viewAllPosts");
		}
		else {
			mv.setViewName("error");
		}
		return mv;
	}
	
	
	@PostMapping(value="/deletePost")
	public ModelAndView deletePost(@RequestParam (name="postId") String dpostId,@RequestParam (name="postOwnerEmail") String postOwnerEmail,HttpServletRequest req) throws Exception
{
		//UserPost post=new UserPost();
		//int result = Integer.parseInt(postId);			

		//List<UserPost> user_posts=postRepo.findAllByEmail(req.getSession().getAttribute("userId").toString());
		UserPost dpost=postRepo.findByEmailAndId(postOwnerEmail, Integer.parseInt(dpostId));
		
		System.out.print(dpost);
		postRepo.delete(dpost);
		
List<Notification> delnotif=notifRepo.findAllByPostId(dpostId);
for(int i=0;i<delnotif.size();i++) {
	notifRepo.delete(delnotif.get(i));
	
}

List<Comment> delcomment=commentRepo.findAllByPostID(dpostId);
if(!delcomment.isEmpty()) {
for(int i=0;i<delcomment.size();i++) {
	commentRepo.delete(delcomment.get(i));
	
}
}
		ModelAndView mv= new ModelAndView();
		//mv.addObject("friends",p);
		mv.setViewName("success");
		return mv;


}
	
	@GetMapping(value="/editProfile")
	public ModelAndView profile() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("editProfile");
		return mv;
	}
	
	@PostMapping(value="/editUserProfile")
	public ModelAndView saveProfile(@RequestParam (name="name",required=true) String name,@RequestParam (name="desc") String mydesc,HttpServletRequest req,@RequestPart(value = "file") MultipartFile file)
{
		ModelAndView mv = new ModelAndView();
		
		Student n=studentRepo.findByEmail(req.getSession().getAttribute("userId").toString());
		
		
if(!name.isEmpty())	
n.setName(name);
if(!mydesc.isEmpty())
n.setDescription(mydesc);
if(!this.amazonClient.uploadFile(file).toString().isEmpty())
n.setImageurl(this.amazonClient.uploadFile(file).toString());
studentRepo.save(n);
//console.log("Picture has been Succefully upload to "+ this.amazonClient.uploadFile(file));
System.out.println("Picture has been Succefully upload to "+ this.amazonClient.uploadFile(file));

req.getSession().setAttribute("key",this.amazonClient.uploadFile(file));
mv.addObject("student",n);
mv.setViewName("myProfile");
//return "Picture has been Succefully upload to "+ this.amazonClient.uploadFile(file);
//return new ModelAndView("myProfile");
return mv;
}	
	
	
	@PostMapping(value="/viewNotif")
	public ModelAndView viewPost(@RequestParam (name="postId") String npostId,@RequestParam (name="postOwnerEmail") String postOwnerEmail,HttpServletRequest req) throws Exception
{
		//UserPost post=new UserPost();
		//int result = Integer.parseInt(postId);			

		//List<UserPost> user_posts=postRepo.findAllByEmail(req.getSession().getAttribute("userId").toString());
		UserPost npost=postRepo.findByEmailAndId(postOwnerEmail, Integer.parseInt(npostId));
		
		List<Comment> com=commentRepo.findAllByPostOwner(postOwnerEmail);
		System.out.print(com);

		
		
		ModelAndView mv= new ModelAndView();
		System.out.println(npost);
		mv.addObject("comments",com);

		mv.addObject("nposts",npost);
		mv.setViewName("viewNotifPost");
		return mv;
		
		
}
	
	@PostMapping(value="/notifPostComment")
	public ModelAndView postComment(@RequestParam (name="postId") String npostId,HttpServletRequest req,@RequestParam (name="postOwnerEmail") String postOwnerEmail,@RequestParam (name="postOwnerFbId") String postOwnerFbId,@RequestParam (name="comment") String commentText) throws Exception
{
		//UserPost post=new UserPost();
		//int result = Integer.parseInt(postId);			

		//List<UserPost> user_posts=postRepo.findAllByEmail(req.getSession().getAttribute("userId").toString());
		Comment com=new Comment();
		com.setPostID(npostId);
		com.setFriendComment(commentText);
		com.setFriendID(req.getSession().getAttribute("userId").toString());
		com.setPostOwnerEmail(postOwnerEmail);
		com.setPostOwner(postOwnerFbId);

		
		
		commentRepo.save(com);
		List<Notification> comnotif=notifRepo.findAllByPostOwnerEmailAndFriendToFbIdAndPostId(postOwnerEmail,req.getSession().getAttribute("fbId").toString() , npostId);
		for(int i=0;i<comnotif.size();i++)
				{
			comnotif.get(i).setFriendToFbEmail(req.getSession().getAttribute("userId").toString());
			comnotif.get(i).setCommentNotif("commentnotify");
			notifRepo.save(comnotif.get(i));
			
			}
		
		ModelAndView mv= new ModelAndView();
		//mv.addObject("friends",p);
		mv.setViewName("success");
		return mv;


}

	
	
}
