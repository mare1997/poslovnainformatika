var token= localStorage.getItem("token");
var idN = 0;
var stavka = null;
$(document).ready(function(){
	idN = window.location.search.slice(1).split('&')[0].split('=')[1];
	console.log("id jeee" + idN);
//	loadNarudzbenica(idN);
//	loadStavkeNarudzbenice(idN);
});

function loadNarudzbenica(id){
	var tempUrl = "https://localhost:8081/api/narudzbenice/active/"+id;
	$.ajax({
		url: tempUrl,
		headers:{Authorization:"Bearer " + token},
		type:'get',
		dataType: 'json',
		cashe: false,
		success: function(response){
			$('#brNarudz').html(id);
			var date = currentDate();
			$('#datumIzrade').html(date);
			
			}
          
		},
		
	);
	
}

/*//Radi path
function loadStavkeNarudzbenice(id){
	console.log("mm?")
	var tempUrl = "https://localhost:8081/api/narudzbenice/stavkeNarudzbenice/"+id;
	$.ajax({
		url: tempUrl,
		headers:{Authorization:"Bearer " + token},
		type:'get',
		dataType: 'json',
		cashe: false,
		success: function(response){
			console.log(response)
			for(var i=0; i<response.length; i++){
				stavka = response[i];
				$('#nazivRobe').append(stavka.naziv).append(stavka.kolicina).append(",");
				
				
				
				}
			
			
		}
    
	},
	
);
*/
}
	
function potvrdiNarudzbenicu(){
	createFaktura();
//	window.location.href = 'invoice.html?id='+idN;
}	

function createFaktura(){
	console.log("uhuu")
	var date = currentDate();
	  var formData ={
	    		'brojFakture' : null,
	    		'datumFakture' : date,
	    		'datumValute' : '2019-02-02' ,
	    		'osnovica' : 800,
	    		'ukupanPDV' : 500,
	    		'iznosZaPlacanje' : 900,
	    		'statusFakture' : "je",
	    		'narudzbeniceRel' : idN,
	    		'otpremnicaRel' : 1,
	    		'kupac' : idN,
	    		'user' : '1',
	    		'obrisano' : false,
	    		'preduzece' : 1
	    		 
	    }
	    console.log(formData)
	   
		$.ajax({
			type: 'POST',
	        url: 'https://localhost:8081/api/fakture/addFaktura',
	        headers:{Authorization:"Bearer " + token},
	        contentType: "application/json",
	        data: JSON.stringify(formData),


			dataType: 'json',
	        success: function (response) {
	        	console.log(response);
	        	idFakture = response.idFakture;
	        	console.log(idFakture);
	        	loadFakture(idFakture);
	        	
	        	
	        	
	        },
			error: function (jqXHR, textStatus, errorThrown) {  
				alert(textStatus);
			}
	    });
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
			console.log(response.idFakture)
			createSF(idFakture);
			window.location.href = 'invoice.html?id='+id;
			}
          
		},
		
	);
	
}

function createSF(id){
	console.log("mozd moze")
	  var formData ={
			  	'kolicina' : stavka.kolicina,
				'jedinicnaCena' : stavka.cena,
				'rabat' : 8000 ,
				'osnovicaZaPDV' : 85 ,
				'procenatPDV' : 2,
				'iznosPDV' : 560,
				'iznosStavke' : 9562,
				'idFakture' : id,
				'robaUslugaId' : 1,
				'jedinicaMere' : stavka.jedinicaMere,
				'obrisano' : false
	    }
	

	  console.log(formData)
	  $.ajax({
			type: 'POST',
	        url: 'https://localhost:8081/api/fakture/stavkeFakture/addStavkaFakture',
	        headers:{Authorization:"Bearer " + token},
	        contentType: "application/json",
	        data: JSON.stringify(formData),


			dataType: 'json',
	        success: function (response) {
	        	console.log("da li?")
	        	console.log(response)
	        //	window.location.href = 'mojaNarudzbenica.html?id='+id;
	        //	populateStavkeNarudzbenice();
	        //	window.location.href = '/Videos/user.html?id=' + loggedInUser.id;
	        	
	    	}
		
		},
		
	);

};