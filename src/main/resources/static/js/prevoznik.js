$(document).ready(function() {
	loadPrevoznik();
	$(document).on("click", "#prevoznikBody tr", function(e) {
		//var name = this.attr("name");
		var delId = this.id;
		var delUserRow = $("#prevoznikBody tr");
		console.log(delId);
		localStorage.setItem("deletePrevoznik", delId);
    //alert(name);
});
});
var token= localStorage.getItem("token");
function loadPrevoznik(){
  $.ajax({
    url:'https://localhost:8081/api/prevoznik/all',
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var table = $('#prevoznikBody');
      for (var i=0; i<response.length; i++){
        prevoznik = response[i];

          table.append(
            '<tr id="'+prevoznik.id+'">'+'<td>'+prevoznik.name+'</td></tr>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}

function addPrevoznika(){
	var ime = $('#addNazivPrevoznika').val().trim();
	if(ime==""){
		alert("Sva polja moraju biti popunjena");
		return;
}

console.log(ime);
var data={'name' : ime}

$.ajax({
	type: 'POST',
			contentType: 'application/json',
			url:'https://localhost:8081/api/prevoznik/add',
			headers:{Authorization:"Bearer " + token},
			data: JSON.stringify(data),
			dataType: 'json',
	cache: false,
	processData: false,
			success: function (response) {
				alert("Dodavanje uspesno.")
				$('#addPrevoznika').modal('toggle');
				location.reload();
			},
	error: function (jqXHR, textStatus, errorThrown) {
		if(jqXHR.status=="403"){
			alert("Dodavanje neuspesno.");
		}

	}
	});
}


function deletePrevoznika(id){
	console.log(id);
	$.ajax({
        url: 'https://localhost:8081/api/prevoznik/'+id,
        contentType: "application/json",
		type: 'DELETE',
        success: function (response) {
        	console.log("Prevoznik uspesno obrisan ");
        	loadPrevoznik();
           
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}

function prevoznikDeleteModal(){
	$('#prevoznikDeleteModal').modal();
}


function deletePrevoznik(){
	var brisanje = localStorage.getItem("deletePrevoznik");
	var token = localStorage.getItem("token");
	$.ajax({
        url: 'https://localhost:8081/api/prevoznik/delete/'+brisanje,
        headers:{Authorization:"Bearer " + token},
        contentType: "application/json",
		type: 'DELETE',
        success: function (response) {
        	console.log("prevoznik delete success: ");
        	
        	$('#prevoznikDeleteModal').modal('toggle');
        	location.reload();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}

