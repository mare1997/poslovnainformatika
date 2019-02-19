var token= localStorage.getItem("token");
var auth = "";
var currentUserId = "";
$(document).ready(function() {
	loadUsers();
	$(document).on("click", "#usersBody tr", function(e) {
		//var name = this.attr("name");
		var delId = this.id;
		var delUserRow = $("#usersBody tr");
		console.log(delId);
		localStorage.setItem("deleteId", delId);
    //alert(name);
});
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
            '<tr id="'+user.id+'">'+'<td>'+user.firstname+'</td><td>'+user.lastname+'</td><td>'+user.username+'</td></tr>'
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
	var auth = "REGULAR";
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


function openDeleteModal(){
	$('#userDeleteModal').modal();
}

function deleteUser(){
	var brisanje = localStorage.getItem("deleteId");
	var token = localStorage.getItem("token");
	$.ajax({
        url: 'https://localhost:8081/api/users/delete/'+brisanje,
        headers:{Authorization:"Bearer " + token},
        contentType: "application/json",
		type: 'DELETE',
        success: function (response) {
        	console.log("user delete success: ");
        	
        	$('#deleteUserModal').modal('toggle');
        	location.reload();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}
