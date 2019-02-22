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
var imeKupca;
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
				imeKupca1(narudzbenica.kupac)
				var table = $('#bodyTable');
				
				table.append('<tr id="'+narId+'"><td>'+narudzbenica.brojNarudzbenice+'</td><td>'+narudzbenica.datumIzrade+'</td>'+
						'<td>'+narudzbenica.datumIsporuke+'</td><td>'+narudzbenica.preduzece+'</td><td>'+imeKupca+'</td></tr>');
				
				
				
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
				
				imeKupca1(kupacId)
				console.log("jeee" +response.idNarudzbenice + "kupac id " + kupacId)
				$('#brNar').html(response.brojNarudzbenice);
				$('#datumIzr').html(response.datumIzrade);
				$('#datumIsp').html(response.datumIsporuke);
				$('#kupac').html(imeKupca)
				
				
				
				
			

			},
			error: function (jqXHR, textStatus, errorThrown) {
				if(jqXHR.status=="403"){
					alert("Error.");
				}

			}

	});
		

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

	
	
function deleteNar(){
	var idDel = localStorage.getItem("selectedId");
		$.ajax({
			url: 'https://localhost:8081/api/narudzbenice/softDeleteNarudzbenica/'+idDel,
			headers:{Authorization:"Bearer " + token},
			type: 'delete',
			success : function(response){
				alert("izbrisana naruzbenica" + idDel)
				/*var divSN = $('#selektovanaNarudzbenica');
				divSN.hide();*/
				loadListaNarudzbenica();
				
			},
			error: function (jqXHR, textStatus, errorThrown) {  
				alert(jqXHR.status);
			}
	    });
	}