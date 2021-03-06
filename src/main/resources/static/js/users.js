var token= localStorage.getItem("token");
var auth = "";
var currentUserId = "";
var delId;
$(document).ready(function() {
	loadUsers();
	$(document).on("click", "#usersBody tr", function(e) {
		//var name = this.attr("name");
		delId = this.id;
		var delUserRow = $("#usersBody tr");
		console.log(delId);
		localStorage.setItem("deleteId", delId);
		var asd = $('#'+delId+'');
		console.log(asd);
		asd.addClass("bg-danger");
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
		    	  if(user.id != delId){
		    		  $('#'+user.id+'').removeClass("bg-danger"); 
		    	  }
		    	 
		      }
		    },error: function (jqXHR, textStatus, errorThrown) {
					alert("read error!!!");
		  }
		});
		
    //alert(name);
});

	
});
function loginStatus(){
	currentUserId = localStorage.getItem("id");
	console.log("currentUserId: "+currentUserId);




}

function editModal(){
	
	$('#editKorisnika').modal();
	 $('#editName').val("");
     $('#editLastName').val("");
     $('#editUsername').val("");
     $('#editPass').val("");
     $('#editPreduzece').val("");
	var userC = localStorage.getItem("currentUserId");
	$.ajax({
	    url:'https://localhost:8081/api/users/' + userC,
	    headers:{Authorization:"Bearer " + token},
	    type:"GET",
	    dataType: 'json',
	    crossDomain:true,
	    success: function (response) {
	        user = response;
	        console.log(user)
	        $('#editName').val(user.firstname);
	        $('#editLastname').val(user.lastname);
	        $('#editUsername').val(user.username);
	        $('#editPass').val(user.password);
	        $('#editPreduzece').val(user.preduzece.name);
	        




	    },error: function (jqXHR, textStatus, errorThrown) {
				alert("read error!!!");
	  }
	});
	
}
function editMe(){
	
	
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
var ime1 = $('#editName').val().trim();
var prezime1 = $('#editLastname').val().trim();
var korIme1 = $('#editUsername').val().trim();
var lozinka1 = $('#editPass').val().trim();
var auth1 = localStorage.getItem("authority");
var preduzeceId = localStorage.getItem("pId")
if(ime1=="" || prezime1 == "" ||korIme1=="" || lozinka1==""){
	alert("Sva polja moraju biti popunjena");
	return;

}


var userC = localStorage.getItem("currentUserId");

var data={
		  	'autority':auth1,
			'firstname':ime1,
			'lastname':prezime1,
			'username':korIme1,
			'password':lozinka1,
			'preduzece' : preduzeceObject

	}
console.log(data);
$.ajax({
    url:'https://localhost:8081/api/users/update/' + userC,
    headers:{Authorization:"Bearer " + token},
    type:"PUT",
    data: JSON.stringify(data),
    dataType: 'json',
    contentType: 'application/json',
    async: false,
    crossDomain:true,
    success: function (response) {
        console.log("edit ok");
        $('#editKorisnika').modal('toggle');
        refresh();
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});




	
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
        	refresh();
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
        	refresh();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}

function refresh(){
	var table = $('#usersBody tr');
    console.log(table);
    table.remove();
	loadUsers();
}
