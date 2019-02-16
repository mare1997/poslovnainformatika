var idRobe = 0;
$(document).ready(function(){
	
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
    	var tempUrl = "http://localhost:8080/api/roba/getAllRobadeliteNoName/" ;
    	var x = document.getElementById("select1");
    	var y = x.options[x.selectedIndex].value;
		console.log("urlll" + tempUrl + "iddddd" + y);
		$.ajax({
			 
			method:'GET',
			url: tempUrl + y,
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
			url: "http://localhost:8080/api/gruparobe/getGRdeliteNo/all",
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
 


 function createStavkaNarudzbenice(){
	 console.log("ko znasta je ovo")
	 	var kolRobe = $('#kolRobe').val();
		var x = document.getElementById("select2");
	    var roba = x.options[x.selectedIndex].value;
	    
		var data = new FormData();
		data.append('naziv',roba);
		data.append('kolicina',kolRobe);
		data.append('jedinicaMere', "blabla");
		data.append('obrisano', false);
		console.log(roba + kolRobe)
		console.log(data)
		$.ajax({
			type: 'POST',
	        url: 'http://localhost:8080/api/narudzbenice/stavkeNarudzbenice/addStavkaNarudzbenice"',
	        contentType: false,
	        data: data,
			cache: false,
			processData: false,
			dataType: 'json',
	        success: function (response) {
		
	        	console.log("ko zna sta je ovo" + response)
	    	}
		
		},
		
	);

};


 
	