

$(document).ready(function(){
	var name = window.location.search.slice(1).split('?')[0].split('=')[1];
	console.log(name)
	if(name == 'faktura'){
		loadFakture();
	}
	else if(name == 'narudzbenica'){
		loadNarudzbenice();
	}else if(name == 'otpremnica'){
		loadotpremnice();
	}else if(name == 'grupa'){
		loadGrupeRobe();
	}else if(name == 'kupac'){
		loadKupac();
	}

});
var token= localStorage.getItem("token");
var preduzeceId =localStorage.getItem("pId");
var poslovnaGod = localStorage.getItem("pgId");

function loadKupac(){
	
	var tableHeader = $('#istorijaHead');
	tableHeader.append('<tr><th>Ime</th><th>Adresa</th><th>JMBG/PID</th><th>Datum kreiranja</th><th>Obrisan</th></tr>');
	var url = new URL("https://localhost:8081/api/kupac/getInactive/all/"+preduzeceId+"/"+poslovnaGod);
	console.log(url);
	 $.ajax({
			method:'GET',
			url: url,
			headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			success: function(response){
					var table = $('#istorijaBody');
					for(var i=0;i<response.length;i++){
						kupac = response[i];
						console.log(kupac);
						table.append('<tr id="'+kupac.id+'">'+'<td>'+kupac.name+'</td>'+'<td>'+kupac.adresa+'</td>'+'<td>'+kupac.pib_jmbg+'</td>'+'<td>'+kupac.datum_kreiranja+'</td>'+'<td>'+kupac.obrisano+'</td></tr>');
					}
					
				
			},error: function (jqXHR, textStatus, errorThrown) {
				alert(textStatus);
	  }
	});
	 
	}
	


function loadGrupeRobe(){
	var tableHeader = $('#istorijaHead');
	tableHeader.append('<tr><th>Grupa</th><th>PDV</th></tr>');
	var url = new URL("https://localhost:8081/api/gruparobe/getGRdeliteYes/all/"+preduzeceId+"/"+poslovnaGod);
	console.log(url);
	 $.ajax({
			method:'GET',
			url: url,
			headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			success: function(response){
					var table = $('#istorijaBody');
					for(var i=0;i<response.length;i++){
						grupa = response[i];
						console.log(grupa);
						table.append('<tr id="'+grupa.id+'">'+'<td>'+grupa.name+'</td>'+'<td>'+grupa.pdv.name+'</td></tr>');
					}
					
				
			},error: function (jqXHR, textStatus, errorThrown) {
				alert(textStatus);
	  }
	});
}

function loadNarudzbenice(){
	var tableHeader = $('#istorijaHead');
	tableHeader.append('<tr><th>Br. narudzbenice</th><th>Datum izrade</th><th>Datum isporuke</th><th>Placena</th><th>Kupac</th></tr>');
	var url = new URL("https://localhost:8081/api/narudzbenice/all?page=0&size=10&posGodId="+poslovnaGod+"&preduzeceId="+preduzeceId);
	console.log(url);
	 $.ajax({
			method:'GET',
			url: url,
			headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			success: function(response){
					var table = $('#istorijaBody');
					console.log("cao")
					for(var i=0;i<response.length;i++){
						narudzbenica = response[i];
						console.log("cao")
						console.log(narudzbenica);
						table.append('<tr><td>'+narudzbenica.brojNarudzbenice+'</td><td>'+narudzbenica.datumIzrade+'</td>'+
								'<td>'+narudzbenica.datumIsporuke+'</td><td>'+narudzbenica.preduzece+'</td><td>'+narudzbenica.faktura_rel+'</td></tr>');
					}
					
				
			},error: function (jqXHR, textStatus, errorThrown) {
				alert(textStatus);
	  }
	});
}

function loadotpremnice(){
	var tableHeader = $('#istorijaHead');
	tableHeader.append('<tr><th>Br. fakture</th><th>Datum fakture</th><th>Iznos za placanje</th><th>Status fakture</th><th>Kupac</th></tr>');
	$.ajax({
		method:'GET',
		url: 'https://localhost:8081/api/otpremnice/all?page=0&size=10&posGodId='+poslovnaGod+"&preduzeceId="+preduzeceId,
		 headers:{Authorization:"Bearer " + token},
		dataType: 'json',
		cashe: false,
		success: function(response){
			console.log(response)
			for(var i=0; i<response.length; i++){
			otpremnica = response[i];
			otprId = otpremnica.idOtpremnice;
			
			var table = $('#istorijaBody');
			table.append('<tr id="'+otprId+'"><td>'+otprId+'</td><td>'+otpremnica.datumOtpremnice+'</td><td>'+otpremnica.datumIsporuke+'</td>'+
					'<td>'+otpremnica.primljenaRoba+'</td><td>'+otpremnica.kupacId+'</td></tr>');
			
			
				}
			
			 }
			
		},

	);
	
}

function loadFakture(){
	var tableHeader = $('#istorijaHead');
	tableHeader.append('<tr><th>Br. fakture</th><th>Datum fakture</th><th>Iznos za placanje</th><th>Status fakture</th><th>Kupac</th></tr>');
	
	 $.ajax({
			method:'GET',
			url: 'https://localhost:8081/api/fakture/all?page=0&size=10&posGodId='+poslovnaGod+"&preduzeceId="+preduzeceId,
			 headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			success: function(response){
				console.log(response)
				for(var i=0; i<response.length; i++){
				faktura = response[i];
				faktId = faktura.idFakture;
				console.log("idFakture" + faktId)
				var table = $('#istorijaBody');
				table.append('<tr id="'+faktId+'"><td>'+faktId+'</td><td>'+faktura.datumFakture+'</td><td>'+faktura.iznosZaPlacanje+'</td>'+
						'<td>'+faktura.statusFakture+'</td><td>'+faktura.kupac+'</td></tr>');
				
				
					}
				
				 }
				
			},
	
		);
}