var token= localStorage.getItem("token");
var idF = 0;
var name;
var stopa;
$(document).ready(function(){
	idF = window.location.search.slice(1).split('?')[0].split('=')[1];
	console.log("idF" + idF)
	loadFakture(idF)
	loadStavkeFakture(idF)
//	populateFaktura();
//	loadStavkeFakture(idF,cena,name);
});

// popuniti fakturu 
function populateFaktura(){
	console.log("populate faktura")
	$('#brFakture').html(idF);
	var date = currentDate();
	$('#datumFakture').html(date);
	
	
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
			
			$('#brFakture').html(id);
		//	var date = currentDate();
			$('#datumFakture').html(date);

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
				console.log("stavka" + stavka.jedinicnaCena)
				console.log("roba id: " + stavka.robaUslugaId)
				getProcenatStopaPDV(stavka.robaUslugaId);
				getNameRobe(stavka.robaUslugaId);
				var table = $('#tableBody');
				//dodaj jos stavki u tabelu
				table.append('<tr><td>'+name+'</td><td>'+stavka.jedinicaMere+'</td> <td>'+stavka.kolicina+'</td><td>'+stavka.jedinicnaCena+'</td>'+
						'<td>'+(stavka.kolicina * stavka.jedinicnaCena)+'</td><td>'+((rabat*100)/(stavka.kolicina * stavka.jedinicnaCena))+'</td><td>'+stavka.rabat+'</td><td>'+stavka.osnovicaZaPDV+'</td><td>'+stopa+'</td>'+
						'<td>'+stavka.iznosPDV+'</td><td>'+stavka.iznosStavke+'</td></tr>');
				
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
			console.log("id za stopu: "+ response.grupa.pdv.id)
			getProcenatStopaPDV(response.grupa.pdv.id);
		}

		
	},
	);
	
}
function getProcenatStopaPDV(id){
	
	$.ajax({
		url:"https://localhost:8081/api/stopapdv/getSpdvdeleteNo/"+id ,
		 headers:{Authorization:"Bearer " + token},
		type:'get',
		dataType: 'json',
		cashe: false,
		async: false,
		success: function(response){
			console.log("stopa je: "+ response.procenat)
			stopa = response.procenat;
			console.log("stopa je: "+ stopa)
		}

		
	},
	);
	
}
function loadKupac(){
	$.ajax({
		url:'https://localhost:8081/api/kupac/'+idKupac,
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
/*function loadStavkeFakture(id){
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
			
				$('#kolicina').append(stavka.kolicina);
				$('#cena').append(stavka.cena);
				$('#rabat').append(stavka.rabat);
				$('#osnPDV').append(stavka.osnovicaZaPDV);
				$('#procPDV').append(stavka.procenatPDV);
				$('#iznosPDV').append(stavka.iznosPDV);
				$('#iznosStavke').append(stavka.iznosStavke);

				

		 }

		
	},

);
};*/
/*function loadStavkeFakture(id, cena, name){
	
	console.log("loadStavkeFakture" + id)
	console.log("cenaRobe je: " + cena)
	console.log("ime robe je: " + name)
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
				console.log("stavka" + stavka.jedinicnaCena)
				var table = $('#tableBody');
				table.append('<tr><td>Sandra</td><td>'+stavka.kolicina+'</td><td>'+stavka.jedinicnaCena+'</td>'+
						'<td>'+stavka.rabat+'</td><td>'+stavka.osnovicaZaPDV+'</td><td>'+stavka.procenatPDV+'</td>'+
						'<td>'+stavka.iznosPDV+'</td><td>'+stavka.iznosStavke+'</td></tr>');
				
				}
			
				$('#kolicina').append(stavka.kolicina);
				$('#cena').append(stavka.cena);
				$('#rabat').append(stavka.rabat);
				$('#osnPDV').append(stavka.osnovicaZaPDV);
				$('#procPDV').append(stavka.procenatPDV);
				$('#iznosPDV').append(stavka.iznosPDV);
				$('#iznosStavke').append(stavka.iznosStavke);

				
		//	window.location.href = 'https://localhost:8081/listaNarudzbenica.html';
		 }

		
	},

);
};*/

	
	
	
	


