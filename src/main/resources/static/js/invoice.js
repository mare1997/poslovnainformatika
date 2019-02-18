var idF = 0;
$(document).ready(function(){
	idF = window.location.search.slice(1).split('&')[0].split('=')[1];
	
	populateFaktura();
	
});

// popuniti fakturu 
function populateFaktura(){
	
	$('#brFakture').html(idF);
	var date = currentDate();
	$('#datumFakture').html(date);
	
}

