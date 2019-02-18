var token= localStorage.getItem("token");
$(document).ready(function() {
	loadPreduzece();
  loadPG();
	var auth = localStorage.getItem("authority")
	console.log(auth);
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
        preduzece = response[i];

          select.append(
            '<option value="'+preduzece.id+'">'+preduzece.name+'</option>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}

function loadPG(){
  $.ajax({
    url:'https://localhost:8081/api/poslovnagodina/all',
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
	var selectP = document.getElementById('preduzeca');
	var selectPG = document.getElementById('pg');
	var pgId= selectPG.options[selectPG.selectedIndex].value;
	var pId= selectP.options[selectP.selectedIndex].value;
	console.log("id pg: " + pgId + "id pred: " + pId);
	localStorage.setItem("pgId",pgId);
	localStorage.setItem("pId",pId);
	window.location.replace("mainPage.html");
}
