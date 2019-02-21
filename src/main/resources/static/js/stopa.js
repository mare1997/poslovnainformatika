var token= localStorage.getItem("token");
pdvId=""
$(document).ready(function() {
	loadStope();
	$(document).on("click", "#stopeBody tr", function(e) {
		delId = this.id;
		var pdvId = localStorage.getItem("pdvId");
		var delUserRow = $("#stopeBody tr");
		console.log(delId);
		localStorage.setItem("deleteStopu", delId);
		var asd = $('#'+delId+'');
		console.log(asd);
		asd.addClass("bg-danger");
		$.ajax({
		    url:'https://localhost:8081/api/stopapdv/getStopaByPdv/'+pdvId,
		    headers:{Authorization:"Bearer " + token},
		    type:"GET",
		    dataType: 'json',
		    crossDomain:true,
		    success: function (response) {
		      var table = $('#stopeBody');
		      for (var i=0; i<response.length; i++){
		    	  sp = response[i];
		    	  if(sp.id != delId){
		    		  $('#'+sp.id+'').removeClass("bg-danger"); 
		    	  }
		    	 
		      }
		    },error: function (jqXHR, textStatus, errorThrown) {
					alert("read error!!!");
		  }
		});
	
	});	
});
function loadStope(){

var pdvId = localStorage.getItem("pdvId");

 

  $.ajax({

    url:'https://localhost:8081/api/stopapdv/getStopaByPdv/'+pdvId,
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      console.log(response);
      var table = $('#stopeBody');
      for(var i=0; i<response.length; i++){
    	  console.log("usao u for");
    	  var stopa = response[i];
    	  console.log(stopa);
    	  table.append('<tr id="'+stopa.id+'"><td>'+stopa.procenat+'%</td><td>'+stopa.datum_vazenja+'</td></tr>');

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}

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


function addStopu(){
	var danas = currentDate();
	var pdvId = localStorage.getItem("pdvId");
	var procenat = $('#addProcenat').val().trim();
	//var danas = currentDate();
	var datumV = document.getElementById('addDatumVazenjaStope').value;
	if(datumV < danas){
		alert("datum vazenja ne moze biti manji od danasnjeg");
		return;
}
	if(procenat > 100 || procenat== 0 || procenat == "" || procenat == null){
		alert("Procenat mora biti najmanje 1 a najvise 100");
		return;
	}

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

	var data={'procenat' : procenat,
						 'datum_vazenja' : datumV,
						 'pdv' : pdvObject
					 	}

						console.log(data);

	$.ajax({
		type: 'POST',
				contentType: 'application/json',
				url:'https://localhost:8081/api/stopapdv/add',
				headers:{Authorization:"Bearer " + token},
				data: JSON.stringify(data),
				dataType: 'json',
		cache: false,
		processData: false,
				success: function (response) {
					alert("Dodavanje uspesno.")
					$('#addStopuPdv').modal('toggle');
					refresh();
				},
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Dodavanje neuspesno.");
			}

		}
		});

}


function stopaModal(){
	$('#addStopuPdv').modal();
}

function stopaDeleteModal(){
	$('#stopaDeleteModal').modal();
}

function deleteStopa(){
	var brisanje = localStorage.getItem("deleteStopa");
	var token = localStorage.getItem("token");
	$.ajax({
        url: 'https://localhost:8081/api/stopapdv/delete/'+brisanje,
        headers:{Authorization:"Bearer " + token},
        contentType: "application/json",
		type: 'DELETE',
        success: function (response) {
        	console.log("stopa delete success: ");
        	
        	$('#stopaDeleteModal').modal('toggle');
        	refresh();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}


function refresh(){
	var table = $('#stopeBody tr');
    console.log(table);
    table.remove();
	loadStope();
}