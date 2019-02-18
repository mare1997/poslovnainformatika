

function login(){
	var username=$('#usernameLog').val().trim();
	var pass =$('#passLog').val().trim();
	if (username=="" || pass==""){
		alert("Nije uneto korisnicko ime ili lozinka!!!")
		return;
	}
	var data = {
			'username':username,
			'password':pass
	}
	console.log(data);
	console.log("login");


	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: 'https://localhost:8081/api/auth/login',
		data: JSON.stringify(data),
		dataType: 'json',
		crossDomain:true,
		cache:false,
		processData:false,
		success: function(response){
			var token = response.access_token;
			console.log(token);
			console.log(response);

			localStorage.setItem("token",token);
			localStorage.setItem("id",response.id);
			console.log(username);
			localStorage.setItem("username",username);
			console.log("login i ajax success");

			window.location.replace("izborPPG.html");
		},
		error: function(jqXHR, textstatus, errorThrown){

			if(jqXHR.status == "401"){
				alert("Pogresno uneto korisnicko ime ili lozinka!!!")
			}
		}

	}); 
}
