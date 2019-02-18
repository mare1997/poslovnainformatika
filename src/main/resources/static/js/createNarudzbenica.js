var token= localStorage.getItem("token");
var idRobe = 0;
var idN = 0;
var currentNarudzbenica = null;
var stavka = null;
var currentUserId = null;
var currentUsrName = null;

$(document).ready(function(){
	
	createNarudzbenica();
	 currentUserId = localStorage.getItem("id");
	 currentUserUsrName = localStorage.getItem("username");

	
	$('input[name="checkBoxActive"]').click(function() {
	    if($(this).is(':checked')){
	        alert('checked');
	        createFaktura();
	        
	        
	    }
	});
	
	function createFaktura(){
		console.log("Kreiranje fakture")
		var date = currentDate();
		  var formData ={
		    		'brojFakture' : null,
		    		'datumFakture' : date,
		    		'datumValute' : '2019-02-02' ,
		    		'osnovica' : 800,
		    		'ukupanPDV' : 500,
		    		'iznosZaPlacanje' : 900,
		    		'statusFakture' : "je",
		    		'narudzbeniceRel' : idN,
		    		'otpremnicaRel' : 1,
		    		'kupac' : idN,
		    		'user' : '1',
		    		'obrisano' : false,
		    		'preduzece' : 1
		    		 
		    }
		    console.log("Faktura formData" + formData)
		   
			$.ajax({
				type: 'POST',
		        url: 'https://localhost:8081/api/fakture/addFaktura',
		        headers:{Authorization:"Bearer " + token},
		        contentType: "application/json",
		        data: JSON.stringify(formData),


				dataType: 'json',
		        success: function (response) {
		        	console.log(response);
		        	idFakture = response.idFakture;
		        	console.log(idFakture);
		        	loadFakture(idFakture);
		        	
		        	
		        	
		        },
				error: function (jqXHR, textStatus, errorThrown) {  
					alert(textStatus);
				}
		    });
		}

	function loadFakture(id) {
		console.log("loadFakture")
		var tempUrl = "https://localhost:8081/api/fakture/"+id;
		$.ajax({
			url: tempUrl,
			headers:{Authorization:"Bearer " + token},
			type:'get',
			dataType: 'json',
			cashe: false,
			success: function(response){
				console.log("Id fakture je" + response.idFakture);
				id = response.idFakture;
				createStavkeFakture(id);
				$('#brFakture').html(id);
				var date = currentDate();
				$('#datumFakture').html(date);
			//	window.location.href = 'invoice.html?id='+id;
				}
	          
			},
			
		);
		
	}
	
	function createStavkeFakture(id){
		console.log("createStavkeFakture")
		  var formData ={
				  	'kolicina' : stavka.kolicina,
					'jedinicnaCena' : stavka.jedinicnaCena,
					'rabat' : 8000 ,
					'osnovicaZaPDV' : 85 ,
					'procenatPDV' : 2,
					'iznosPDV' : 560,
					'iznosStavke' : 9562,
					'idFakture' : id,
					'robaUslugaId' : stavka.robaUslugaId,
					'jedinicaMere' : stavka.jedinicaMere,
					'obrisano' : false
		    }
		

		  console.log(formData)
		  $.ajax({
				type: 'POST',
		        url: 'https://localhost:8081/api/fakture/stavkeFakture/addStavkaFakture',
		        headers:{Authorization:"Bearer " + token},
		        contentType: "application/json",
		        data: JSON.stringify(formData),


				dataType: 'json',
		        success: function (response) {
		        	console.log("dodao stavku fakture sa id " +response.idStavkeFakture )
		        	console.log("stavke fakture su" + response + "stavka je " + stavka );
		       // 	loadStavkeFakture(id, stavka);
		        
		        	
		    	}
			
			},
			
		);

	};
	
	
});



   
    function prikazRobe(){
    	var tempUrl = "https://localhost:8081/api/roba/getAllRobadeliteNoName/" ;
    	var x = document.getElementById("select1");
    	var y = x.options[x.selectedIndex].value;
		console.log("urlll" + tempUrl + "iddddd" + y);
		
		$.ajax({
			 
			method:'GET',
			url: tempUrl + y,
			headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			
			success: function(response){
				clearSelect2();
				console.log(response);
				var select = document.getElementById("select2");
				for(var i =0; i< response.length; i++){
					 var option = document.createElement("OPTION");
					 txt =  document.createTextNode(response[i].name);
					 option.appendChild(txt);
					 select2.insertBefore(option, select2.lastChild);
					 id = response[i].id;
					 console.log("id robe koji mi treba je " + id)
					
					 
				}
				
				
				 }
		
				
			},
		
			
			
		);
	};

function potvrdiN(){
	/*	$('#dodajRobu').modal('toggle');
		var kolRobe = $('#kolRobe').val();
		var x = document.getElementById("select2");
	    var y = x.options[x.selectedIndex].value;
		var node = document.createElement("LABEL");
		var textnode = document.createTextNode(y+ "("+ kolRobe + ")" + ", ");
		node.appendChild(textnode);
		document.getElementById("nazivRobe").appendChild(node);*/
	console.log("ID narudzbenice je" + currentNarudzbenica.idNarudzbenice)
	createStavkeNarudzbenice(currentNarudzbenica.idNarudzbenice);
}
    
	
 function loadGrupaRobe(){
	 console.log("load robe aaaaaaaaa")
	 var idRobe = 0;
	 $.ajax({
			method:'GET',
			url: "https://localhost:8081/api/gruparobe/getGRdeliteNo/all",
			headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			success: function(response){
					clearSelect1();
					var select = document.getElementById("select1");
					
					for(var i =0; i< response.length; i++){
					 var option = document.createElement("OPTION");
					// id = document.createTextNode(response[i].id);
					 txt =  document.createTextNode(response[i].name);
				
					 option.appendChild(txt);
					 select.insertBefore(option, select.lastChild);
				
					 
					 }
					
			
			},error: function (jqXHR, textStatus, errorThrown) {
				alert("read error!!!");
	  }
	});
	}
 function clearSelect1(){
	 var select = $('#select1');
	 select.empty();
 }
 function clearSelect2(){
	 var select = $('#select2');
	 select.empty();
 }			
 
 
 
 


 function createNarudzbenica(){
	 console.log("Kreiranje narudzbenice")
	 	var kolRobe = $('#kolRobe').val();
		var x = document.getElementById("select2");
	    var roba = x.options[x.selectedIndex].value;
	    idN = 0;
	    var formData ={
	    		'brojNarudzbenice' : '1',
	    		'datumIzrade' : '2019-01-02',
	    		'datumIsporuke' : '2019-02-02' ,
	    		'aktivna' : true,
	    		'obrisano' : false,
	    		'faktura_rel' : null,
	    		'kupac' : 1,
	    		'user' : 1,
	    		'preduzece' : '1'
	    		 
	    }
	    console.log(formData)
	   
		$.ajax({
			type: 'POST',
	        url: 'https://localhost:8081/api/narudzbenice/addNarudzbenica',
	        headers:{Authorization:"Bearer " + token},
	        contentType: "application/json",
	        data: JSON.stringify(formData),


			dataType: 'json',
	        success: function (response) {
	        	idN = response.idNarudzbenice;
	        	var narudzbenica = response;
	        	currentNarudzbenica = narudzbenica;
	        	$('#brNarudz').html(idN);
				var date = currentDate();
				$('#datumIzrade').html(date);
				$('#username').html(currentUserUsrName);
	        	
	        	
	        	
	        	
	        	console.log("Kreirana narudzbenica id" +currentNarudzbenica.idNarudzbenice)
	        	console.log(response.idNarudzbenice);
	      //  	createSN(idN);
	     //   	populateSN(currentNarudzbenica,idN);
	        	
	    	}
		
		},
		
	);

};

function createStavkeNarudzbenice(id){
	console.log("Kreiranje stavke narudzbenice" + id)
	var kolRobe = $('#kolRobe').val();
	var x = document.getElementById("select2");
    var roba = x.options[x.selectedIndex].value;
	
	
	  var formData ={
			  	'naziv' : roba,
				'kolicina' : $('#kolRobe').val(),
				'jedinicaMere' : 'kg' ,
				'idNarudzbenice' : id ,
				'robaUslugaId': 1,
				'obrisano' : false
	    }

	  console.log(formData)
	  $.ajax({
			type: 'POST',
	        url: 'https://localhost:8081/api/narudzbenice/stavkeNarudzbenice/addStavkaNarudzbenice',
	        headers:{Authorization:"Bearer " + token},
	        contentType: "application/json",
	        data: JSON.stringify(formData),


			dataType: 'json',
	        success: function (response) {
	        	console.log("Id stavke narudzbenice" + response.idStavkeNarudzbenice)
	        	$('#dodajRobu').modal('toggle');
	        	
	        	loadStavkeNarudzbenice(id);
	       // 	window.location.href = 'mojaNarudzbenica.html?id='+id;
	        //	populateStavkeNarudzbenice();
	        //	window.location.href = '/Videos/user.html?id=' + loggedInUser.id;
	        	
	    	}
		
		},
		
	);

};
function loadStavkeNarudzbenice(id){
	console.log("Ucitavanje stavke narudzbenice?")
	var tempUrl = "https://localhost:8081/api/narudzbenice/stavkeNarudzbenice/"+id;
	$.ajax({
		url: tempUrl,
		headers:{Authorization:"Bearer " + token},
		type:'get',
		dataType: 'json',
		cashe: false,
		success: function(response){
			console.log(response)
			clearNazivRobe();
			for(var i=0; i<response.length; i++){
				stavka = response[i];
				var nazivRobe = $('#nazivRobe');
				
			//Ispraviti   
				nazivRobe.append('<label>'+stavka.naziv+'('+stavka.kolicina+'</label>'+'),');
			//	document.getElementById('nazivRobe').append.innerHTML = stavka.naziv;
				
				
				}
				
			
			
		}
    
	},
	
);

}


function clearNazivRobe(){
	var nazivRobe = $('#nazivRobe');
	nazivRobe.empty();
}
/*function populateSN(id, narudzbenica){
	
	
	  var formData ={
			  	'naziv' : roba,
				'kolicina' : $('#kolRobe').val(),
				'jedinicaMere' : 'kg' ,
				'id_narudzbenice' : id ,
				'obrisano' : false
	    }
	  console.log(formData)
	  $.ajax({
			type: 'POST',
	        url: 'https://localhost:8081/api/narudzbenice/stavkeNarudzbenice/addStavkaNarudzbenice',
	        headers:{Authorization:"Bearer " + token},
	        contentType: "application/json",
	        data: JSON.stringify(formData),


			dataType: 'json',
	        success: function (response) {
	        	console.log("jeeeeeeeee" + response.idStavkeNarudzbenice)
	    //    	populateStavkeNarudzbenice();
	        	
	        	
	    	}
		
		},
		
	);

};
	*/


	

