var token= localStorage.getItem("token");
cenId=""
$(document).ready(function() {
	loadStavkeCen();
});
function loadStavkeCen(){

var cenId = localStorage.getItem("cenId");

  $.ajax({

    url:'https://localhost:8081/api/stavkecenovnika/all/'+cenId,
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var table = $('#ceneBody');
      for (var i=0; i<response.length; i++){
        cena = response[i];

          table.append(
            '<tr value="'+cena.id+'">'+'<td>'+cena.name+'</td><td>'+cena.roba+'</td></tr>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}


function addStavkuCen(){
	var cenId = localStorage.getItem("cenId");
	var cena = $('#cenaStavnke').val().trim();
	var kolRobe = $('#kolStavnke').val().trim();
	if(procenat > 100 || procenat = 0 || datumV < danas){
		alert("Sva polja moraju biti popunjena");
		return;
	}

	var cenObject;
	$.ajax({
		url:'https://localhost:8081/api/cenovnik//getCenovnikdeleteNo/'+cenId,
		headers:{Authorization:"Bearer " + token},
		type: 'GET',
		dataType:'json',
		async: false,
		crossDomain: true,
		success:function(response){
			console.log(response);
			cenObject = response;
		},
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Error.");
			}

		}

	});

	var data={'cena' : cena,
						 'roba' : kolRobe,
						 'cenovnik' : cenObject
					 	}

						console.log(data);

	$.ajax({
		type: 'POST',
				contentType: 'application/json',
				url:'https://localhost:8081/api/stavkacenovnika/add',
				headers:{Authorization:"Bearer " + token},
				data: JSON.stringify(data),
				dataType: 'json',
		cache: false,
		processData: false,
				success: function (response) {
					alert("Dodavanje uspesno.")
					$('#addStavkuCenovnika').modal('toggle');
					location.reload();
				},
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Dodavanje neuspesno.");
			}

		}
		});

}


function stavkaModal(){
	$('#addStavkuCenovnika').modal('toggle');
}
