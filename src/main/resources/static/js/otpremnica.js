var token= localStorage.getItem("token");
var idO = 0;
var name;
var stopa;
$(document).ready(function(){
	idO = window.location.search.slice(1).split('?')[0].split('=')[1];
	console.log("idFO" + idO);
	loadOtpremnice(idO);
	loadStavkeOtpremnice(idO);
	loadPreduzece();
	
//	populateFaktura();
//	loadStavkeFakture(idF,cena,name);
});
var preduzeceId =localStorage.getItem("pId")
var poslovnaGod = localStorage.getItem("pgId");

function loadOtpremnice(id) {
	console.log("loadOtpremnice")
	var tempUrl = "https://localhost:8081/api/otpremnice/"+id;
	$.ajax({
		url: tempUrl,
		headers:{Authorization:"Bearer " + token},
		type:'get',
		dataType: 'json',
		cashe: false,
		
		success: function(response){
			console.log("Id otpremnice je" + response.idOtpremnice);
			
			
			$('#brOtrpemnice').html(response.idOtpremnice);
			$('#datumOtrpemnice').html(response.datumOtpremnice);
			$('#datumIsporuke').html(response.datumIsporuke);
			loadKupac(response.kupacId);
			loadPrevoznik(response.prevoznikId);
			console.log("kupacId optremnice je: " + response.kupacId)
			ukupnaCena(response.fakturaRel)
			}
          
		},
		
	);
	
}
function loadStavkeOtpremnice(id){
	
	console.log("loadStavkeOtpremnice" + id)
	
	var tempUrl = "https://localhost:8081/api/otpremnice/stavkeOtpremnice/"+id;
	console.log("url je " + tempUrl)
	$.ajax({
		url: tempUrl,
		 headers:{Authorization:"Bearer " + token},
		type:'get',
		dataType: 'json',
		cashe: false,
		success: function(response){
			for(var i=0; i<response.length; i++){
				stavka = response[i];
				console.log("response otpremnice" + stavka.napomena);
				console.log("br stavki" + response.length)
				console.log("stavka" + stavka.cena)
				console.log("roba id: " + stavka.isporucenaKolicina)
			//	getProcenatStopaPDV(stavka.robaUslugaId);
				getNameRobe(stavka.robaUslugaId);
				
				
				var table = $('#tableBody');
				//dodaj jos stavki u tabelu
				table.append('<tr><td style="text-align:left;">'+name+'</td><td style="text-align:center;">'+stavka.cena+'</td><td style="text-align:center;">'+stavka.isporucenaKolicina+'</td>'+
						'<td style="text-align:center;">'+stavka.jedinicaMere+'</td><td style="text-align:center;">'+stavka.napomena+'</td></tr>');
				
				}
			
			
		 }

		
	},

);
};

function getNameRobe(id){
	
	$.ajax({
		url:"https://localhost:8081/api/roba/getRobadeliteNo/"+id ,
		 headers:{Authorization:"Bearer " + token},
		type:'get',
		dataType: 'json',
		cashe: false,
		success: function(response){
			name = response.name;
			console.log("ime robe je " + name)
	//		console.log("id za stopu: "+ response.grupa.pdv.id)
		//	getProcenatStopaPDV(response.grupa.pdv.id);
		}

		
	},
	);
	
}
/*function getProcenatStopaPDV(id){
	
	$.ajax({
		url:"https://localhost:8081/api/stopapdv/getSpdvdeleteNo/"+id ,
		 headers:{Authorization:"Bearer " + token},
		type:'get',
		dataType: 'json',
		cashe: false,
		success: function(response){
			console.log("stopa je: "+ response.procenat)
			stopa = response.procenat;
			console.log("stopa je: "+ stopa)
		}

		
	},
	);
	
}*/

function loadKupac(id){
	var url = new URL("https://localhost:8081/api/kupac/getActive/"+id+"/"+preduzeceId+"/"+poslovnaGod);
	$.ajax({
		method:'GET',
		url: url,
		 headers:{Authorization:"Bearer " + token},
		dataType: 'json',
		cashe: false,
		async : false,
		success: function(response){
			$('#imeKupca').html(response.name);
			$('#jmbg').html(response.pib_jmbg);
			$('#adresa').html(response.adresa);
			
			 }
			
		},
	
	);
	
	
	
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
			$('#preduzeceEmail').html(response.email);
			$('#preduzeceName').html(response.name);
			$('#preduzeceTelefon').html(response.telefon);
			$('#preduzeceAdresa').html(response.adresa);
			
			
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Error.");
			}

		}

		});
	
	
}

function loadPrevoznik(prevoznikId){
	$.ajax({
		url:'https://localhost:8081/api/prevoznik/'+prevoznikId,
		headers:{Authorization:"Bearer " + token},
		type: 'GET',
		dataType:'json',
		async: false,
		crossDomain: true,
		success:function(response){
			$('#prevoznik').html(response.name);
			
			
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Error.");
			}

		}

		});
	
	
}

function ukupnaCena(idF){
	$.ajax({
		url:'https://localhost:8081/api/fakture/active/'+idF,
		headers:{Authorization:"Bearer " + token},
		type: 'GET',
		dataType:'json',
		async: false,
		crossDomain: true,
		success:function(response){
			$('#uc').html(response.iznosZaPlacanje);
			
			
			
		},
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Error.");
			}

		}

		});
}
