var currentUserId = "";
$(document).ready(function() {
	loginStatus();
	loadUsers();
});
function loginStatus(){
	currentUserId = localStorage.getItem("id");
	console.log("currentUserId: "+currentUserId);
	token = localStorage.getItem("token");
	



}

function loadUsers(){
  $.ajax({
    url:'https://localhost:8081/api/users/active',
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var table = $('#usersBody');
      for (var i=0; i<response.length; i++){
        user = response(i);
        while(user){
          table.append(
            '<tr>'+'<td>'+user.firstname+'</td><td>'+user.lastname+'</td><td>'+user.username+'</td></tr>'
          )
        }
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
	if(ime=="" || prezime == "" ||korIme=="" || lozinka==""){
		alert("Sva polja moraju biti popunjena");
		return;

	}

	console.log(ime+" "+prezime+" "+korIme+" "+lozinka);
	var data={
			'firstname':ime,
			'lastname':prezime,
			'username':korIme,
			'password':lozinka,

	}

	$.ajax({
		type: 'POST',
        contentType: 'application/json',
        url: 'http://localhost:8081/api/users/',
        data: JSON.stringify(data),
        dataType: 'json',
		cache: false,
		processData: false,
        success: function (response) {
        	alert("Registracija uspesna.")
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
        url: 'http://localhost:8081/api/users/'+currentUserId,
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
