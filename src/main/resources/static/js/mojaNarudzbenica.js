var token= localStorage.getItem("token");
var idN = 0;

$(document).ready(function(){
	idN = window.location.search.slice(1).split('&')[0].split('=')[1];
	console.log("id jeee" + idN);
	loadNarudzbenica(idN);
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

//Ne radi path
function loadStavkeNarudzbenice(id){
	var tempUrl = "https://localhost:8081/api/narudzbenice/stavkeNarudzbenice/"+id;
	$.ajax({
		url: tempUrl,
		  headers:{Authorization:"Bearer " + token},
		type:'get',
		dataType: 'json',
		cashe: false,
		success: function(response){
			
		}
    
	},
	
);

}
	
function potvrdiNarudzbenicu(){
	createFaktura();
//	window.location.href = 'invoice.html?id='+idN;
}	

function createFaktura(){
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
	    		'kupac' : '1',
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
	        	
	        	
	        	
	    	}
		
		},
		
	);

};

function loadFakture(id) {
	
	var tempUrl = "https://localhost:8081/api/fakture/"+id;
	$.ajax({
		url: tempUrl,
		  headers:{Authorization:"Bearer " + token},
		type:'get',
		dataType: 'json',
		cashe: false,
		success: function(response){
			
			window.location.href = 'invoice.html?id='+id;
			}
          
		},
		
	);
	
}