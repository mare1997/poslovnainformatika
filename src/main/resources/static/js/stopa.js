var token= localStorage.getItem("token");
pdvId=""
$(document).ready(function() {
	loadStope();
	$(document).on("click", "#stopeBody tr", function(e) {
		//var name = this.attr("name");
		var delId = this.id;
		var delUserRow = $("#stopeBody tr");
		console.log(delId);
		localStorage.setItem("deleteStopa", delId);
    //alert(name);
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


function addStopu(){
	var pdvId = localStorage.getItem("pdvId");
	var procenat = $('#addProcenat').val().trim();
	//var danas = currentDate();
	var datumV = document.getElementById('addDatumVazenjaStope').value;

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
					location.reload();
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
        	location.reload();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}
