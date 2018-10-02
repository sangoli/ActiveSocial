
<!DOCTYPE html>
<html >
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="User information form snippet with timezones" />
  <meta name="google" value="notranslate">

  
  <!--stylesheets / link tags loaded here-->



  <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
  <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" />
  

  

  <style type="text/css"></style>

</head>
<body >
  
  <form action="student/addProfile" method="post" enctype="multipart/form-data">

<div class="container">
    <h1>Create Profile</h1>
    <hr>
    
    <div class="row">
        <!-- left column -->
        <div class="col-md-3">
            <div class="text-center">
                <img src="//placehold.it/100" class="avatar img-circle" alt="avatar">
                <h6>Upload a  photo...</h6>

                <input type="file" class="form-control" name="file" accept="image/*">
            </div>
        </div>

        <!-- edit form column -->
        <div class="col-md-9 personal-info">

            <h3>Personal info</h3>

            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-lg-3 control-label">Name:</label>
                    <div class="col-lg-8">
                        <input class="form-control" type="text" name="name" id="name">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-lg-3 control-label">Short Description:</label>
                    <div class="col-lg-8">
                        <input class="form-control" type="text" name="desc" id="desc"> 
                    </div>
                </div>


            </form>
            <div class="form-group">
                <label class="col-md-3 control-label"></label>
                <div class="col-md-8">
                    <input type="submit" class="btn btn-primary">
                    <span></span>

                </div>
            </div>

        </div>
    </div>

    <hr>
</div>
  
</body>
</html>
