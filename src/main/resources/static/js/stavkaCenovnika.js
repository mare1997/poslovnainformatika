var token= localStorage.getItem("token");
cenId=""
$(document).ready(function() {
	loadStavkeCen();
	$(document).on("click", "#ceneBody tr", function(e) {
		delId = this.id;
		var cenId = localStorage.getItem("cenId");
		var delUserRow = $("#ceneBody tr");
		console.log(delId);
		localStorage.setItem("deleteStavku", delId);
		var asd = $('#'+delId+'');
		console.log(asd);
		asd.addClass("bg-danger");
		$.ajax({
		    url:'https://localhost:8081/api/stavkacenovnika/all/'+cenId,
		    headers:{Authorization:"Bearer " + token},
		    type:"GET",
		    dataType: 'json',
		    crossDomain:true,
		    success: function (response) {
		      var table = $('#ceneBody');
		      for (var i=0; i<response.length; i++){
		    	  sc = response[i];
		    	  if(sc.id != delId){
		    		  $('#'+sc.id+'').removeClass("bg-danger"); 
		    	  }
		    	 
		      }
		    },error: function (jqXHR, textStatus, errorThrown) {
					alert("read error!!!");
		  }
		});
	
	});	
});
function loadStavkeCen(){

var cenId = localStorage.getItem("cenId");

 

  $.ajax({

    url:'https://localhost:8081/api/stavkacenovnika/all/'+cenId,
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      console.log(response);
      var table = $('#ceneBody');
      for(var i=0; i<response.length; i++){
    	  console.log("usao u for");
    	  var cena = response[i];
    	  console.log(cena);
    	  table.append('<tr id="'+cena.id+'"><td>'+cena.roba+'</td><td>'+cena.cena+'</td></tr>');

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}


function addStavkuCen(){
	var cenId = localStorage.getItem("cenId");
	var cena = $('#cenaStavnke').val().trim();
	var roba = document.getElementById('addRobuStavke');
	var robaId= roba.options[roba.selectedIndex].value;

	var cenObject;
	$.ajax({
		url:'https://localhost:8081/api/cenovnik/getCenovnikdeleteNo/'+cenId,
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

	var data={			 'cena' : cena,
						 'roba' : robaId, //id robe
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
					refresh();
				},
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Dodavanje neuspesno.");
			}

		}
		});

}


function stavkaModal(){
	$('#addStavkuCenovnika').modal();
	
	$.ajax({
    url:'https://localhost:8081/api/roba/getAllRobadeliteNo/all',
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
			var select = $('#addRobuStavke');
			for (var i=0; i<response.length; i++){
				roba = response[i];
				console.log(roba);
				select.append(
		            '<option value="'+roba.id+'">'+roba.name+'</option>'
		          );
    }
  },error: function (jqXHR, textStatus, errorThrown) {
      alert("read error!!!");

}
	});
}


function stavkaDeleteModal(){
	$('#stavkaDeleteModal').modal();
}

function deleteStavka(){
	var brisanje = localStorage.getItem("deleteStavka");
	var token = localStorage.getItem("token");
	$.ajax({
        url: 'https://localhost:8081/api/stavkacenovnika/delete/'+brisanje,
        headers:{Authorization:"Bearer " + token},
        contentType: "application/json",
		type: 'DELETE',
        success: function (response) {
        	console.log("stavka delete success: ");
        	
        	$('#stopaDeleteModal').modal('toggle');
        	refresh();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}

function refresh(){
	var table = $('#ceneBody tr');
    console.log(table);
    table.remove();
	loadStavkeCen();
}