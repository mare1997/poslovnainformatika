function zakljucivanjePG(){
	var pg = localStorage.getItem("pgId");
	var token = localStorage.getItem("token");
	var poslovnaGodina;
	  $.ajax({
		    url:'https://localhost:8081/api/poslovnagodina/' + pg,
		    headers:{Authorization:"Bearer " + token},
		    type:"GET",
		    dataType: 'json',
		    crossDomain:true,
		    async:false,
		    success: function (response) {
		    	console.log(response);
		    	poslovnaGodina = response;
		    	
		    },error: function (jqXHR, textStatus, errorThrown) {
					alert("read error!!!");
		  }
		});
	  var today;
	  function currentDate(){
			var today = new Date();
			var dd = today.getDate();
			var mm = today.getMonth()+1; //January is 0!

			var yyyy = today.getFullYear();
			if(dd<10){
			    dd='0'+dd;
			} 
			if(mm<10){
			    mm='0'+mm;
			} 
			var today = yyyy+"-"+mm+"-"+dd;
			
			return today;
		}
	  today = currentDate();
	  console.log(today);
	  
	  var data = {'datumKraj': today,
			  		'zavrsena': true}
	  
	  console.log(data);
	  $.ajax({
		    url:'https://localhost:8081/api/poslovnagodina/zakljucena/' + pg,
		    headers:{Authorization:"Bearer " + token},
		    type:"PUT",
		    data:JSON.stringify(data),
		    dataType: 'json',
		    contentType: 'application/json',
		    crossDomain:true,
		    async:false,
		    success: function (response) {
		    	console.log(response);
		    	poslovnaGodina = response;
		    	location.reload();
		    },error: function (jqXHR, textStatus, errorThrown) {
					alert("zakljucena error!!!");
		  }
		});
}