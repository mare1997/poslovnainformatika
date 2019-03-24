var token= localStorage.getItem("token");

$(document).ready(function() {
	var auth = localStorage.getItem("authority");
	console.log(auth);
//	var cenL = $("#cenLabel");
//	cenL.hide();
//	var akSelect = $("#akCenovnik");
//	akSelect.hide();
//	var ok = $("#okButton1");
//	ok.hide();
	loadPreduzece();
	loadPG1();
	//loadAkCen1();
	
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

/*function loadAkCen(){
	var posG =localStorage.getItem("pgId");
	var prId =localStorage.getItem("pId");
	  $.ajax({
	    url:'https://localhost:8081/api/cenovnik/getCenovnikdeleteNo/all/'+prId+'/'+posG,
	    headers:{Authorization:"Bearer " + token},
	    type:"GET",
	    dataType: 'json',
	    crossDomain:true,
	    success: function (response) {
	      var select = $('#akCenovnik');
	      for (var i=0; i<response.length; i++){
	        cen = response[i];

	          select.append(
	            '<option value="'+cen.id+'">'+cen.name+'</option>'
	          )

	      }
	    },error: function (jqXHR, textStatus, errorThrown) {
				alert("read error!!!");
	  }
	});
}

*/




function proceed(){
	
	var selectPG = document.getElementById('pg');
	var pgId= selectPG.options[selectPG.selectedIndex].value;
	var selectP = document.getElementById("preduzeca");
	var pId= selectP.options[selectP.selectedIndex].value;
	//var selectAC = document.getElementById("akCenovnik");
	//var ac= selectAC.options[selectAC.selectedIndex].value;
	console.log("id pg: " + pgId + "id pred: " + pId);
	localStorage.setItem("pgId",pgId);
	localStorage.setItem("pId",pId);
	//tb = $("#okButton");
	//tb.hide();
	//var cenL = $("#cenLabel");
	//cenL.show();
	//var akSelect = $("#akCenovnik");
	//akSelect.show();
	//var ok = $("#okButton1");
	//ok.show();
	//loadAkCen();
	//localStorage.setItem("akCen",ac);
	//window.location.replace("mainPage.html");
	window.location.replace("mainPage.html");
}

/*function proceed1(){
	var selectAC = document.getElementById("akCenovnik");
	var ac= selectAC.options[selectAC.selectedIndex].value;
	localStorage.setItem("akCenovnik",ac);
	console.log("cen id =" +ac);
	window.location.replace("mainPage.html");
	
}
*/
