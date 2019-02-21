var token= localStorage.getItem("token");
var idRobe = 0;
var cenaRobe= "";
var idN = 0;
var narNewId=0;
var idFakture = 0;
var idOtpremnice = 0;
var currentNarudzbenica = null;
var idKupac = 0;
var pId = null;
//var stavka = null;
var currentUserId = null;
var currentUsrName = null;
var preduzeceId =localStorage.getItem("pId");
var poslovnaGod = localStorage.getItem("pgId");
$(document).ready(function(){
	
	currentUserId = localStorage.getItem("id");
	currentUserUsrName = localStorage.getItem("username");
	
	createDefaultNar();
	loadKupci();
	loadPreduzece();
	
	
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
		    		'preduzece' : preduzeceId
		    		
		    }
		    console.log("Data je "+ formData.fakturaRel + "datumIzrade:" + date + "datumIsporuke" + datumIsporuke+ "id kupaaaac: "+ idKupac);
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
		        	/*if(response.aktivna == false){
		        		createFaktura(response.idNarudzbenice);
		        	}
		        	*/
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
	        	createStavkeFakture(response.narudzbeniceRel,idF);
	        	
	        	
	        },
			error: function (jqXHR, textStatus, errorThrown) {  
				alert(textStatus);
			}
	    });
	 
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
	        		robaUslugaId = stavkaF.robaUslugaId;
	        		console.log("Kreirane stavke fakture sa id robom su" + stavkaF.robaUslugaId)
	        		createSF(stavkaF,idF,robaUslugaId);
	        		
	        	}
	        	
	        	
	       // 	
	       // 	window.location.href = 'https://localhost:8081/listaNarudzbenica.html';
	        	
	    	}
		
		},
		
	);

}
function createSF(stavka,idF,robaUslugaId){
	console.log("Dodavanje stavki faktura")
	
	$.ajax({
	url:'https://localhost:8081/api/roba/getRobadeliteNo/'+robaUslugaId,
	headers:{Authorization:"Bearer " + token},
	type: 'GET',
	dataType:'json',
	async: false,
	crossDomain: true,
	success:function(response){
		console.log(response);
		robaObject = response;
		robaObjectCena = robaObject.cena;
		robaObjectName = robaObject.name;
		robaObjectGrupaPDVId = robaObject.grupa.pdv.id;
		//trenutno nema polja za unosenje rabat procenta
		//rabatProcenat = robaObject.rabat;
		rabatProcenat = 20;
		console.log("RobaObjectGrupaPDVID mi je" + robaObjectGrupaPDVId)
		console.log("RobaObject mi je" + robaObjectCena)
		console.log("RobaObjectName mi je" + robaObjectName)
		
	},
	error: function (jqXHR, textStatus, errorThrown) {
		if(jqXHR.status=="403"){
			alert("Error.");
		}

	}

	});
	var cena;
	$.ajax({
		method:'GET',
		url: "https://localhost:8081/api/stavkacenovnika/getSCdeleteNo/"+robaObjectCena,
		headers:{Authorization:"Bearer " + token},
		dataType: 'json',
		cashe: false,
		success: function(response){
				cena = response.cena;
				console.log("cena stavke je " + cena)
				
			}
		
	,error: function (jqXHR, textStatus, errorThrown) {
		alert("read error!!!");
}
	
});
	$.ajax({
		url:'https://localhost:8081/api/stopapdv/getStopaByPdv/'+robaObjectGrupaPDVId,
		headers:{Authorization:"Bearer " + token},
		type: 'GET',
		dataType:'json',
		async: false,
		crossDomain: true,
		success:function(response){
			for(var i=0; i<response.length; i++){
				stopaPDVObject = response[i];
				procenatStopaPDV = stopaPDVObject.procenat;
				console.log("Procenat stopePDV je " + procenatStopaPDV);
				
				
				
			}
		},
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Error.");
			}

		}

		});
		var vrednost = cena * stavka.kolicina;
		//rabatprocenat se unosi pri dodavanju stavke narudzbenice
		var rabat = vrednost * rabatProcenat / 100;//rabatProcenat ti je globalna promenjiva
		var osnovicaPDV =  cena - rabat;
		var iznosPDV =  osnovicaPDV * procenatStopaPDV/100;
		var iznosStavke = vrednost- rabat + iznosPDV;
		var formData ={
			  	'kolicina' : stavka.kolicina,
				'jedinicnaCena' : cena,
				'rabat' : rabat ,
				'osnovicaZaPDV' : osnovicaPDV ,
				'procenatPDV' : procenatStopaPDV,
				'iznosPDV' : iznosPDV,
				'iznosStavke' : iznosStavke,
				'idFakture' : idF,
				'robaUslugaId' : stavka.robaUslugaId,
				'jedinicaMere' : stavka.jedinicaMere,
				'obrisano' : false
	    }
	

	  console.log("Jedinicna cena" + formData.jedinicnaCena + formData.osnovicaZaPDV)
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
	        	console.log("Cena robe jeee" + cenaRobe)
	        	
	        
	        	
	    	}
		
		},
		
	);
	
}


function loadKupci(){
	
var url = new URL("https://localhost:8081/api/kupac/getActive/all/"+preduzeceId+"/"+poslovnaGod);
	 $.ajax({
			method:'GET',
			url: url,
			headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			success: function(response){
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
			
				var select = $('#select2');
				for(var i =0; i< response.length; i++){
					 var option = document.createElement("OPTION");
					 txt =  document.createTextNode(response[i].name);
					 option.appendChild(txt);
					 select2.insertBefore(option, select2.lastChild);
					 idRobe = response[i].id;
					 cenaRobe = response[i].cena;
				}
					
					 
					 console.log("id robe koji mi treba je " + idRobe)
					 console.log("Cena robe koji mi treba je " + cenaRobe)
					 
				 }
		
			},
			
		);
	};
	
	
function potvrdiN(){
	createStavkeNarudzbenice(narNewId);
}
    
	
 function loadGrupaRobe(){
	 var divRobe = $('#divRobe');
		divRobe.hide();
	 console.log("load robe aaaaaaaaa")
	var url = new URL("https://localhost:8081/api/gruparobe/getGRdeliteNo/all/"+preduzeceId+"/"+poslovnaGod);
	 $.ajax({
			method:'GET',
			url: url,
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



function createStavkeNarudzbenice(id){
	console.log("idRobe je :" + idRobe)
	console.log("cenaRobe je: " + cenaRobe)
	
	console.log("Kreiranje stavke narudzbenice" + id)
	var kolRobe = $('#kolRobe').val();
	var x = document.getElementById("select2");
    var roba = x.options[x.selectedIndex].value;
    var jm = $('#jedinMere').find(":selected").text();
	// dodaj rabat i stavi da ti bude globalna promenjiva(treba ti za izracunavanje fakture)
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
	console.log("cenaRobe je: " + cenaRobe)
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
				console.log("id stavke" + stavka.idStavkeNarudzbenice)
				stavkaIDN = stavka.idStavkeNarudzbenice
				var nazivRobe = $('#nazivRobe');
				console.log("naziv robe u labeli je" + stavka.naziv)
			//Ispraviti   
				nazivRobe.append('<label id="'+stavkaIDN+'">'+stavka.naziv+'('+stavka.kolicina+''+stavka.jedinicaMere+')<button  type="button" title="Ukloni robu" id="deleteRobaBtn" class="removeRoba btn btn-sm" onClick="deleteRoba('+stavkaIDN+')" value="'+stavkaIDN+'"></button></label>');
			//	document.getElementById('nazivRobe').append.innerHTML = stavka.naziv;
				nazivRobe.append('')
				
				}
				
			
			
		}
    
	},
	
);

}
function deleteRoba(id){
	$.ajax({
		url: "https://localhost:8081/api/narudzbenice/stavkeNarudzbenice/hardDeleteStavkaNarudzbenice/"+id,
		headers:{Authorization:"Bearer " + token},
		type: 'DELETE',
		success : function(response){
		//	alert("izbrisana robica")
			document.getElementById(id).style.display = "none";
			$('#deleteRobaBtn').remove();
			
		},
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(jqXHR.status);
		}
    });
}


function clearNazivRobe(){
	var nazivRobe = $('#nazivRobe');
	nazivRobe.empty();
}


function loadPreduzece(){
	var preduzeceId = 0;
	preduzeceId=localStorage.getItem("pId");
	$.ajax({
		url:'https://localhost:8081/api/preduzece/'+preduzeceId,
		headers:{Authorization:"Bearer " + token},
		type: 'GET',
		dataType:'json',
		async: false,
		crossDomain: true,
		success:function(response){
			email = response.email;
			name = response.name;
			telefon = response.telefon;
			adresa = response.adresa;
			
			console.log("preduzece" + email + name + telefon + adresa);
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Error.");
			}

		}

		});
	
	
}

function loadKupac(){
	var url = new URL("https://localhost:8081/api/kupac/getActive/all?id="+idKupac+"&posGodId="+poslovnaGod+"&preduzeceId="+preduzeceId);
	
	$.ajax({
		url: url,
		headers:{Authorization:"Bearer " + token},
		type: 'GET',
		dataType:'json',
		async: false,
		crossDomain: true,
		success:function(response){
			name = response.name;
			adresa = response.adresa;
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Error.");
			}

		}

		});
	
	
}

function createOtpremnica(idF){
	console.log("Kreiranje otpremnice")
	var preduzeceId = 0;
	preduzeceId=localStorage.getItem("pId");
	var date = currentDate();
	
	var tempUrl = "https://localhost:8081/api/fakture/"+idF;
	$.ajax({
		url: tempUrl,
		headers:{Authorization:"Bearer " + token},
		type:'get',
		dataType: 'json',
		cashe: false,
		success: function(response){
			console.log("Id fakture je" + response.idFakture);
			
			datumOtpremnice = response.datumFakture;
			console.log("datum otpremnice je" + datumOtpremnice);
			kupacId = response.kupac;
			userId = response.user;
			datumIsporuke = document.getElementById("datumIsporuke").value;
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Error.");
			}

		}

		});
	  var formData ={
	    		
	    		'datumOtpremnice' : "",
	    		'primljenaRoba' : true ,
	    		'datumIsporuke' : datumIsporuke,
	    		'prevoznikId' : "1",
	    		'kupacId' : 1,
	    		'user' : currentUserId,
	    		'preduzeceId' : preduzeceId,
	    		'fakturaRel' : idF,
	    		'obrisano' : false
	    		
	    		 
	    }
	    console.log("Otpremnica formData" + formData.datumIsporuke)
	   
		$.ajax({
			type: 'POST',
	        url: 'https://localhost:8081/api/otpremnice/addOtpremnica',
	        headers:{Authorization:"Bearer " + token},
	        contentType: "application/json",
	        data: JSON.stringify(formData),
	        async:false,

			dataType: 'json',
	        success: function (response) {
	        	console.log(response);
	        	idOtpremnice = response.idOtpremnice
	        	loadOtpremnice(idOtpremnice);
	        	
	        	
	        },
			error: function (jqXHR, textStatus, errorThrown) {  
				alert(textStatus);
			}
	    });

}


function loadOtpremnice(id){
	console.log("load Otpremnice")
	var tempUrl = "https://localhost:8081/api/otpremnice/"+id;
	$.ajax({
		url: tempUrl,
		headers:{Authorization:"Bearer " + token},
		type:'get',
		dataType: 'json',
		cashe: false,
		
		success: function(response){
			console.log("Id otrpemnice je" + response.idOtpremnice);
			
		//	createStavkeOtpremnice(response.fakturaRel,id);
			
			}
          
		},
		
	);
}


function createStavkeOtpremnice(idFakture, idOtpremnice){
	console.log("Create stavke Otpremnice")
	console.log("Ucitavanje stavki fakture")
	var tempUrl = "https://localhost:8081/api/fakture/stavkeFakture/"+idFakture;
	console.log("url je " + tempUrl)
	$.ajax({
		url: tempUrl,
		 headers:{Authorization:"Bearer " + token},
		type:'get',
		dataType: 'json',
		cashe: false,
		success: function(response){
			console.log("load stavki otpremnice" + response)
			for(var i=0; i<response.length; i++){
				stavkaF = response[i];
				cenaO = stavkaF.jedinicnaCena;
				kolicinaO = stavkaF.kolicina;
				jedinicaMereO = stavkaF.jedinicaMere;
				robaUslugaIdO = stavkaF.robaUslugaId;
				console.log("rbuO" + robaUslugaIdO)
				
				
				}
			console.log("Dodavanje robe")
			$.ajax({
				url:'https://localhost:8081/api/roba/getRobadeliteNo/'+robaUslugaIdO,
				headers:{Authorization:"Bearer " + token},
				type: 'GET',
				dataType:'json',
				async: false,
				crossDomain: true,
				success:function(response){
					console.log(response);
					robaObjectName = response.name;
					
				},

				});
			var formData ={
		    		'naziv' : robaObjectName,
		    		'cena' : cenaO,
		    		'isporucenaKolicina' : kolicinaO ,
		    		'napomena' : "",
		    		'idOtpremnice' : idOtpremnice,
		    		'jedinicaMere' : jedinicaMereO,
		    		'robaUslugaId' : robaUslugaIdO,
		    		'obrisano' : false
		    }
			
			$.ajax({
				type: 'POST',
		        url: 'https://localhost:8081/api/otpremnice/stavkeOtpremnice/addStavkaOtpremnice',
		        headers:{Authorization:"Bearer " + token},
		        contentType: "application/json",
		        data: JSON.stringify(formData),


				dataType: 'json',
		        success: function (response) {
		        console.log(response);
		        	
		    	}
			
			},
			
		);
		 }
	},

);
	
	

	
	
	
	
	
	
	
}