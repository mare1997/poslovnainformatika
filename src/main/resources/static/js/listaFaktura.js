var token= localStorage.getItem("token");
$(document).ready(function() {
	loadListaFaktura();

	$(document).on("dblclick", ".fakturaBody tr", function(e) {
		var selectedId = this.id;
		console.log("selekted" + selectedId)
		localStorage.setItem("selectedId", selectedId);
		window.location.href = 'invoice.html?id=' + selectedId;
    
});
	$(document).on("click", ".fakturaBody tr", function(e) {
		var selectedId = this.id;
		$(this).addClass("highlight");
		console.log("selekted" + selectedId)
	/*	localStorage.setItem("selectedId", selectedId);
		window.location.href = 'invoice.html?id=' + selectedId;*/
    
});
	
});
var preduzeceId =localStorage.getItem("pId")
var poslovnaGod = localStorage.getItem("pgId");
var brojNarudzbenice;
var brojOtpremnice;
var imeKupca;
function loadListaFaktura(){
	 console.log("load lista faktura")
	 var url = new URL("https://localhost:8081/api/fakture/all?page=0&size=10&posGodId="+poslovnaGod+"&preduzeceId="+preduzeceId);
	 $.ajax({
			method:'GET',
			url: url,
			 headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			success: function(response){
				console.log(response)
				for(var i=0; i<response.length; i++){
				faktura = response[i];
				faktId = faktura.idFakture;
				narudzbenicaBroj(faktura.narudzbeniceRel);
				otpremnicaBroj(faktura.otpremnicaRel)
				imeKupca1(faktura.kupac)
				console.log("idFakture" + faktId)
				var table = $('#bodyTable');
				table.append('<tr id="'+faktId+'"><td>'+faktId+'</td><td>'+faktura.datumFakture+'</td><td>'+faktura.datumValute+'</td>'+
						'<td>'+faktura.iznosZaPlacanje+'</td>'+
						'<td>'+brojNarudzbenice+'</td><td>'+brojOtpremnice+'</td><td>'+imeKupca+'</td></tr>');
				
				
					}
				
				 }
				
			},
	
		);
	};
	
	function imeKupca1(id){
		var url = new URL("https://localhost:8081/api/kupac/getActive/"+id+"/"+preduzeceId+"/"+poslovnaGod);
		$.ajax({
			method:'GET',
			url: url,
			 headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			async : false,
			success: function(response){
				imeKupca = response.name;
				
				 }
				
			},
		
		);
		
		
		
	}

function narudzbenicaBroj(id){
	
	$.ajax({
		method:'GET',
		url: "https://localhost:8081/api/narudzbenice/active/"+id,
		 headers:{Authorization:"Bearer " + token},
		dataType: 'json',
		cashe: false,
		async : false,
		success: function(response){
			console.log("narudz broj " + response.brojNarudzbenice)
			brojNarudzbenice = response.brojNarudzbenice;
			
			 }
			
		},
	
	);
	
}

function otpremnicaBroj(id){
	
	$.ajax({
		method:'GET',
		url: "https://localhost:8081/api/otpremnice/active/"+id,
		 headers:{Authorization:"Bearer " + token},
		dataType: 'json',
		cashe: false,
		async : false,
		success: function(response){
			console.log("narudz broj " + response.brojOtpremnice)
			brojOtpremnice = response.brojOtpremnice;
			
			 }
			
		},
	
	);
	
}
