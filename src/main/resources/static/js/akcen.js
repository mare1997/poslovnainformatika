var cenId;
var token = localStorage.getItem("token");
$(document).ready(function(){
	var pgId = localStorage.getItem("pgId",pgId);
	var pId = localStorage.getItem("pId",pId);
	$.ajax({
	    url:'https://localhost:8081/api/cenovnik/getActiveCenovnik/'+pId+'/'+pgId,
	    headers:{Authorization:"Bearer " + token},
	    type:"GET",
	    dataType: 'json',
	    async:false,
	    crossDomain:true,
	    success: function (response) {
	    	cenId = response.id;
	    	console.log("cenId:"+ cenId);
	    	localStorage.setItem("akCenovnik",cenId);
	    	console.log(localStorage.getItem("akCenovnik"));

	    },error: function (jqXHR, textStatus, errorThrown) {
				alert("log error!!!");
	  }
	});
	
})

