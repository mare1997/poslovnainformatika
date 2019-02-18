$(document).ready(function() {
	loadGrupu();
	$(document).on("click", "#grupaBody tr", function(e) {
		//var name = this.attr("name");
		var delId = this.id;
		var delUserRow = $("#grupaBody tr");
		console.log(delId);
		localStorage.setItem("deleteGrupa", delId);
    //alert(name);
});
});
preduzeceId=""
var token= localStorage.getItem("token");
function loadGrupu(){
  $.ajax({
    url:'https://localhost:8081/api/gruparobe/getGRdeliteNo/all',
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var table = $('#grupaBody');
      for (var i=0; i<response.length; i++){
        grupa = response[i];

          table.append(
            '<tr id="'+grupa.id+'">'+'<td>'+grupa.name+'</td>'+'<td>'+grupa.pdv.name+'</td></tr>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}

function grupaModal(){
	$('#addGrupaRobeee').modal();
  $.ajax({
    url:'https://localhost:8081/api/pdv/getPDVdeleteNo/all',
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var table = $('#grupaRobePdv');
      for (var i=0; i<response.length; i++){
        pdv = response[i];

          table.append(
            '<option value="'+pdv.id+'">'+pdv.name+'</option>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
      alert("read error!!!");
  }
});
}

function addGrupaRobe(){
	var ime = $('#addNazivGrupeRobe').val().trim();
  var pdvSel = document.getElementById('grupaRobePdv');
	var preduzeceId = localStorage.getItem("pId");
  var pdvId= pdvSel.options[pdvSel.selectedIndex].value;
	if(ime==""){
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
var pdvObject;
$.ajax({
	url:'https://localhost:8081/api/pdv/getPDVdeleteNo/'+pdvId,
	headers:{Authorization:"Bearer " + token},
	type: 'GET',
	dataType:'json',
	async: false,
	crossDomain: true,
	success:function(response){
		console.log(response);
		pdvObject = response;
	},
	error: function (jqXHR, textStatus, errorThrown) {
		if(jqXHR.status=="403"){
			alert("Error.");
		}

	}

});

	console.log(ime+" "+pdvObject+" "+preduzeceObject);
	var data={
		  'name':ime,
			'pdv':pdvObject,
			'preduzece':preduzeceObject

	}
	console.log(data);

	$.ajax({
		type: 'POST',
        contentType: 'application/json',
        url:'https://localhost:8081/api/gruparobe/add',
        headers:{Authorization:"Bearer " + token},
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (response) {
        	console.log("usao u success")
        	alert("Dodavanje uspesno.");
        	$('#addKorisnika').modal('toggle');
        },
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Dodavanje neuspesno.");
			}

		}
    });
}

function grupaDeleteModal(){
	$('#grupaDeleteModal').modal();
}


function deleteGrupa(){
	var brisanje = localStorage.getItem("deleteGrupa");
	var token = localStorage.getItem("token");
	$.ajax({
        url: 'https://localhost:8081/api/gruparobe/delete/'+brisanje,
        headers:{Authorization:"Bearer " + token},
        contentType: "application/json",
		type: 'DELETE',
        success: function (response) {
        	console.log("grupa delete success: ");
        	
        	$('#grupaDeleteModal').modal('toggle');
        	location.reload();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}

