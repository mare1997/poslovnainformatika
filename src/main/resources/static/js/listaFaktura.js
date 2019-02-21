var token= localStorage.getItem("token");
$(document).ready(function() {
	loadListaFaktura();

	$(document).on("dblclick", ".fakturaBody tr", function(e) {
		var selectedId = this.id;
		console.log("selekted" + selectedId)
		localStorage.setItem("selectedId", selectedId);
		window.location.href = 'invoice.html?id=' + selectedId;
    
});
	
});

function loadListaFaktura(){
	 console.log("load lista faktura")
	 
	 $.ajax({
			method:'GET',
			url: 'https://localhost:8081/api/fakture/all?page=0&size=10',
			 headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			success: function(response){
				console.log(response)
				for(var i=0; i<response.length; i++){
				faktura = response[i];
				faktId = faktura.idFakture;
				console.log("idFakture" + faktId)
				var table = $('#bodyTable');
				table.append('<tr id="'+faktId+'"><td>'+faktId+'</td><td>'+faktura.datumFakture+'</td><td>'+faktura.iznosZaPlacanje+'</td>'+
						'<td>'+faktura.statusFakture+'</td><td>'+faktura.kupac+'</td></tr>');
				
				
					}
				
				 }
				
			},
	
		);
	};
	
	

