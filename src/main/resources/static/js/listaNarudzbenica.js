var token= localStorage.getItem("token");

$(document).ready(function() {
	loadListaNarudzbenica();
	var divSN = $('#selektovanaNarudzbenica');
	divSN.hide();
	$(document).on("click", ".bodyLista tr", function(e) {
		//var name = this.attr("name");
		var selectedId = this.id;
		
		console.log(selectedId);
		
		divSN.show();
		prikazSelNar(selectedId);
		
	//	document.getElementById("obrisiBtn").value=selectedId ;
		
		localStorage.setItem("selectedId", selectedId);
    
});
});

var preduzeceId =localStorage.getItem("pId")
var poslovnaGod = localStorage.getItem("pgId");
function loadListaNarudzbenica(){
	 console.log("load lista narudzbenica")
	 var url = new URL("https://localhost:8081/api/narudzbenice/all/finished?page=0&size=10&posGodId="+poslovnaGod+"&preduzeceId="+preduzeceId);
	 $.ajax({
			method:'GET',
			url: url,
			 headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			success: function(response){
				console.log(response)
				for(var i=0; i<response.length; i++){
				narudzbenica = response[i];
				narId = narudzbenica.idNarudzbenice;
				var table = $('#bodyTable');
				table.append('<tr id="'+narId+'"><td>'+narudzbenica.brojNarudzbenice+'</td><td>'+narudzbenica.datumIzrade+'</td>'+
						'<td>'+narudzbenica.datumIsporuke+'</td><td>'+narudzbenica.preduzece+'</td><td>'+narudzbenica.faktura_rel+'</td></tr>');
				
				
				
				}
				
				 }

				
			},
	
		);
	};
	
	

function prikazSelNar(id){

	$.ajax({
		url:'https://localhost:8081/api/narudzbenice/stavkeNarudzbenice/'+id,
		headers:{Authorization:"Bearer " + token},
		type: 'GET',
		dataType:'json',
		async: false,
		crossDomain: true,
		success:function(response){
			console.log("hoces li ucis")
			for(var i=0; i<response.length; i++){
				stavkeNar = response[i];
				console.log(stavkeNar)
				
				nazivLabel = $('#nazivRobe');
				kolicinaLabel = $('#kol');
				jMLabel = $('#jedM');
				naziv = stavkeNar.naziv;
				kolicina = stavkeNar.kolicina;
				jedinicaMere = stavkeNar.jedinicaMere;
				console.log("naziv" + naziv + "jm" + jedinicaMere + "kolicina" + kolicina);
				
				nazivLabel.append('<label class="text-muted">'+naziv+'</label>&nbsp;&nbsp;');
				kolicinaLabel.append('<label class="text-muted">'+kolicina+'</label><label class="text-muted">'+jedinicaMere+'</label>');
			//	jMLabel.append('<label class="text-muted">'+jedinicaMere+'</label>');
				
		//		kolicinaLabel.append(jMLabel);
				nazivLabel.append(kolicinaLabel);
				
				
				prikazRobeLabel=$('#prikazRobeLabel');
				prikazRobeLabel.append('<label>Izabrana roba i kolicina:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="text-muted">'+naziv+'</label>&nbsp;&nbsp;<label class="text-muted">'+kolicina+'</label><label class="text-muted">'+jedinicaMere+'</label>;</p>')
				
			/*	
				$('#nazivRobe').html(naziv);
				$('#kol').html(kolicina);
				$('#jedM').html(jedinicaMere);*/
			}
		},
	

	});

	$.ajax({
		url:'https://localhost:8081/api/narudzbenice/'+id,
		headers:{Authorization:"Bearer " + token},
		type: 'GET',
		dataType:'json',
		async: false,
		crossDomain: true,
		success:function(response){
			
			kupacId = response.kupac;
			console.log("jeee" +response.idNarudzbenice)
			$('#brNar').html(response.brojNarudzbenice);
			$('#datumIzr').html(response.datumIzrade);
			$('#datumIsp').html(response.datumIsporuke);
			
			
			$.ajax({
				url:'https://localhost:8081/api/kupac/'+kupacId,
				headers:{Authorization:"Bearer " + token},
				type: 'GET',
				dataType:'json',
				async: false,
				crossDomain: true,
				success:function(response){
					imeKupca = response.name
					$('#kupac').html(imeKupca);
					
				},
				error: function (jqXHR, textStatus, errorThrown) {
					if(jqXHR.status=="403"){
						alert("Error.");
					}

				}

				});
			
			
		

		},
		error: function (jqXHR, textStatus, errorThrown) {
			if(jqXHR.status=="403"){
				alert("Error.");
			}

		}

});
	

}



function addNarudzbenica(){

}


function deleteNarudzbenica(){
	idDel=localStorage.getItem("selectedId");
	$.ajax({
		url: 'https://localhost:8081/api/narudzbenice/softDeleteNarudzbenica/'+idDel,
		headers:{Authorization:"Bearer " + token},
		type: 'PUT',
		success : function(response){
			alert("izbrisana naruzbenica")
			var divSN = $('#selektovanaNarudzbenica');
			divSN.hide();
			
		},
		error: function (jqXHR, textStatus, errorThrown) {  
			alert(jqXHR.status);
		}
    });
}

