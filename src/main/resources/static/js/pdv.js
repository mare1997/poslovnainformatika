$(document).ready(function() {
	loadPdv();
	$(document).on("dblclick", "#pdvBody tr", function(e) {
		//var name = this.attr("name");
		var pdvId = this.id;
		console.log(pdvId);
		localStorage.setItem("pdvId", pdvId);
		window.location.replace("stopaPdv.html");
    //alert(name);
});
	
	$(document).on("click", "#pdvBody tr", function(e) {
		//var name = this.attr("name");
		var delPdvId = this.id;
		console.log(delPdvId);
		localStorage.setItem("delId", delPdvId);
    //alert(name);
});
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
            '<tr id="'+pdv.id+'">'+'<td>'+pdv.name+'</td></tr>'
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
				refresh();
			},
	error: function (jqXHR, textStatus, errorThrown) {
		if(jqXHR.status=="403"){
			alert("Dodavanje neuspesno.");
		}

	}
	});
}

function deletePdv(){
	var brisanje = localStorage.getItem("delId");
	var token = localStorage.getItem("token");
	$.ajax({
        url: 'https://localhost:8081/api/pdv/delete/'+brisanje,
        headers:{Authorization:"Bearer " + token},
        contentType: "application/json",
		type: 'DELETE',
        success: function (response) {
        	console.log("pdv delete success: ");
        	
        	$('#deletePdvModal').modal('toggle');
        	refresh();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}

function openDeleteModal(){
	$('#deletePdvModal').modal();
}
function refresh(){
	var table = $('#pdvBody tr');
    console.log(table);
    table.remove();
	loadPdv();
}