$(document).ready(function() {
	var pg = localStorage.getItem("pgId");
	
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

	  
	
	
});