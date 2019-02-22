
$(document).ready(function() {
	var token = localStorage.getItem("token");
	var role = localStorage.getItem("authority");
	console.log(token);
	if(token == null){
		window.stop();
		document.body.innerHTML = '';
		window.location.replace("login.html");
		//alert("You are not logged in, please log in!");
		
	}
	if(role == "ADMIN"){
		//alert("im admin");
		$('#navigacijaUser').hide();
	}
	if(role=="REGULAR"){
		//$('#zaUsera').show();
		$('#navigacijaAdmin').hide();
		$('#navigacijaUser').show();
	}
	
});
function logout(){
	localStorage.removeItem("token");
	localStorage.removeItem("pId");
	localStorage.removeItem("pgId");
	localStorage.removeItem("currentUserId");
	localStorage.removeItem("authority");
	window.location.replace("login.html");
	
}


/*<ul id="zaUsera">
<li><a href="mainPage.html">Home</a></li>

				<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Narudzbenice <span class="caret"></span>
			</a>

				<ul class="dropdown-menu">
					<li><a href="listaNarudzbenica.html">Lista zavrsenih narudzbenica</a></li>
					<li><a href="listaActiveNarudzbenica.html">Lista aktivnih narudzbenica</a></li>
					<li><a href="createNarudzbenica.html">Kreiraj narudzbenicu</a></li>


				</ul></li>
					<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Fakture <span class="caret"></span>
			</a>

				<ul class="dropdown-menu">
					<li><a href="listaFaktura.html">Lista faktura</a></li>

				</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Otpremnice<span class="caret"></span>
			</a>

				<ul class="dropdown-menu">
					<li><a href="listaOtrpemnica.html">Lista otpremnica</a></li>

				</ul></li>
</ul>*/