$(document).ready(function() {
	loadRoba();
	console.log(idGrupe);
	

	$(document).on("click", "#robaBody tr", function(e) {
		delId = this.id;
		var idGrupe = localStorage.getItem("grupaId");
		var delUserRow = $("#robaBody tr");
		console.log(delId);
		localStorage.setItem("deleteRoba", delId);
		var asd = $('#'+delId+'');
		console.log(asd);
		asd.addClass("bg-danger");
		$.ajax({
		    url:'https://localhost:8081/api/roba/getAllRobadeliteNo/'+idGrupe,
		    headers:{Authorization:"Bearer " + token},
		    type:"GET",
		    dataType: 'json',
		    crossDomain:true,
		    success: function (response) {
		      var table = $('#robaBody');
		      for (var i=0; i<response.length; i++){
		    	  r = response[i];
		    	  if(r.id != delId){
		    		  $('#'+r.id+'').removeClass("bg-danger"); 
		    	  }
		    	 
		      }
		    },error: function (jqXHR, textStatus, errorThrown) {
					alert("read error!!!");
		  }
		});
	
	});	
});
var token= localStorage.getItem("token");
var idGrupe = localStorage.getItem("grupaId");
var pId="";


var cenId = localStorage.getItem("akCenovnik");
function loadRoba(){
	
  $.ajax({
    url:'https://localhost:8081/api/roba/getAllRobadeliteNo/'+idGrupe,
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    async:false,
    success: function (response) {
      var table = $('#robaBody');
      for (var i=0; i<response.length; i++){
        roba = response[i];
		console.log("roba id je: "+roba.id);
		
		var cena;
		$.ajax({
		    url:'https://localhost:8081/api/stavkacenovnika/getCenaByRoba/'+roba.id+'/'+cenId,
		    headers:{Authorization:"Bearer " + token},
		    type:"GET",
		    dataType: 'json',
		    async:false,
		    crossDomain:true,
		    success: function (response) {
		    	cena = response.cena;
		    	console.log(cena);
		    	table.append(
		                '<tr id="'+roba.id+'" value="'+roba.id+'">'+'<td>'+roba.name+'</td><td>'+roba.grupa.name+'</td><td>'+roba.jedninica_mere+'</td><td>'+cena+'</td></tr>'
		              )

		    },error: function (jqXHR, textStatus, errorThrown) {
					alert("log error!!!");
		  }
		});
                  

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}

function fillGrupaRobe(){
	var pgId = localStorage.getItem("pgId",pgId);
	var pId = localStorage.getItem("pId",pId);
	$('#addRoba').modal();
	$.ajax({
    url:'https://localhost:8081/api/gruparobe/getGRdeliteNo/'+idGrupe+'/'+pId+'/'+pgId,
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var select = $('#pickGrupaRobe');
      select.val(response.name);
      
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});

}
//dodam robu sa id za cenu 0, dodam stavke cen sa id ak cen i id robe, update robu i dodam cenu id
var idKreiranog;
function addRoba(){
	var naziv = $('#addNazivRobe').val().trim();
	var mera = $('#addJedinicaMere').val().trim();
	var cena = $('#inputCenaStavke').val().trim();

  var grupaId= idGrupe;
	if(naziv=="" || mera=="" || cena==""){
		alert("Sva polja moraju biti popunjena");
		return;

	}
	var pgId = localStorage.getItem("pgId");
	var pId = localStorage.getItem("pId");
	var akCenId = localStorage.getItem("akCenovnik");
	var grupaObject;
$.ajax({
	
	url:'https://localhost:8081/api/gruparobe/getGRdeliteNo/'+idGrupe+'/'+pId+'/'+pgId,
	headers:{Authorization:"Bearer " + token},
	type: 'GET',
	dataType:'json',
	async: false,
	crossDomain: true,
	success:function(response){
		console.log(response);
		grupaObject = response;
	},
	error: function (jqXHR, textStatus, errorThrown) {
		if(jqXHR.status=="403"){
			alert("Error.");
		}

	}

});



	var data={
			'name':naziv,
			'jedninica_mere':mera,
			'grupa':grupaObject,
			'cena': cena

	}
	console.log(data);
	
	

	$.ajax({
		type: 'POST',
        contentType: 'application/json',
        url:'https://localhost:8081/api/roba/add',
        headers:{Authorization:"Bearer " + token},
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (response) {
        	console.log("usao u success")
        	alert("Dodavanje uspesno.");
        	
        	idKreiranog = response.id;
        	console.log(response.id);
        	
        	$('#addRoba').modal('hide');
        	refresh();
       
        	
        },
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Dodavanje neuspesno.");
			}

		}
    });
}

function robaDeleteModal(){
	$('#robaDeleteModal').modal();
}


function deleteRoba(){
	var brisanje = localStorage.getItem("deleteRoba");
	var token = localStorage.getItem("token");
	$.ajax({
        url: 'https://localhost:8081/api/roba/delete/'+brisanje,
        headers:{Authorization:"Bearer " + token},
        contentType: "application/json",
		type: 'DELETE',
        success: function (response) {
        	console.log("roba delete success: ");
        	
        	$('#robaDeleteModal').modal('toggle');
        	refresh();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}

function refresh(){
	var table = $('#robaBody tr');
    console.log(table);
    table.remove();
	loadRoba();
}

/** var idKreiraneStavke;
function addSCen(){
	var cenId = localStorage.getItem("akCenovnik");
	var cena = $('#inputCenaStavke').val().trim();
	var pgId = localStorage.getItem("pgId",pgId);
	var pId = localStorage.getItem("pId",pId);

	var cenObject;
	$.ajax({
		url:'https://localhost:8081/api/cenovnik/getCenovnikdeleteNo/'+cenId+'/'+pId+'/'+pgId,
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
	console.log("id cenovnika aktivnog"+ cenId);
	console.log

	var data={			 'cena' : cena,
						 'roba' : idKreiranog, //id robe
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
					
					idKreiraneStavke= response.id;
					update();
					
					
				},
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Dodavanje neuspesno.");
			}

		}
		});

}

function update(){
	console.log(idKreiraneStavke);
	var data = {'cena' : idKreiraneStavke};
	
	$.ajax({
		url:'https://localhost:8081/api/roba/edit/'+idKreiranog,
		headers:{Authorization:"Bearer " + token},
		type: 'PUT',
		data: JSON.stringify(data),
		dataType:'json',
		cache: false,
		processData: false,
		contentType: 'application/json',
		success:function(response){
			console.log("update success");
			$('#addRoba').modal('toggle');
			
			refresh();
		},
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Error.");
			}

		}

	});
	
}
**/
// /getAllActiveRobaByName
function searchRoba(){
	var idGrupe = localStorage.getItem("grupaId");
	var searchPoljeValue = document.getElementById("searchRoba").value;
	console.log(searchPoljeValue);
	var table = $('#robaBody tr');
    console.log(table);
    table.remove();
    $.ajax({
        url:'https://localhost:8081/api/roba/getAllActiveRobaByName/'+idGrupe+'/?name='+searchPoljeValue,
        headers:{Authorization:"Bearer " + token},
        type:"GET",
        dataType: 'json',
        crossDomain:true,
        success: function (response) {
          var table = $('#robaBody');
          for (var i=0; i<response.length; i++){
            roba = response[i];
    			console.log(roba);
              table.append(
                '<tr id="'+roba.id+'" value="'+roba.id+'">'+'<td>'+roba.name+'</td><td>'+roba.grupa.name+'</td><td>'+roba.jedninica_mere+'</td><td>'+roba.cena+'</td></tr>'
              )

          }
        },error: function (jqXHR, textStatus, errorThrown) {
    			alert("read error!!!");
      }
    });
	
	
}