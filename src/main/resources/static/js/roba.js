$(document).ready(function() {
	loadRoba();
});
var token= localStorage.getItem("token");

function loadRoba(){
  $.ajax({
    url:'https://localhost:8081/api/roba/getAllRobadeliteNo/all',
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var table = $('#robaBody');
      for (var i=0; i<response.length; i++){
        roba = response[i];

          table.append(
            '<tr>'+'<td>'+roba.name+'</td><td>'+roba.grupa+'</td><td>'+roba.jedinica_mere+'</td></tr>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}
