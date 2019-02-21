
$(document).ready(function() {
	var token = localStorage.getItem("token");
	var role = localStorage.getItem("authority");
	console.log(token);
	if(token == null){
		window.stop();
		document.body.innerHTML = '';
		window.location.replace("login.html");
		//alert("You are not logged in, please log in!");
		
	}
	if(role == "ADMIN"){
		//alert("im admin");
	}
	if(role=="REGULAR"){
		//alert("im user");
	}
	
});
function logout(){
	localStorage.removeItem("token");
	localStorage.removeItem("pId");
	localStorage.removeItem("pgId");
	localStorage.removeItem("currentUserId");
	localStorage.removeItem("authority");
	window.location.replace("login.html");
	
}


