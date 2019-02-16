$(document).ready(function(){
	
	
});

function formatDate(tempDate) {
	var date = new Date(tempDate);
	var monthNames = [
	  "January", "February", "March",
	  "April", "May", "June", "July",
	  "August", "September", "October",
	  "November", "December"
	];

	var day = date.getDate();
	var monthIndex = date.getMonth();
	var year = date.getFullYear();

	return day + '. ' + monthNames[monthIndex] + ' of ' + year+'.';
	//return day + '. ' + parseInt(monthIndex+1) + '. ' + year+'.';
}


function currentDate(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!

	var yyyy = today.getFullYear();
	if(dd<10){
	    dd='0'+dd;
	} 
	if(mm<10){
	    mm='0'+mm;
	} 
	var today = yyyy+"-"+mm+"-"+dd;
	
	return today;
}