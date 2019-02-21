$(document).ready(function() {
	loadMesto();

    //gore brisi
		$(document).on("click", "#mestoBody tr", function(e) {
			delId = this.id;
			var delUserRow = $("#mestoBody tr");
			console.log(delId);
			localStorage.setItem("deleteMesto", delId);
			var asd = $('#'+delId+'');
			console.log(asd);
			asd.addClass("bg-danger");
			$.ajax({
			    url:'https://localhost:8081/api/mesto/all',
			    headers:{Authorization:"Bearer " + token},
			    type:"GET",
			    dataType: 'json',
			    crossDomain:true,
			    success: function (response) {
			      var table = $('#mestoBody');
			      for (var i=0; i<response.length; i++){
			    	  m = response[i];
			    	  if(m.id != delId){
			    		  $('#'+m.id+'').removeClass("bg-danger"); 
			    	  }
			    	 
			      }
			    },error: function (jqXHR, textStatus, errorThrown) {
						alert("read error!!!");
			  }
			});
		
	
});
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
            '<tr id="'+mesto.id+'">'+'<td>'+mesto.grad+'</td>'+'<td>'+mesto.postanski_broj+'</td></tr>'
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
				refresh();
			},
	error: function (jqXHR, textStatus, errorThrown) {
		if(jqXHR.status=="403"){
			alert("Dodavanje neuspesno.");
		}

	}
	});
}


function mestoDeleteModal(){
	$('#mestoDeleteModal').modal();
}

function deleteMesto(){
	var brisanje = localStorage.getItem("deleteMesto");
	var token = localStorage.getItem("token");
	$.ajax({
        url: 'https://localhost:8081/api/mesto/delete/'+brisanje,
        headers:{Authorization:"Bearer " + token},
        contentType: "application/json",
		type: 'DELETE',
        success: function (response) {
        	console.log("mesto delete success: ");
        	
        	$('#mestoDeleteModal').modal('toggle');
        	refresh();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}

function refresh(){
	var table = $('#mestoBody tr');
    console.log(table);
    table.remove();
	loadMesto();
}
