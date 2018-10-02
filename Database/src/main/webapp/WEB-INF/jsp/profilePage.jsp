<html xmlns:th="http://www.thymeleaf.org">
<head>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/profile.css">

</head>

<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/"><img src="http://sgolifiles.images.s3.amazonaws.com/1520206707523-SocialMedia-300x212.png" width="25px" align="center">ActiveSocial.com</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="/">Home</a></li>
      <li><a href="/profilePage">Profile</a></li>
      <li><a href="#">Page 2</a></li>
      <li><a href="#">Page 3</a></li>
    </ul>
  </div>
</nav>
<div id="user-profile-2" class="user-profile">
		<div class="tabbable">

			<div class="tab-content no-border padding-24">
				<div id="home" class="tab-pane in active">
					<div class="row">
						<div class="col-xs-12 col-sm-3 center">
							<span class="profile-picture">
								<img class="editable img-responsive" alt="GO back and Upload" id="avatar2" src="<%=request.getSession().getAttribute("key") %>">
							</span>

							
						</div><!-- /.col -->

						<div class="col-xs-12 col-sm-9">
							<h4 class="blue">
								<span class="middle">Welcome!</span>

								<span class="label label-purple arrowed-in-right">
									<i class="ace-icon fa fa-circle smaller-80 align-middle"></i>
									online
								</span>
							</h4>

							<div class="profile-user-info">
								<div class="profile-info-row">
									<div class="profile-info-name"> Username </div>

									<div class="profile-info-value">
										<span>Santosh Kumar Goli</span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name"> Location </div>

									<div class="profile-info-value">
										<i class="fa fa-map-marker light-orange bigger-110"></i>
										<span>Albany</span>
										<span>NewYork</span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name"> Age </div>

									<div class="profile-info-value">
										<span>22</span>
									</div>
								</div>


								<div class="profile-info-row">
									<div class="profile-info-name"> Interests </div>
            <div class="profile-info-value">
										<span>Machine Learning,Data Mining,Software Engineering</span>
									</div>
								</div>
              </div>
							

							<div class="hr hr-8 dotted"></div>

							<div class="profile-user-info">
								<div class="profile-info-row">
									<div class="profile-info-name"> Website </div>

									<div class="profile-info-value">
										<a href="#" target="_blank">----</a>
									</div>
								</div>

								

								
							</div>
						</div><!-- /.col -->
					</div><!-- /.row -->

					<div class="space-20"></div>

					<div class="row">
						<div class="col-xs-12 col-sm-6">
							<div class="widget-box transparent">
								<div class="widget-header widget-header-small">
									<h4 class="widget-title smaller">
										<i class="ace-icon fa fa-check-square-o bigger-110"></i>
										About Me
									</h4>
								</div>

								<div class="widget-body">
									<div class="widget-main">
										<p>Hey! I am a graduate student currently pursuing my Masters' in Computer Science.</p>
										<p>
											Thanks for visiting my profile.
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div><!-- /#home --><!-- /#feed --><!-- /#friends --><!-- /#pictures -->
			</div>
		</div>
	</div>

</body>
</html>