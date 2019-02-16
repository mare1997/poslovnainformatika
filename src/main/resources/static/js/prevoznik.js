$(document).ready(function() {
	loadPrevoznik();
});
var token= localStorage.getItem("token");
function loadPrevoznik(){
  $.ajax({
    url:'https://localhost:8081/api/prevoznik/all',
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var table = $('#prevoznikBody');
      for (var i=0; i<response.length; i++){
        prevoznik = response[i];

          table.append(
            '<tr>'+'<td>'+prevoznik.name+'</td></tr>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}
