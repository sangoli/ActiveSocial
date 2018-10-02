<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="b" uri="http://java.sun.com/jsp/jstl/core"%>
                <%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<table class="table table-dark">
  <thead>
    <tr>
      <th scope="col">text</th>
      <th scope="col">image</th>
      <th scope="col">audioUrl</th>
       <th scope="col">Comment</th>
       
      
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><c:out value="${nposts.text}"/></td>
      <td><img src="<c:out value="${nposts.imageurl}"/>"/></td>
      
      <td><audio id="<c:out value="${nposts.id}"/>" onmouseover="f(id)" onmouseout="s(id)" src="<c:out value="${nposts.audiourl}"/>" controls type="audio/webm"></audio></td>
      
      <td>
      <form method=post action="/notifPostComment">
      <input type="hidden" name="postId" id="postId" value="${nposts.id}"/>
            <input type="hidden" name="postOwnerEmail" id="postOwnerEmail" value="${nposts.email}"/>
                        <input type="hidden" name="postOwnerFbId" id="postOwnerFbId" value="${nposts.facebookID}"/>
            
      
      <input type="text" name="comment" id="comment"/>
      <input type="submit" name="submit" value="submit" />
      </form>
      </td>
      
      
    
     <!--<b:forEach items="${comments_on_friendsposts}" var="comment">
      <b:if test="${comment.postID==(post.id)}">
      
      <td><b:out value="${comment.friendID}"/> has commented:<b:out value="${comment.friendComment}"/><br>
      		     
      		      <a:if test="${not empty comment.postOwnerReply}">
      		
      		<b:out value="${comment.postOwnerEmail}"/> has replied:<b:out value="${comment.postOwnerReply}"/><br>
      		</a:if>
           
      
      </td>
      
      	
    
 	 </b:if>
      
       </b:forEach>-->
      
    </tr>
  </tbody>
</table>

<script>
function f(id){
	console.log(id);
	document.getElementById(id).play();
	
}
function s(id){
	console.log(id);
	document.getElementById(id).pause();
	
}
</script>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>