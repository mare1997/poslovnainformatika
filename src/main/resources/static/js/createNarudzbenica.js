var token= localStorage.getItem("token");
var idRobe = 0;
var idN = 0;
var narNewId=0;
var idFakture = 0;

var currentNarudzbenica = null;
var idKupac = 0;
var pId = null;
//var stavka = null;
var currentUserId = null;
var currentUsrName = null;

$(document).ready(function(){
	
	currentUserId = localStorage.getItem("id");
	currentUserUsrName = localStorage.getItem("username");
	createDefaultNar();
	loadKupci();

	
	
$('#saveN').submit(function(e){
		e.preventDefault();
		
		 console.log("Kreiranje narudzbenice")
		
		    /*	if(idRobe == '0'){
				alert("nece moci ove noci")
			}*/
		   
		    
		    datumIsporuke = document.getElementById("datumIsporuke").value;
		
		
			var date = currentDate();
			
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
			
		
			
		    var formData ={
			   		'idNarudzbenice' : narNewId,
		    		'brojNarudzbenice' : narNewId,
		    		'datumIzrade' : date,
		    		'datumIsporuke' : datumIsporuke ,
		    		'aktivna' : active,
		    		'obrisano' : false,
		    		'fakturaRel' : idFakture,
		    		'kupac' : idKupac,
		    		'user' : currentUserId,
		    		'preduzece' : '1'
		    		 
		    }
		    console.log("Data je "+ formData + "datumIzrade:" + date + "datumIsporuke" + datumIsporuke+ "id kupaaaac: "+ idKupac);
		   
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
		     
		        },
				
				
			});
			
		});

});
function createFaktura(id){
	console.log("Kreiranje fakture")
	var preduzeceId = 0;
	preduzeceId=localStorage.getItem("pId");
	var idF=0;
	var date = currentDate();
	  var formData ={
	    		'brojFakture' : '5',
	    		'datumFakture' : date,
	    		'datumValute' : '2019-02-02' ,
	    		'osnovica' : 800,
	    		'ukupanPDV' : 500,
	    		'iznosZaPlacanje' : 900,
	    		'statusFakture' : "je",
	    		'narudzbeniceRel' : id,
	    		'otpremnicaRel' : 1,
	    		'kupac' : idKupac,
	    		'user' : currentUserId,
	    		'obrisano' : false,
	    		'preduzece' : preduzeceId
	    		 
	    }
	    console.log("Faktura formData" + formData.preduzece)
	   
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
	        		stavkaF = response[i];
	        		createSF(stavkaF,idF);
	        	}
	        	
	        	
	       // 	
	       // 	window.location.href = 'https://localhost:8081/listaNarudzbenica.html';
	        	
	    	}
		
		},
		
	);

}
function createSF(stavka,idF){
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
	        	loadStavkeFakture(idF);
	        
	        	
	    	}
		
		},
		
	);
	
}
function loadStavkeFakture(id){
	console.log("loadStavkeFakture" + id)
	var tempUrl = "https://localhost:8081/api/fakture/stavkeFakture/"+id;
	console.log("url je " + tempUrl)
	$.ajax({
		url: tempUrl,
		 headers:{Authorization:"Bearer " + token},
		type:'get',
		dataType: 'json',
		cashe: false,
		success: function(response){
			console.log("load sf" + response)
			for(var i=0; i<response.length; i++){
				stavka = response[i];
				console.log("br stavki" + response.length)
				console.log("stavka" + stavka)
				var table = $('#tableBody');
				table.append('<tr><td>'+stavka.kolicina+'</td><td>'+stavka.cena+'</td>'+
						'<td>'+stavka.rabat+'</td><td>'+stavka.osnovicaZaPDV+'</td><td>'+stavka.procenatPDV+'</td>'+
						'<td>'+stavka.iznosPDV+'</td><td>'+stavka.iznosStavke+'</td></tr>');
				
				}
			
				/*$('#kolicina').append(stavka.kolicina);
				$('#cena').append(stavka.cena);
				$('#rabat').append(stavka.rabat);
				$('#osnPDV').append(stavka.osnovicaZaPDV);
				$('#procPDV').append(stavka.procenatPDV);
				$('#iznosPDV').append(stavka.iznosPDV);
				$('#iznosStavke').append(stavka.iznosStavke);*/

				
		//	window.location.href = 'https://localhost:8081/listaNarudzbenica.html';
		 }

		
	},

);
};

function loadKupci(){
	 $.ajax({
			method:'GET',
			url: "https://localhost:8081/api/kupac/all",
			headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			success: function(response){
				
					clearSelect1();
					var select = $('#select3');
					
					for(var i =0; i< response.length; i++){
					 kupac = response[i];
					 idKupac = kupac.id;
					 select.append(
							 '<option value="'+idKupac+'">'+kupac.name+'</option>'
							 )
					
					 }
			},error: function (jqXHR, textStatus, errorThrown) {
				alert(textStatus);
	  }
	});
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
				var divRobe = $('#divRobe');
				divRobe.show();
				clearSelect2();
				console.log(response);
				/*divKol = $('#divKol');
				divKol.show();*/
				var select = document.getElementById("select2");
				for(var i =0; i< response.length; i++){
					 var option = document.createElement("OPTION");
					 txt =  document.createTextNode(response[i].name);
					 option.appendChild(txt);
					 select2.insertBefore(option, select2.lastChild);
					 idRobe = response[i].id;
					 console.log("id robe koji mi treba je " + idRobe)
					
					 
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
	 var divRobe = $('#divRobe');
		divRobe.hide();
	 console.log("load robe aaaaaaaaa")
	 var idRobe = 0;
	// $('#select2').hide();
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
	var preduzeceId = 0;
	preduzeceId=localStorage.getItem("pId");
	var date = currentDate();
	document.getElementById("datumIsporuke").min= date ;
	console.log("Id preduzeca je " + preduzeceId)
	
	var formData ={
	   		'datumIzrade' : date,
    		'aktivna' : true,
    		'obrisano' : false,
    		'user' : currentUserId,
    		'preduzece' : preduzeceId
    		 
    }
	console.log("Preduzece jee" + formData.preduzece)
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



/* function potvrdiNarudzbenicu(){
	 console.log("Kreiranje narudzbenice")
	 	var kolRobe = $('#kolRobe').val();
		var x = document.getElementById("select2");
	    var roba = x.options[x.selectedIndex].value;

	    datumIsporuke = document.getElementById("datumIsporuke").value;
	
		console.log("datum je: " + datumIsporuke);
		
		var date = currentDate();
		
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

		
		
	    var formData ={
		   		'idNarudzbenice' : narNewId,
	    		'brojNarudzbenice' : narNewId,
	    		'datumIzrade' : date,
	    		'datumIsporuke' : datumIsporuke ,
	    		'aktivna' : active,
	    		'obrisano' : false,
	    		'fakturaRel' : idFakture,
	    		'kupac' : 1,
	    		'user' : currentUserId,
	    		'preduzece' : '1'
	    		 
	    }
	    console.log("Data je "+ formData + "datumIzrade:" + date + "datumIsporuke" + datumIsporuke);
	   
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

}*/

function createStavkeNarudzbenice(id){
	console.log("idRobe je :" + idRobe)
	console.log("Kreiranje stavke narudzbenice" + id)
	var kolRobe = $('#kolRobe').val();
	var x = document.getElementById("select2");
    var roba = x.options[x.selectedIndex].value;
    var jm = $('#jedinMere').find(":selected").text();
	
	  var formData ={
			  	'naziv' : roba,
				'kolicina' : $('#kolRobe').val(),
				'jedinicaMere' : jm ,
				'idNarudzbenice' : id ,
				'robaUslugaId': idRobe,
				'obrisano' : false
	    }

	  console.log(formData + "Jedinica mere" + formData.jedinicaMere)
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
				nazivRobe.append('<label>'+stavka.naziv+'('+stavka.kolicina+''+stavka.jedinicaMere+'</label>'+')');
			//	document.getElementById('nazivRobe').append.innerHTML = stavka.naziv;
				nazivRobe.append('<button  type="button" title="remove" id="deleteRobaBtn" class="removeRoba btn btn-sm" onClick="deleteRoba()" value="+'+stavka.id+'"></button>')
				
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


