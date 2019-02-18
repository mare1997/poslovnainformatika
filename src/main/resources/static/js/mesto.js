$(document).ready(function() {
	loadMesto();
});
var token= localStorage.getItem("token");
function loadMesto(){
  $.ajax({
    url:'https://localhost:8081/api/mesto/all',
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var table = $('#mestoBody');
      for (var i=0; i<response.length; i++){
        mesto = response[i];

          table.append(
            '<tr>'+'<td>'+mesto.grad+'</td>'+'<td>'+mesto.postanski_broj+'</td></tr>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}

function mestoModal(){
	$('#addMesto').modal();
}


function addMesto(){
	var grad = $('#addGrad').val().trim();
  var postanski_broj = $('#addPB').val().trim();
	if(grad==""){
		alert("Sva polja moraju biti popunjena");
		return;
}

var data={'grad' : grad,
          'postanski_broj':postanski_broj}

$.ajax({
	type: 'POST',
			contentType: 'application/json',
			url:'https://localhost:8081/api/mesto/add',
			headers:{Authorization:"Bearer " + token},
			data: JSON.stringify(data),
			dataType: 'json',
	cache: false,
	processData: false,
			success: function (response) {
				alert("Dodavanje uspesno.")
				$('#addMesto').modal('toggle');
				location.reload();
			},
	error: function (jqXHR, textStatus, errorThrown) {
		if(jqXHR.status=="403"){
			alert("Dodavanje neuspesno.");
		}

	}
	});
}
