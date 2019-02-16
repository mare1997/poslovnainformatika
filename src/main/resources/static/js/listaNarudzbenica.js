$(document).ready(function(){
	loadListaNarudzbenica()
	 
});

function loadListaNarudzbenica(){
	 console.log("load lista narudzbenica")
	 $.ajax({
			method:'GET',
			url: "http://localhost:8080/api/narudzbenice/all",
			dataType: 'json',
			cashe: false,
			success: function(response){
				console.log(response)
				for(var i=0; i<response.length; i++){
				narudzbenica = response[i];
				var table = $('#bodyTable');
				table.append('<tr><td>'+narudzbenica.brojNarudzbenice+'</td><td>'+narudzbenica.datumIzrade+'</td>'+
						'<td>'+narudzbenica.datumIsporuke+'</td><td>'+narudzbenica.preduzece+'</td><td>'+narudzbenica.faktura_rel+'</td></tr>');
				
				}
				
				 }

				
			},
	
		);
	};
	
	

	
/*var table = document.getElementsByTagName("table")[0];
var tbody = table.getElementsByTagName("tbody")[0];
tbody.onclick = function (e) {
    e = e || window.event;
    var data = [];
    var target = e.srcElement || e.target;
    while (target && target.nodeName !== "TR") {
        target = target.parentNode;
    }
    if (target) {
        var cells = target.getElementsByTagName("td");
        for (var i = 0; i < cells.length; i++) {
            data.push(cells[i].innerHTML);
        }
    }
    alert(data);
};*/
	
