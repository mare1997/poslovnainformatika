var token= localStorage.getItem("token");
var idF = 0;
$(document).ready(function(){
	idF = window.location.search.slice(1).split('&')[0].split('=')[1];
	
	populateFaktura();
	loadStavkeFakture(idF,stavka);
});

// popuniti fakturu 
function populateFaktura(){
	console.log("populate faktura")
	$('#brFakture').html(idF);
	var date = currentDate();
	$('#datumFakture').html(date);
	
	
}


function loadStavkeFakture(id, stavka){
	console.log("loadStavkeFakture")
	var tempUrl = "https://localhost:8081/api/fakture/stavkeFakture/"+id;
	$.ajax({
		url: tempUrl,
		 headers:{Authorization:"Bearer " + token},
		type:'get',
		dataType: 'json',
		cashe: false,
		success: function(response){
			console.log("load sf" + stavka)
			for(var i=0; i<response.length; i++){
				stavka = response[i];
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

				

		 }

		
	},

);
};


	
	
	
	


