$(document).ready(function() {
	loadCen();
});
var token= localStorage.getItem("token");

function loadCen(){
  $.ajax({
    url:'https://localhost:8081/api/cenovnik/getCenovnikdeleteNo/all',
    headers:{Authorization:"Bearer " + token},
    type:"GET",
    dataType: 'json',
    crossDomain:true,
    success: function (response) {
      var table = $('#cenBody');
      for (var i=0; i<response.length; i++){
        cen = response[i];

          table.append(
            '<tr><td>'+cen.name+'</td></tr>'
          )

      }
    },error: function (jqXHR, textStatus, errorThrown) {
			alert("read error!!!");
  }
});
}
