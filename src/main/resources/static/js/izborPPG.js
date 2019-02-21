var token= localStorage.getItem("token");

$(document).ready(function() {
	var auth = localStorage.getItem("authority");
	console.log(auth);
	loadPreduzece();
	loadPG1();
	
});

function loadPreduzece(){
  $.ajax({
    url:'https://localhost:8081/api/preduzece/all',
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var select = $('#preduzeca');
      for (var i=0; i<response.length; i++){
    	  pr = response[i];
    	  select.append(
    	            '<option value="'+pr.id+'" >'+pr.name+'</option>');
    	  
      }
      
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}

function loadPG(){
	
	var pred = document.getElementById("preduzeca");
	
	var status= pred.options[pred.selectedIndex].value;
	
	var pg = document.getElementById("pg");
	
	pg.options.length = 0;
	
	
	console.log(status);
  $.ajax({
    url:'https://localhost:8081/api/poslovnagodina/all/'+ status,
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var select = $('#pg');
      for (var i=0; i<response.length; i++){
        pg = response[i];

          select.append(
            '<option value="'+pg.id+'">'+pg.godina+'</option>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}


function loadPG1(){

	console.log(status);
  $.ajax({
    url:'https://localhost:8081/api/poslovnagodina/all/'+ 1,
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var select = $('#pg');
      for (var i=0; i<response.length; i++){
        pg = response[i];

          select.append(
            '<option value="'+pg.id+'">'+pg.godina+'</option>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}



function proceed(){
	
	var selectPG = document.getElementById('pg');
	var pgId= selectPG.options[selectPG.selectedIndex].value;
	var selectP = document.getElementById("preduzeca");
	var pId= selectP.options[selectP.selectedIndex].value;
	console.log("id pg: " + pgId + "id pred: " + pId);
	localStorage.setItem("pgId",pgId);
	localStorage.setItem("pId",pId);
	window.location.replace("mainPage.html");
}

