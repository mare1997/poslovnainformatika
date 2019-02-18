var token= localStorage.getItem("token");

$(document).ready(function() {
	loadPreduzece();
  loadPG();
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
            '<option>'+preduzece.name+'</option>'
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
            '<option>'+pg.godina+'</option>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}
