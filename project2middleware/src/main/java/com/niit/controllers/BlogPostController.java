package com.niit.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.BlogPostDao;
import com.niit.dao.UserDao;
import com.niit.model.BlogPost;
import com.niit.model.ErrorClazz;
import com.niit.model.User;

@Controller
public class BlogPostController {
	@Autowired
private BlogPostDao blogPostDao;
	@Autowired
private UserDao userDao;
@RequestMapping(value="/addblogpost",method=RequestMethod.POST)	
public ResponseEntity<?> addBlogPost(@RequestBody BlogPost blogPost,HttpSession session){
	//CHECK FOR AUTHENTICATION
	String email=(String)session.getAttribute("email");
	if(email==null){
		ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
	}
	
	blogPost.setPostedOn(new Date());
	User postedBy=userDao.getUser(email);
	blogPost.setPostedBy(postedBy);//author of the blogpost
	try{
	blogPostDao.addBlogPost(blogPost);
	return new ResponseEntity<Void>(HttpStatus.OK);
	}catch(Exception e){
		ErrorClazz errorClazz=new ErrorClazz(6,"Unable to post blogpost details.."+ e.getMessage());
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@RequestMapping(value="/blogswaitingforapproval",method=RequestMethod.GET)
public ResponseEntity<?> blogsWaitingForApproval(HttpSession session){
	String email=(String)session.getAttribute("email");
	
	//CHECK FOR AUTHENTICATION
	if(email==null){
		ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//login.html
	}
	
	//CHECK FOR AUTHORIZATION - Only admin can view list of blogs waiting for approval
	User user=userDao.getUser(email);
	if(!user.getRole().equals("ADMIN")){//Logged in user is not an admin
		ErrorClazz errorClazz=new ErrorClazz(6,"Access denied...");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		//blogsWaitingForApproval.html -> Access Denied
	}
	
	List<BlogPost> blogs=blogPostDao.blogsWaitingForApproval();
	return new ResponseEntity<List<BlogPost>>(blogs,HttpStatus.OK);
}

@RequestMapping(value="/blogsapproved",method=RequestMethod.GET)
public ResponseEntity<?> blogsApproved(HttpSession session){
	String email=(String)session.getAttribute("email");
	
	//CHECK FOR AUTHENTICATION
	if(email==null){
		ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//login.html
	}
	
	List<BlogPost> blogs=blogPostDao.blogsApproved();
	return new ResponseEntity<List<BlogPost>>(blogs,HttpStatus.OK);
}
@RequestMapping(value="/getBlog/{blogPostId}",method=RequestMethod.GET)
public ResponseEntity<?> getBlog(@PathVariable int blogPostId,HttpSession session){
     String email=(String)session.getAttribute("email");
	
	//CHECK FOR AUTHENTICATION
	if(email==null){
		ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login");
		return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);//login.html
	}
	BlogPost blogPost=blogPostDao.getBlog(blogPostId);
	//CHECK FOR AUTHORIZATION - Only admin can view list of blogs waiting for approval
	if(!blogPost.isApproved()){
		User user=userDao.getUser(email);
		if(!user.getRole().equals("ADMIN")){//Logged in user is not an admin
			ErrorClazz errorClazz=new ErrorClazz(6,"Access denied...");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
			//blogsWaitingForApproval.html -> Access Denied
		}
		
	}
		return new ResponseEntity<BlogPost>(blogPost,HttpStatus.OK);
	
}

}





