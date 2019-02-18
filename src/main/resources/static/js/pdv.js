$(document).ready(function() {
	loadPdv();
});
var token= localStorage.getItem("token");
function loadPdv(){
  $.ajax({
    url:'https://localhost:8081/api/pdv/getPDVdeleteNo/all',
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var table = $('#pdvBody');
      for (var i=0; i<response.length; i++){
        pdv = response[i];

          table.append(
            '<tr>'+'<td>'+pdv.name+'</td></tr>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}

function pdvModal(){
	$('#addPdv').modal();
}


function addPdv(){
	var pdv = $('#addNazivPdv').val().trim();
	if(pdv==""){
		alert("Sva polja moraju biti popunjena");
		return;
}

console.log(pdv);
var data={'name' : pdv}

$.ajax({
	type: 'POST',
			contentType: 'application/json',
			url:'https://localhost:8081/api/pdv/add',
			headers:{Authorization:"Bearer " + token},
			data: JSON.stringify(data),
			dataType: 'json',
	cache: false,
	processData: false,
			success: function (response) {
				alert("Dodavanje uspesno.")
				$('#addPdv').modal('toggle');
				location.reload();
			},
	error: function (jqXHR, textStatus, errorThrown) {
		if(jqXHR.status=="403"){
			alert("Dodavanje neuspesno.");
		}

	}
	});
}
