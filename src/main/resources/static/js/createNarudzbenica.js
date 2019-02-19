var token= localStorage.getItem("token");
var idRobe = 0;
var idN = 0;
var narNewId=0;
var idFakture = 0;
var currentNarudzbenica = null;
//var stavka = null;
var currentUserId = null;
var currentUsrName = null;

$(document).ready(function(){
	
//	
	currentUserId = localStorage.getItem("id");
	currentUserUsrName = localStorage.getItem("username");
	createDefaultNar();
	
	
});
function createFaktura(id){
	console.log("Kreiranje fakture")
	var idF=0;
	var date = currentDate();
	  var formData ={
	    		'brojFakture' : null,
	    		'datumFakture' : date,
	    		'datumValute' : '2019-02-02' ,
	    		'osnovica' : 800,
	    		'ukupanPDV' : 500,
	    		'iznosZaPlacanje' : 900,
	    		'statusFakture' : "je",
	    		'narudzbeniceRel' : id,
	    		'otpremnicaRel' : 1,
	    		'kupac' : narNewId,
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
	        async:false,

			dataType: 'json',
	        success: function (response) {
	        	console.log(response);
	        	idFakture = response.idFakture;
	        	idF = response.idFakture;
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
			createStavkeFakture(response.narudzbeniceRel,id);
			$('#brFakture').html(id);
			var date = currentDate();
			$('#datumFakture').html(date);
		//	
			}
          
		},
		
	);
	
}

function createStavkeFakture(id,idF){
	//iz id nadji objekat fakture, iz fakture nadji id narudzbenice,izlistaj stavke narudzbenice za taj id narudzbenice i onda koroz for petlju za svaku stavku nar dodaj stavku fakture
	console.log("createStavkeFakture")
	 $.ajax({
			type: 'Get',
	        url: 'https://localhost:8081/api/narudzbenice/stavkeNarudzbenice/active/'+id,
	        headers:{Authorization:"Bearer " + token},
	        success: function (response) {
	        	for(var i=0; i<response.length; i++){
	        		createF(response[i],idF);
	        	}
	        	
	        	
	       // 	
	        
	        	
	    	}
		
		},
		
	);

}
function createF(stavka,idF){
		var formData ={
			  	'kolicina' : stavka.kolicina,
				'jedinicnaCena' : stavka.jedinicnaCena,
				'rabat' : 8000 ,
				'osnovicaZaPDV' : 85 ,
				'procenatPDV' : 2,
				'iznosPDV' : 560,
				'iznosStavke' : 9562,
				'idFakture' : idF,
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
	
}


   
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
//	console.log("ID narudzbenice je" + currentNarudzbenica.idNarudzbenice)
	createStavkeNarudzbenice(narNewId);
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
 
function createDefaultNar(){
	var date = currentDate();
	var formData ={
	   		'datumIzrade' : date,
    		'aktivna' : true,
    		'obrisano' : false,
    		'user' : currentUserId,
    		'preduzece' : '1'
    		 
    }
	$.ajax({
		type: 'POST',
        url: 'https://localhost:8081/api/narudzbenice/addNarudzbenica',
        headers:{Authorization:"Bearer " + token},
        contentType: "application/json",
        data: JSON.stringify(formData),


		dataType: 'json',
        success: function (response) {
        	narNewId = response.idNarudzbenice;
        	if(response.aktivna == false){
        		createFaktura(response.idNarudzbenice);
        	}
        	
        	var narudzbenica = response;
        	currentNarudzbenica = narudzbenica;
        	console.log("Kreirana narudzbenica id" +currentNarudzbenica.idNarudzbenice)
        	console.log(response.idNarudzbenice);
        	$('#brNarudz').html(narNewId);
        	$('#datumIzrade').html(date);
        	$('#username').html(currentUserUsrName);
        	
    		}
	
		},
	
	);
}
 
 


 function potvrdiNarudzbenicu(){
	 console.log("Kreiranje narudzbenice")
	 	var kolRobe = $('#kolRobe').val();
		var x = document.getElementById("select2");
	    var roba = x.options[x.selectedIndex].value;
	    
	    
	    var x = document.getElementById("chbxActive").checked;
	    console.log("cekbox: " + x);
	    var active = true;
	    if(x==true){
	    	active = false;
	    }
	    console.log("Id narudz pre kreiranja fakture: " + narNewId);
		if(active == false){
			createFaktura(narNewId);
		}
		console.log("Id fakture pre kreiranja narudzbenice: " + idFakture);
	/*	var data = new FormData();
		
		data.append('idNarudzbenice',narNewId);
		data.append('brojNarudzbenice',narNewId);
		data.append('datumIzrade',narNewId);
		data.append('datumIsporuke',narNewId);
		data.append('aktivna',active);
		data.append('obrisano',false);
		data.append('faktura_rel',idFakture);
		data.append('kupac',1);
		data.append('user',1);
		data.append('preduzece','1');*/
		
		
	    var formData ={
		   		'idNarudzbenice' : narNewId,
	    		'brojNarudzbenice' : narNewId,
	    		'datumIzrade' : '2019-01-02',
	    		'datumIsporuke' : '2019-02-02' ,
	    		'aktivna' : active,
	    		'obrisano' : false,
	    		'fakturaRel' : idFakture,
	    		'kupac' : 1,
	    		'user' : 1,
	    		'preduzece' : '1'
	    		 
	    }
	    console.log("Data je: " +formData.faktura_rel);
	   
		$.ajax({
			type: 'PUT',
	        url: 'https://localhost:8081/api/narudzbenice/editNarudzbenica/'+narNewId,
	        headers:{Authorization:"Bearer " + token},
	        data: JSON.stringify(formData),
	        dataType: 'json',
			cache: false,
			processData: false,
			 contentType: 'application/json',
	        success: function (response) {
	        	if(response.aktivna == false){
	        		createFaktura(response.idNarudzbenice);
	        	}
	        	
	        	var narudzbenica = response;
	        	currentNarudzbenica = narudzbenica;
	        	console.log("Kreirana narudzbenica id" +currentNarudzbenica.idNarudzbenice)
	        	console.log(response.idNarudzbenice);
	       //	window.location.href = 'invoice.html?id='+idFakture;
	      //  	createSN(idN);
	     //   	populateSN(currentNarudzbenica,idN);
	        	
	    	}
		
		},
		
	);

}

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