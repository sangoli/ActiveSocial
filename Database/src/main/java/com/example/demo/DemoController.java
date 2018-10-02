package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoController {
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private NotificationRepository notifRepo;
	@GetMapping(value="/")
	public ModelAndView renderFirstPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	private AmazonClient amazonClient;
	@Autowired
    DemoController(AmazonClient amazonClient) {
       this.amazonClient = amazonClient;
   }

	@PostMapping(value="/student/add")
		public ModelAndView saveStudent(@RequestParam (name="name",required=true) String name,@RequestParam String email)
{
	Student n=new Student();
	n.setName(name);
	n.setEmail(email);
	studentRepo.save(n);
	return new ModelAndView("index");
}
	@GetMapping(value="/students")
	public ModelAndView getAllStudents() {
		ModelAndView mv = new ModelAndView();
		List<Student> students=studentRepo.findAll();
		mv.addObject("students",students);
		mv.setViewName("allStudents");
		return mv;
	}
	@GetMapping(value="/student")
	public ModelAndView getOneStudent(@RequestParam (name="email",required=true) String email)
 {
		ModelAndView mv = new ModelAndView();
		try {
		//Optional<Student> result=studentRepo.findById(Integer.parseInt(id));
			Student result=studentRepo.findByEmail(email);

		//if(result.isPresent()) {
			//Student s=result.get();
			//mv.addObject("student",s);
			//mv.setViewName("studentInfo");
		//}
		//else {
			//throw new Exception("Error");
		//}
			mv.addObject("student",result);
			mv.setViewName("studentInfo");
		
		}
		catch(Exception e){
			mv.addObject("error","Student not found");
			mv.setViewName("studentError");
			e.printStackTrace();
			
		}
		
		return mv;
	}
	@GetMapping(value="/login")
	public ModelAndView getLogin() {
		ModelAndView mv = new ModelAndView();
		//List<Student> students=studentRepo.findAll();
		//mv.addObject("students",students);
		mv.setViewName("index1");
		return mv;
	}
	@PostMapping(value="/facebookRedirect")
	public ModelAndView handleRedirect(
			@RequestParam (name="myId") String myId,
			@RequestParam (name="myName") String myName,
			@RequestParam (name="myFriends") String myFriends,
			@RequestParam (name="myEmail") String myEmail,HttpServletRequest req,@RequestParam (name="myFriendsName") String myFriendsName)
{
		
System.out.println(myId+myName+myEmail+myFriends);
	String[] splitted=myFriends.split("/");
	req.getSession().setAttribute("myFriendsListFbId", myFriends);

	for(int i=0;i<splitted.length;i++) {
		System.out.println(i+":"+splitted[i]);
	}
	
	String[] friendsname=myFriendsName.split("/");
	req.getSession().setAttribute("myFriendsListFbName", myFriendsName);

	for(int i=0;i<friendsname.length;i++) {
		System.out.println(i+":"+friendsname[i]);
	}
		
	/*if(notifRepo.findByPostOwner(myId)==null) {
			for(int i=0;i<splitted.length;i++) {
		Notification notif=new Notification();
		notif.setPostOwner(myId);
		notif.setFriendToFbId(splitted[i]);
		notifRepo.save(notif);
		}
		}*/
	
	//if(req.getSession().getAttribute("userId").toString()!=null)
	req.getSession().setAttribute("userId", myEmail);
	req.getSession().setAttribute("fbId", myId);
	req.getSession().setAttribute("username", myName);


	if(studentRepo.findByEmail(myEmail)==null) {
		Student n=new Student();
		n.setFacebookID(myId);
		n.setName(myName);
		n.setEmail(myEmail);
		studentRepo.save(n);
		
	}
		
	
	ModelAndView mv = new ModelAndView();
	try {
	//Optional<Student> result=studentRepo.findByEmail(Integer.parseInt(myId));
		Student s =new Student();
		 s=studentRepo.findByEmail(myEmail);
		
	if(s.getDescription()==null) {
		//Student s=result.get();
		
		mv.setViewName("newProfile");	
		
	}
	else {
		//throw new Exception("Error");
		mv.addObject("student",studentRepo.findByEmail(myEmail));
		List<Notification> checknotif=notifRepo.findAllByfriendToFbIdAndNotifyToFriendAndFriendViewed(req.getSession().getAttribute("fbId").toString(), "notify","unseen");
		System.out.println(checknotif);
		List<Notification> commentnotif=notifRepo.findAllByPostOwnerAndCommentNotif(req.getSession().getAttribute("fbId").toString(),"commentnotify");

		mv.addObject("notifchecker", checknotif);
		mv.addObject("commentchecker", commentnotif);

		mv.setViewName("myProfile");	
		
		
		
		
		//return new ModelAndView("index");
							
	}
		//mv.addObject("student",result);
		//mv.setViewName("studentInfo");
	
	}
	
	catch(Exception e){
		mv.addObject("error","Student not found");
		mv.setViewName("studentError");
		e.printStackTrace();
		
	}
	
	return mv;
	
	
	
	//return new ModelAndView("index");
}
	@PostMapping(value="/student/addProfile")
	public ModelAndView saveProfile(@RequestParam (name="name",required=true) String name,@RequestParam (name="desc") String mydesc,HttpServletRequest req,@RequestPart(value = "file") MultipartFile file)
{
		ModelAndView mv = new ModelAndView();
		
		Student n=studentRepo.findByEmail(req.getSession().getAttribute("userId").toString());
n.setName(name);
n.setDescription(mydesc);
n.setImageurl(this.amazonClient.uploadFile(file).toString());
studentRepo.save(n);
//console.log("Picture has been Succefully upload to "+ this.amazonClient.uploadFile(file));
System.out.println("Picture has been Succefully upload to "+ this.amazonClient.uploadFile(file));

req.getSession().setAttribute("key",this.amazonClient.uploadFile(file));
mv.addObject("student",n);

List<Notification> checknotif=notifRepo.findAllByfriendToFbIdAndNotifyToFriendAndFriendViewed(req.getSession().getAttribute("fbId").toString(), "notify","unseen");
System.out.println(checknotif);
List<Notification> commentnotif=notifRepo.findAllByPostOwnerAndCommentNotif(req.getSession().getAttribute("fbId").toString(),"commentnotify");

mv.addObject("notifchecker", checknotif);
mv.addObject("commentchecker", commentnotif);

mv.setViewName("myProfile");
//return "Picture has been Succefully upload to "+ this.amazonClient.uploadFile(file);
//return new ModelAndView("myProfile");
return mv;
}	

	@GetMapping(value="/viewFriends")
	public ModelAndView viewFriends() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("viewFriends");
		return mv;
	}

	@GetMapping(value="/myProfile")
	public ModelAndView profile(HttpServletRequest req) {
		Student n=studentRepo.findByEmail(req.getSession().getAttribute("userId").toString());

		ModelAndView mv = new ModelAndView();
		
		List<Notification> checknotif=notifRepo.findAllByfriendToFbIdAndNotifyToFriendAndFriendViewed(req.getSession().getAttribute("fbId").toString(), "notify","unseen");
		System.out.println(checknotif);
		List<Notification> commentnotif=notifRepo.findAllByPostOwnerAndCommentNotif(req.getSession().getAttribute("fbId").toString(),"commentnotify");

		mv.addObject("notifchecker", checknotif);
		mv.addObject("commentchecker", commentnotif);
		
		mv.addObject("student",n);
		mv.setViewName("myProfile");
		return mv;
	}
	
	
	
}
