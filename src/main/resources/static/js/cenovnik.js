var pId = ""
$(document).ready(function() {
	loadCen();
	//addRowHandlers();
	$(document).on("dblclick", "#cenBody tr", function(e) {
			//var name = this.attr("name");
			var cenId = this.id;
			console.log(cenId);
			localStorage.setItem("cenId", cenId);
			window.location.replace("ceneCenovnika.html");
	    //alert(name);
	});
	
	$(document).on("click", "#cenBody tr", function(e) {
		var pgId = localStorage.getItem("pgId",pgId);
		var pId = localStorage.getItem("pId",pId);
		delId = this.id;
		var delUserRow = $("#cenBody tr");
		console.log(delId);
		localStorage.setItem("deleteId", delId);
		var asd = $('#'+delId+'');
		console.log(asd);
		asd.addClass("bg-danger");
		$.ajax({
		    url:'https://localhost:8081/api/cenovnik/getCenovnikdeleteNo/all/'+pId+'/'+pgId,
		    headers:{Authorization:"Bearer " + token},
		    type:"GET",
		    dataType: 'json',
		    crossDomain:true,
		    success: function (response) {
		      var table = $('#cenBody');
		      for (var i=0; i<response.length; i++){
		    	  cen = response[i];
		    	  if(cen.id != delId){
		    		  $('#'+cen.id+'').removeClass("bg-danger"); 
		    	  }
		    	 
		      }
		    },error: function (jqXHR, textStatus, errorThrown) {
					alert("read error!!!");
		  }
		});
});
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
	var pgId = localStorage.getItem("pgId",pgId);
	var pId = localStorage.getItem("pId",pId);
  $.ajax({
    url:'https://localhost:8081/api/cenovnik/getCenovnikdeleteNo/all/'+pId+'/'+pgId,
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var table = $('#cenBody');
      for (var i=0; i<response.length; i++){
        cen = response[i];

          table.append(
            '<tr id="'+cen.id+'"><td>'+cen.name+'</td><td>'+cen.datum_vazenja+'<td></td></tr>'
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
	if(datumV < danas){
		alert("Sva polja moraju biti popunjena");
		return;
}	
	if(datumV < danas){
		alert("Datum ne sme biti manji od danasnjeg");
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


var data={			 'name' : ime,
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
				refresh();
			},
	error: function (jqXHR, textStatus, errorThrown) {
		if(jqXHR.status=="403"){
			alert("Dodavanje neuspesno.");
		}

	}
	});
}

function cenovnikDeleteModal(){
	$('#cenovnikDeleteModal').modal();
}


function deleteCenovnik(){
	var brisanje = localStorage.getItem("deleteCenovnik");
	var token = localStorage.getItem("token");
	$.ajax({
        url: 'https://localhost:8081/api/cenovnik/delete/'+brisanje,
        headers:{Authorization:"Bearer " + token},
        contentType: "application/json",
		type: 'DELETE',
        success: function (response) {
        	console.log("cenovnik delete success: ");
        	
        	$('#cenovnikDeleteModal').modal('toggle');
        	refresh();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}

function refresh(){
	var table = $('#cenBody tr');
    console.log(table);
    table.remove();
	loadCen();
}
