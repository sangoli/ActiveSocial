<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>Audio-only Example - Record Plugin for Video.js</title>

  <link href="//vjs.zencdn.net/6.8.0/video-js.min.css" rel="stylesheet">

  <script>
  // disable analytics tracking (note: used in vjs.zencdn.net CDN-hosted version
  // of video.js only, see https://github.com/videojs/video.js#quick-start)
  window.HELP_IMPROVE_VIDEOJS = false;
  </script>
<link href="https://vjs.zencdn.net/6.6.3/video-js.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/videojs-record/2.1.0/css/videojs.record.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/video.js/6.7.2/video.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/RecordRTC/5.4.6/RecordRTC.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/adapterjs/0.15.0/adapter.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/wavesurfer.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/wavesurfer.microphone.min.js"></script>
<script src="https://collab-project.github.io/videojs-record/dist/videojs.wavesurfer.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/videojs-record/2.1.2/videojs.record.min.js"></script>
  

  <style>
  /* change player background color */
  #myAudio {
      background-color: #9FD6BA;
  }
  </style>
</head>
<body>

<audio id="myAudio" class="video-js vjs-default-skin"></audio>

<script>
var player = videojs("myAudio", {
    controls: true,
    width: 600,
    height: 300,
    plugins: {
        wavesurfer: {
            src: "live",
            waveColor: "#36393b",
            progressColor: "black",
            debug: true,
            cursorWidth: 1,
            msDisplayMax: 20,
            hideScrollbar: true
        },
        record: {
            audio: true,
            video: false,
            maxLength: 20,
            debug: true
        }
    }
}, function(){
    // print version information at startup
    videojs.log('Using video.js', videojs.VERSION,
        'with videojs-record', videojs.getPluginVersion('record'),
        '+ videojs-wavesurfer', videojs.getPluginVersion('wavesurfer'),
        'and recordrtc', RecordRTC.version);
});

// error handling
player.on('deviceError', function() {
    console.log('device error:', player.deviceErrorCode);
});

// user clicked the record button and started recording
player.on('startRecord', function() {
    console.log('started recording!');
});

// user completed recording and stream is available
player.on('finishRecord', function() {
    // the blob object contains the recorded data that
    // can be downloaded by the user, stored on server etc.
    console.log('finished recording: ', player.recordedData);
	 var reader=new FileReader();
    var base64Data;
    //reader.readAsDataurl(player.recordedData);
    if (reader.readAsDataURL) {reader.readAsDataURL(player.recordedData);}
	else if (reader.readAsDataurl) {reader.readAsDataurl(player.recordedData);}
    reader.onloadend= function(){
    	base64Data=reader.result;
    	console.log(base64Data);
    	$('#recording1').val(base64Data);
    }
});
$(document).ready(function(){
	$('#saveButton').on("click",function(){
		$('#audioForm').submit();
	});
});
</script>
<form id="audioForm" method="post" action="base64Audio" enctype="multipart/form-data">
   Image:<input type="file" class="form-control" name="file" accept="image/*">

  <input type="hidden" name="recording1" id="recording1"/> 
  Text:<input class="form-control" type="text" name="desc" id="desc"> 
  
</form>
<button id="saveButton" name="save">Save</button>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

</body>
</html>
    