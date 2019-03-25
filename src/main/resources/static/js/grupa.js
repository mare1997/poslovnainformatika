var token = localStorage.getItem("token");
$(document).ready(function() {
	loadGrupu();
	
	
	$(document).on("dblclick", "#grupaBody tr", function(e) {
		//var name = this.attr("name");
		var grupaId = this.id;
		console.log(grupaId);
		localStorage.setItem("grupaId", grupaId);
		window.location.replace("listaRobe.html");
    //alert(name);
});
	
	
	$(document).on("click", "#grupaBody tr", function(e) {
		var pgId = localStorage.getItem("pgId",pgId);
		var pId = localStorage.getItem("pId",pId);
		delId = this.id;
		var delUserRow = $("#grupaBody tr");
		console.log(delId);
		localStorage.setItem("deleteId", delId);
		var asd = $('#'+delId+'');
		console.log(asd);
		asd.addClass("bg-danger");
		$.ajax({
		    url:'https://localhost:8081/api/gruparobe/getGRdeliteNo/all/'+pId+'/'+pgId,
		    headers:{Authorization:"Bearer " + token},
		    type:"GET",
		    dataType: 'json',
		    crossDomain:true,
		    success: function (response) {
		      var table = $('#grupaBody');
		      for (var i=0; i<response.length; i++){
		    	  roba = response[i];
		    	  if(roba.id != delId){
		    		  $('#'+roba.id+'').removeClass("bg-danger"); 
		    	  }
		    	 
		      }
		    },error: function (jqXHR, textStatus, errorThrown) {
					alert("read error!!!");
		  }
		});
	
	});	
});



preduzeceId=""
var token= localStorage.getItem("token");
function loadGrupu(){
	var pgId = localStorage.getItem("pgId",pgId);
	var pId = localStorage.getItem("pId",pId);
  $.ajax({
    url:'https://localhost:8081/api/gruparobe/getGRdeliteNo/all/'+pId+'/'+pgId,
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var table = $('#grupaBody');
      for (var i=0; i<response.length; i++){
        grupa = response[i];

          table.append(
            '<tr id="'+grupa.id+'">'+'<td>'+grupa.name+'</td>'+'<td>'+grupa.pdv.name+'</td></tr>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}


var today;
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
console.log(today);



function grupaModal(){
	$('#addGrupaRobeee').modal();
  $.ajax({
    url:'https://localhost:8081/api/pdv/getPDVdeleteNo/all',
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var table = $('#grupaRobePdv');
      for (var i=0; i<response.length; i++){
        pdv = response[i];

          table.append(
            '<option value="'+pdv.id+'">'+pdv.name+'</option>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
      alert("read error!!!");
  }
});
}

function addGrupaRobe(){
	var ime = $('#addNazivGrupeRobe').val().trim();
  var pdvSel = document.getElementById('grupaRobePdv');
	var preduzeceId = localStorage.getItem("pId");
  var pdvId= pdvSel.options[pdvSel.selectedIndex].value;
	if(ime==""){
		alert("Sva polja moraju biti popunjena");
		return;

	}
	var preduzeceId=localStorage.getItem("pId");
	var preduzeceObject;
$.ajax({
	url:'https://localhost:8081/api/preduzece/'+preduzeceId,
	headers:{Authorization:"Bearer " + token},
	type: 'GET',
	dataType:'json',
	async: false,
	crossDomain: true,
	success:function(response){
		console.log(response);
		preduzeceObject = response;
	},
	error: function (jqXHR, textStatus, errorThrown) {
		if(jqXHR.status=="403"){
			alert("Error.");
		}

	}

});
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

	console.log(ime+" "+pdvObject+" "+preduzeceObject);
	currentDate();
	today = currentDate();
	var data={
		  'name':ime,
			'pdv':pdvObject,
			'preduzece':preduzeceObject,
			'datum_kreiranja': today

	}
	
	
	console.log(data.datum_kreiranja == null);

	$.ajax({
		type: 'POST',
        contentType: 'application/json',
        url:'https://localhost:8081/api/gruparobe/add',
        headers:{Authorization:"Bearer " + token},
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (response) {
        	console.log("usao u success")
        	alert("Dodavanje uspesno.");
        	$('#addKorisnika').modal('toggle');
        	refresh();
        },
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Dodavanje neuspesno.");
			}

		}
    });
}

function grupaDeleteModal(){
	$('#grupaDeleteModal').modal();
}


function deleteGrupa(){
	var brisanje = localStorage.getItem("deleteGrupa");
	var token = localStorage.getItem("token");
	$.ajax({
        url: 'https://localhost:8081/api/gruparobe/delete/'+brisanje,
        headers:{Authorization:"Bearer " + token},
        contentType: "application/json",
		type: 'DELETE',
        success: function (response) {
        	console.log("grupa delete success: ");
        	
        	$('#grupaDeleteModal').modal('toggle');
        	refresh();
        },
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(textStatus);
		}
    });
}

function refresh(){
	var table = $('#grupaBody tr');
    console.log(table);
    table.remove();
    loadGrupu();
}

