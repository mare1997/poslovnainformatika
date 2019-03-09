$(document).ready(function() {
	var token = localStorage.getItem("token");
	var role = localStorage.getItem("authority");
	console.log(role);


	if(role=="REGULAR"){
		window.stop();
		document.body.innerHTML = '';
		window.location.replace("mainPage.html");
	}
	
});