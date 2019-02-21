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
	


