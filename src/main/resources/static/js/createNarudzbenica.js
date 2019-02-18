var token= localStorage.getItem("token");
var idRobe = 0;
var idN = 0;
$(document).ready(function(){
	
	var currentNarudzbenica = null ;

//	generateSerial() 
});


function createNarudzbenica(){
	
   
	console.log("OTvorio se modal za pregled Narudzbenice")
	var date = currentDate();
	console.log(date);
	document.getElementById("addDatumIzrade").innerHTML = date;
	
	var date1 = document.getElementById('datumIsporuke').value;
	console.log("date1 je "+ date1)
	
	document.getElementById("addDatumIsporuke").innerHTML = date1;
	
	
    };
   
   
    function prikazRobe(){
    	var tempUrl = "https://localhost:8081/api/roba/getAllRobadeliteNoName/" ;
    	var x = document.getElementById("select1");
    	var y = x.options[x.selectedIndex].value;
		console.log("urlll" + tempUrl + "iddddd" + y);
		
		$.ajax({
			 
			method:'GET',
			url: tempUrl + y,
			headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			
			success: function(response){
				clearSelect2();
				console.log(response);
				var select = document.getElementById("select2");
				for(var i =0; i< response.length; i++){
					 var option = document.createElement("OPTION");
					 txt =  document.createTextNode(response[i].name);
					 option.appendChild(txt);
					 select2.insertBefore(option, select2.lastChild);
					 id = response[i].id;
					 console.log("id robe koji mi treba je " + id)
					
					 
				}
				
				
				 }
		
				
			},
		
			
			
		);
	};

function potvrdiN(){
		$('#dodajRobu').modal('toggle');
		var kolRobe = $('#kolRobe').val();
		var x = document.getElementById("select2");
	    var y = x.options[x.selectedIndex].value;
		var node = document.createElement("LABEL");
		var textnode = document.createTextNode(y+ "("+ kolRobe + ")" + ", ");
		node.appendChild(textnode);
		document.getElementById("nazivRobe").appendChild(node);
		
}
    
	
 function loadGrupaRobe(){
	 console.log("load robe aaaaaaaaa")
	 var idRobe = 0;
	 $.ajax({
			method:'GET',
			url: "https://localhost:8081/api/gruparobe/getGRdeliteNo/all",
			headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			success: function(response){
					clearSelect1();
					var select = document.getElementById("select1");
					
					for(var i =0; i< response.length; i++){
					 var option = document.createElement("OPTION");
					// id = document.createTextNode(response[i].id);
					 txt =  document.createTextNode(response[i].name);
				
					 option.appendChild(txt);
					 select.insertBefore(option, select.lastChild);
				
					 
					 }
					
			
				 }
				
				
				
			},
		
			
			
		);
	};
 function clearSelect1(){
	 var select = $('#select1');
	 select.empty();
 }
 function clearSelect2(){
	 var select = $('#select2');
	 select.empty();
 }			
 
 
 function generateSerial() {

	      var randomNumber;

	        randomNumber = Math.floor(Math.random() * 100);
	        
	    
	    document.getElementById('brNarudz').innerHTML = randomNumber;
	    
	}
 


 function createNarudzbenica(){
	 console.log("ko znasta je ovo")
	 	var kolRobe = $('#kolRobe').val();
		var x = document.getElementById("select2");
	    var roba = x.options[x.selectedIndex].value;
	    idN = 0;
	    var formData ={
	    		'brojNarudzbenice' : '1',
	    		'datumIzrade' : '2019-01-02',
	    		'datumIsporuke' : '2019-02-02' ,
	    		'aktivna' : false,
	    		'obrisano' : false,
	    		'faktura_rel' : null,
	    		'kupac' : 1,
	    		'user' : 1,
	    		'preduzece' : '1'
	    		 
	    }
	    console.log(formData)
	   
		$.ajax({
			type: 'POST',
	        url: 'https://localhost:8081/api/narudzbenice/addNarudzbenica',
	        headers:{Authorization:"Bearer " + token},
	        contentType: "application/json",
	        data: JSON.stringify(formData),


			dataType: 'json',
	        success: function (response) {
	        	idN = response.idNarudzbenice;
	        	var narudzbenica = response;
	        	currentNarudzbenica = narudzbenica;
	        	
	        	console.log("narddd" +currentNarudzbenica)
	        	console.log(response.idNarudzbenice);
	        	createSN(idN);
	     //   	populateSN(currentNarudzbenica,idN);
	        	
	    	}
		
		},
		
	);

};

function createSN(id){
	
	var kolRobe = $('#kolRobe').val();
	var x = document.getElementById("select2");
    var roba = x.options[x.selectedIndex].value;
	console.log(id);
	
	
	  var formData ={
			  	'naziv' : roba,
				'kolicina' : $('#kolRobe').val(),
				'jedinicaMere' : 'kg' ,
				'idNarudzbenice' : id ,
				'obrisano' : false
	    }

	  console.log(formData)
	  $.ajax({
			type: 'POST',
	        url: 'https://localhost:8081/api/narudzbenice/stavkeNarudzbenice/addStavkaNarudzbenice',
	        headers:{Authorization:"Bearer " + token},
	        contentType: "application/json",
	        data: JSON.stringify(formData),


			dataType: 'json',
	        success: function (response) {
	        	console.log("jeeeeeeeee" + response.idStavkeNarudzbenice)
	        	window.location.href = 'mojaNarudzbenica.html?id='+id;
	        //	populateStavkeNarudzbenice();
	        //	window.location.href = '/Videos/user.html?id=' + loggedInUser.id;
	        	
	    	}
		
		},
		
	);

};




/*function populateSN(id, narudzbenica){
	
	
	  var formData ={
			  	'naziv' : roba,
				'kolicina' : $('#kolRobe').val(),
				'jedinicaMere' : 'kg' ,
				'id_narudzbenice' : id ,
				'obrisano' : false
	    }
	  console.log(formData)
	  $.ajax({
			type: 'POST',
	        url: 'https://localhost:8081/api/narudzbenice/stavkeNarudzbenice/addStavkaNarudzbenice',
	        headers:{Authorization:"Bearer " + token},
	        contentType: "application/json",
	        data: JSON.stringify(formData),


			dataType: 'json',
	        success: function (response) {
	        	console.log("jeeeeeeeee" + response.idStavkeNarudzbenice)
	    //    	populateStavkeNarudzbenice();
	        	
	        	
	    	}
		
		},
		
	);

};
	*/