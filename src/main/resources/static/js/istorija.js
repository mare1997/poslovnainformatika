$(document).ready(function(){
	var name = window.location.search.slice(1).split('?')[0].split('=')[1];
	console.log(name)
	if(name == 'faktura'){
		loadFakture();
	}
	else if(name == 'narudzbenica'){
		loadNarudzbenice();
	}else if(name == 'otpremnica'){
		loadotpremnice();
	}else if(name == 'grupa'){
		loadGrupeRobe();
	}else if(name == 'kupac'){
		loadKupac();
	}else if(name == 'prevoznik'){
		loadPrevoznici();
	}

});

