<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Facebook Login JavaScript Example</title>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<script>
  // This is called with the results from from FB.getLoginStatus().
  function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    // The response object is returned with a status field that lets the
    // app know the current login status of the person.
    // Full docs on the response object can be found in the documentation
    // for FB.getLoginStatus().
    if (response.status === 'connected') {
      // Logged into your app and Facebook.
      testAPI();
    } else {
      // The person is not logged into your app or we are unable to tell.
      document.getElementById('status').innerHTML = 'Please log ' +
        'into this app.';
    }
  }

  // This function is called when someone finishes with the Login
  // Button.  See the onlogin handler attached to it in the sample
  // code below.
  function checkLoginState() {
    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });
  }

  window.fbAsyncInit = function() {
    FB.init({
      appId      : '167229083897157',
      cookie     : true,  // enable cookies to allow the server to access 
                          // the session
      xfbml      : true,  // parse social plugins on this page
      version    : 'v2.8' // use graph api version 2.8
    });

    // Now that we've initialized the JavaScript SDK, we call 
    // FB.getLoginStatus().  This function gets the state of the
    // person visiting this page and can return one of three states to
    // the callback you provide.  They can be:
    //
    // 1. Logged into your app ('connected')
    // 2. Logged into Facebook, but not your app ('not_authorized')
    // 3. Not logged into Facebook and can't tell if they are logged into
    //    your app or not.
    //
    // These three cases are handled in the callback function.

    FB.getLoginStatus(function(response) {
      statusChangeCallback(response);
    });

  };

  // Load the SDK asynchronously
(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'facebook-jssdk'));

  // Here we run a very simple test of the Graph API after login is
  // successful.  See statusChangeCallback() for when this call is made.
  function testAPI() {
    console.log('Welcome!  Fetching your information.... ');
    FB.api('/me?fields=id,name,email', function(response) {
      console.log(response);
      document.getElementById('status').innerHTML =
        'Thanks for logging in, ' + response.name + '!';
      $('[name="myName"]').val(response.name);
      $('[name="myId"]').val(response.id);
      $('[name="myEmail"]').val(response.email);
      
      FB.api('/me/friends',function(response){
      	console.log(response);
      	//Append the data into the table
      	response.data.forEach(function(ele,i){
      		console.log(ele.name);
      		$("#tableBody").append(
      				'<tr><th scope="row">'+i+'</th>'+
      				'<td>'+ele.name+'</td'+
      				'<td>'+ele.id+'</td>'+'</tr>'
      				);
      		var earlierVal=$('[name="myFriends"]').val();
      		//$('[name="myFriends"]').val(earlierVal+ele.id+"/"+ele.name+"/");	
        	$('[name="myFriends"]').val(earlierVal + ele.id +"/");
        	
        	var earlierValFname=$('[name="myFriendsName"]').val();
      		//$('[name="myFriends"]').val(earlierVal+ele.id+"/"+ele.name+"/");	
        	$('[name="myFriendsName"]').val(earlierValFname + ele.name +"/");

      	});
      	$("#redirectform").submit();
      });
      
    });
   
  }
  
   /* function friends(async){
    FB.api('/me/friends', function(response) {
        //console.log('Successful login for: ' + response.name);
        //document.getElementById('status').innerHTML =
          //'Thanks for logging in, ' + response.name + '!';
        console.log(response);
        
        response.data.forEach(function(ele,i){
        	$("#table").append("<tr><td>"+i+"</td><td>"+response.data[i].name+"</td><td>"+response.data[i].id+"</td></tr>");
        	var earlierVal= $('[name="myFriends"]').val();
        	$('[name="myFriends"]').val(earlierVal + ele.id +"/"+ele.name+"/");
            //submit(async);
			

        });
        $('#redirectform').submit();
       
      });
    }
    function submit(async){
    	 if(async!=null)
        	   $('#redirectform').submit();

    }*/
  
</script>

<!--
  Below we include the Login Button social plugin. This button uses
  the JavaScript SDK to present a graphical Login button that triggers
  the FB.login() function when clicked.
-->

<fb:login-button scope="public_profile,email,user_friends" data-max-rows="1" data-size="large" data-button-type="login_with" data-show-faces="false" data-auto-logout-link="true" data-use-continue-as="True" onlogin="checkLoginState();">
</fb:login-button>

<div id="status">
</div>
<div id="container">
	<table style="width:100%">
  <tr>
    <th>#</th>
    <th>Name</th> 
    <th>ID</th>
  </tr>
  <tbody id="table"></tbody>
</table>
	

</div>
<form id="redirectform" method="post" action="facebookRedirect">
  <input type="hidden" name="myId" />
  <input type="hidden" name="myName" />
  <input type="hidden" name="myFriends" />
    <input type="hidden" name="myFriendsName" />
  
  <input type="hidden" name="myEmail" />
  
</form>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>