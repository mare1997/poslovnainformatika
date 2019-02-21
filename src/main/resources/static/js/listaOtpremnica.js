var token= localStorage.getItem("token");
$(document).ready(function() {
	loadListaOtpremnica();

	$(document).on("dblclick", ".otpremnicaBody tr", function(e) {
		var selectedId = this.id;
		console.log("selekted" + selectedId)
		localStorage.setItem("selectedId", selectedId);
		window.location.href = 'otpremnica.html?id=' + selectedId;
    
});
	
});

function loadListaOtpremnica(){
	 console.log("load lista otpremnica")
	 
	 $.ajax({
			method:'GET',
			url: 'https://localhost:8081/api/otpremnice/all?page=0&size=10',
			 headers:{Authorization:"Bearer " + token},
			dataType: 'json',
			cashe: false,
			success: function(response){
				console.log(response)
				for(var i=0; i<response.length; i++){
				otpremnica = response[i];
				otprId = otpremnica.idOtpremnice;
				console.log("idOtpremnice" + otprId)
				var table = $('#bodyTable');
				table.append('<tr id="'+otprId+'"><td>'+otprId+'</td><td>'+otpremnica.datumOtpremnice+'</td><td>'+otpremnica.datumIsporuke+'</td>'+
						'<td>'+otpremnica.primljenaRoba+'</td><td>'+otpremnica.kupacId+'</td></tr>');
				
				
					}
				
				 }
				
			},
	
		);
	};
	
	

