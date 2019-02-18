var pId = ""
$(document).ready(function() {
	loadCen();
});
var token= localStorage.getItem("token");

function currentDate(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!

	var yyyy = today.getFullYear();
	if(dd<10){
	    dd='0'+dd;
	}
	if(mm<10){
	    mm='0'+mm;
	}
	var today = yyyy+"-"+mm+"-"+dd;

	return today;
}

function loadCen(){
  $.ajax({
    url:'https://localhost:8081/api/cenovnik/getCenovnikdeleteNo/all',
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var table = $('#cenBody');
      for (var i=0; i<response.length; i++){
        cen = response[i];

          table.append(
            '<tr name="'+cen.id+'"><td>'+cen.name+'</td><td>'+cen.datum_vazenja+'<td><button onclick="click()" height="15px" width="15px" value="read more" name="'+cen.id+'"></button></td></tr>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}

function cenModal(){
	$('#addCenovnik').modal();
}

function addCenovnik(){
	var ime = $('#addCenName').val().trim();
	var danas = currentDate();
	var datumV = document.getElementById('addDatumVazenja').value;
	var preduzeceId=localStorage.getItem("pId")
	if(ime=="" || datumV < danas){
		alert("Sva polja moraju biti popunjena");
		return;
}

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


var data={'name' : ime,
					 'datum_vazenja' : datumV,
					 'preduzece' : preduzeceObject
				 	}

					console.log(data);

$.ajax({
	type: 'POST',
			contentType: 'application/json',
			url:'https://localhost:8081/api/cenovnik/add',
			headers:{Authorization:"Bearer " + token},
			data: JSON.stringify(data),
			dataType: 'json',
	cache: false,
	processData: false,
			success: function (response) {
				alert("Dodavanje uspesno.")
				$('#addCenovnik').modal('toggle');
				location.reload();
			},
	error: function (jqXHR, textStatus, errorThrown) {
		if(jqXHR.status=="403"){
			alert("Dodavanje neuspesno.");
		}

	}
	});
}

function click() {
      //var cId = x.name;
			//console.log(cId);
			//localStorage.setItem("cenId", cId);
			window.location.replace("ceneCenovnika.html");
  }
