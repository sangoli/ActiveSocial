<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
<style type="text/css">
	.login-form {
		width: 500px;
		margin: 200px auto;
	}
    .login-form form {        
    	margin-bottom: 15px;
        background: #f7f7f7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
    .login-form h2 {
        margin: 0 0 15px;
    }
    .form-control, .login-btn {
        min-height: 38px;
        border-radius: 2px;
    }
    .input-group-addon .fa {
        font-size: 18px;
    }
    .login-btn {
        font-size: 15px;
        font-weight: bold;
    }
	.social-btn .btn {
		border: none;
        margin: 10px 3px 0;
        opacity: 1;
	}
    .social-btn .btn:hover {
        opacity: 0.9;
    }
	.social-btn .btn-primary {
        background: #507cc0;
    }
	.social-btn .btn-info {
		background: #64ccf1;
	}
	.social-btn .btn-danger {
		background: #df4930;
	}
    .or-seperator {
        margin-top: 20px;
        text-align: center;
        border-top: 1px solid #ccc;
    }
    .or-seperator i {
        padding: 0 10px;
        background: #f7f7f7;
        position: relative;
        top: -11px;
        z-index: 1;
    }   
</style>
</head>
<body>
<script src="/resources/js/fb.js">
</script>

<div class="login-form" >
    <form >
        <h2 class="text-center">Sign in</h2>   
        <div class="form-group" height=300px>
        	<div class="input-group">
            </div>
        </div>
		<div class="form-group">
            <div class="input-group">
            </div>
        </div>        
        <div class="form-group">
        </div>
        <div class="clearfix">
        </div>
        <p class="text-center">Login with your social media account</p>
        <div class="text-center social-btn">
<fb:login-button scope="public_profile,email,user_friends" data-max-rows="1" data-size="large" data-button-type="login_with" data-show-faces="false" data-auto-logout-link="true" data-use-continue-as="True" onlogin="checkLoginState();">
</fb:login-button>            
        </div>
    </form>
</div>

<div id="status">
</div>
<div id="container">
	<table style="width:100%">
  
  <tbody id="table"></tbody>
</table>
	
</div>
<form id="redirectform" method="post" action="facebookRedirect">
  <input type="hidden" name="myId" />
  <input type="hidden" name="myName" />
  <input type="hidden" name="myFriends" />
  <input type="hidden" name="myEmail" />
</form>
</body>
</html>                            