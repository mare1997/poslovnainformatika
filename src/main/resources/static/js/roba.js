$(document).ready(function() {
	loadRoba();
	$(document).on("click", "#robaBody tr", function(e) {
		//var name = this.attr("name");
		var delId = this.id;
		var delUserRow = $("#robaBody tr");
		console.log(delId);
		localStorage.setItem("deleteRoba", delId);
    //alert(name);
});
});
var token= localStorage.getItem("token");
var idGrupe = localStorage.getItem("grupaId");
var pId="";
function loadRoba(){
  $.ajax({
    url:'https://localhost:8081/api/roba/getAllRobadeliteNo/'+idGrupe,
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var table = $('#robaBody');
      for (var i=0; i<response.length; i++){
        roba = response[i];
			console.log(roba);
          table.append(
            '<tr id="'+roba.id+'" value="'+roba.id+'">'+'<td>'+roba.name+'</td><td>'+roba.grupa.name+'</td><td>'+roba.jedninica_mere+'</td><td>'+roba.cena+'</td></tr>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}

function fillGrupaRobe(){
  $('#addRoba').modal();
	$.ajax({
    url:'https://localhost:8081/api/gruparobe/getGRdeliteNo/all',
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var select = $('#pickGrupaRobe');
      for (var i=0; i<response.length; i++){
        grupaR = response[i];

          select.append(
            '<option value="'+grupaR.id+'">'+grupaR.name+'</option>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});

}

function addRoba(){
	var naziv = $('#addNazivRobe').val().trim();
	var mera = $('#addJedinicaMere').val().trim();
	var cena = $('#addcenna').val().trim();

  var grupaR = document.getElementById('pickGrupaRobe');
  var grupaId= grupaR.options[grupaR.selectedIndex].value;
	if(naziv=="" || mera==""){
		alert("Sva polja moraju biti popunjena");
		return;

	}

var grupaObject;
$.ajax({
	url:'https://localhost:8081/api/gruparobe/getGRdeliteNo/'+grupaId,
	headers:{Authorization:"Bearer " + token},
	type: 'GET',
	dataType:'json',
	async: false,
	crossDomain: true,
	success:function(response){
		console.log(response);
		grupaObject = response;
	},
	error: function (jqXHR, textStatus, errorThrown) {
		if(jqXHR.status=="403"){
			alert("Error.");
		}

	}

});

	console.log(naziv+" "+mera+" "+cena+" "+grupaObject);
	var data={
		  'name':naziv,
			'jedninica_mere':mera,
			'cena':cena,
			'grupa':grupaObject

	}
	console.log(data);

	$.ajax({
		type: 'POST',
        contentType: 'application/json',
        url:'https://localhost:8081/api/roba/add',
        headers:{Authorization:"Bearer " + token},
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (response) {
        	console.log("usao u success")
        	alert("Dodavanje uspesno.");
        	$('#addRoba').modal('toggle');
        },
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Dodavanje neuspesno.");
			}

		}
    });
}

function robaDeleteModal(){
	$('#robaDeleteModal').modal();
}


function deleteRoba(){
	var brisanje = localStorage.getItem("deleteRoba");
	var token = localStorage.getItem("token");
	$.ajax({
        url: 'https://localhost:8081/api/roba/delete/'+brisanje,
        headers:{Authorization:"Bearer " + token},
        contentType: "application/json",
		type: 'DELETE',
        success: function (response) {
        	console.log("stopa delete success: ");
        	
        	$('#robaDeleteModal').modal('toggle');
        	location.reload();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}
