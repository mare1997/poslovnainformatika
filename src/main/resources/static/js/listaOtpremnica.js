var token= localStorage.getItem("token");
$(document).ready(function() {
	loadListaOtpremnica();

	$(document).on("dblclick", ".otpremnicaBody tr", function(e) {
		var selectedId = this.id;
		console.log("selekted" + selectedId)
		localStorage.setItem("selectedId", selectedId);
		window.location.href = 'otpremnica.html?id=' + selectedId;
    
});
	
});
var preduzeceId =localStorage.getItem("pId")
var poslovnaGod = localStorage.getItem("pgId");
var prevoznik;
var imeKupca;
function loadListaOtpremnica(){
	
	 console.log("load lista otpremnica")
	 var url = new URL("https://localhost:8081/api/otpremnice/all?page=0&size=10&posGodId="+poslovnaGod+"&preduzeceId="+preduzeceId);
	 $.ajax({
			method:'GET',
			url: url,
			 headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			success: function(response){
				console.log(response)
				for(var i=0; i<response.length; i++){
				otpremnica = response[i];
				prevoznikNaziv(otpremnica.prevoznikId);
				console.log("u fun0"+prevoznik)
				imeKupca1(otpremnica.kupacId);
				otprId = otpremnica.idOtpremnice;
				console.log("idOtpremnice" + otprId)
				var table = $('#bodyTable');
				table.append('<tr id="'+otprId+'"><td>'+otprId+'</td><td>'+otpremnica.datumOtpremnice+'</td><td>'+otpremnica.datumIsporuke+'</td>'+
						'<td>'+otpremnica.primljenaRoba+'</td><td>'+imeKupca+'</td><td>'+prevoznik+'</td></tr>');
				
				
					}
				
				 }
				
			},
	
		);
	};
	

	function prevoznikNaziv(id){
		$.ajax({
			method:'GET',
			url: "https://localhost:8081/api/prevoznik/"+id,
			 headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			async : false,
			success: function(response){
				console.log("response" + response.name)
				prevoznik = response.name;
				console.log("orevoznik" + prevoznik)
				
				 }
				
			},
		
		);

}

	
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