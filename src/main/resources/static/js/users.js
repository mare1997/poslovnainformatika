var token= localStorage.getItem("token");
var auth = "";
var currentUserId = "";
$(document).ready(function() {
	loadUsers();
});
function loginStatus(){
	currentUserId = localStorage.getItem("id");
	console.log("currentUserId: "+currentUserId);




}

function loadUsers(){
  $.ajax({
    url:'https://localhost:8081/api/users',
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var table = $('#usersBody');
      for (var i=0; i<response.length; i++){
        user = response[i];

          table.append(
            '<tr>'+'<td>'+user.firstname+'</td><td>'+user.lastname+'</td><td>'+user.username+'</td></tr>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}

function registerModal(){
	$('#addKorisnika').modal();

}




function register(){
	var ime = $('#addName').val().trim();
	var prezime = $('#addLastname').val().trim();
	var korIme = $('#addUsername').val().trim();
	var lozinka = $('#addPass').val().trim();
	var auth = localStorage.getItem("authority");
	var preduzeceId = localStorage.getItem("pId")
	if(ime=="" || prezime == "" ||korIme=="" || lozinka==""){
		alert("Sva polja moraju biti popunjena");
		return;

	}
	var preduzeceId=localStorage.getItem("pId");
	var preduzeceObject;
$.ajax({
	url:'https://localhost:8081/api/preduzece/'+preduzeceId,
	headers:{Authorization:"Bearer " + token},
	type: 'GET',
	dataType:'json',
	async: false,
	crossDomain: true,
	success:function(response){
		console.log(response);
		preduzeceObject = response;
	},
	error: function (jqXHR, textStatus, errorThrown) {
		if(jqXHR.status=="403"){
			alert("Error.");
		}

	}

});

	console.log(ime+" "+prezime+" "+korIme+" "+lozinka+" " + preduzece + " " + auth);
	var data={
		  'autority':auth,
			'firstname':ime,
			'lastname':prezime,
			'username':korIme,
			'password':lozinka,
			'preduzece' : preduzeceObject

	}
	console.log(data);

	$.ajax({
		type: 'POST',
        contentType: 'application/json',
        url:'https://localhost:8081/api/users/add',
        headers:{Authorization:"Bearer " + token},
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (response) {
        	console.log("usao u success")
        	alert("Registracija uspesna.");
        	$('#addKorisnika').modal('toggle');
        },
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Registracija neuspesna.");
			}

		}
    });
}

function userForEdit(){
	$.ajax({
		type: 'GET',
		url:'https://localhost:8081/api/users',
	    headers:{Authorization:"Bearer " + token},
		cache: false,
        success: function (response) {
        	$('#editKorisnika').modal();


        	$('#editName').val(response.firstname);
          $('#editLastname').val(response.lastname);
          $('#editUserame').val(response.username);
          $('#editPass').val(response.password);

        },
		error: function (jqXHR, textStatus, errorThrown) {
			alert(textStatus);
		}
    });
}


function saveUserEdit(){
  var ime = $('#editName').val().trim();
	var prezime = $('#editLastname').val().trim();
	var korIme = $('#editUsername').val().trim();
	var lozinka = $('#editPass').val().trim();

	if(ime=="" || prezime=="" || korIme=="" || lozinka==""){
		alert("Sva polja morju biti popunjena!!");
		return;
	}

	console.log(ime+" "+prezime+" "+korIme+" "+lozinka+" ");
	var data={
        'firstname':ime,
        'lastname':prezime,
        'username':korIme,
        'password':lozinka,
	}

	$.ajax({
		type: 'PUT',
        contentType: 'application/json',
        url: 'http://localhost:8081/api/users/'+currentUserId,
        data: JSON.stringify(data),
        dataType: 'json',
		cache: false,
		processData: false,
        success: function (response) {

        	$('#editKorisnika').modal('toggle');
        },
		error: function (jqXHR, textStatus, errorThrown) {
			alert(jqXHR.status);
		}
    });
}
