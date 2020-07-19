$("#tabela-bioskopa-menadzer").ready(function () {
	var user = localStorage.getItem("user");
	$.ajax({
		type: "GET", // Dobija informacije od servera u obliku JSON-a
		url: "http://localhost:8080/bioskop/lista/" + user, // URL Kontrolera, gadja getBioskopi()
		dataType: "json", // Povratna vrednost
		success: function (data) { // data je JSON iz kontrolera (ResponseEntity), odnosno lista DTO-ova, pa kroz nju moze da se iterira
			console.log("SUCCESS : ", data); // Ispisuje celu listu u konzolu
			for (i = 0; i < data.length; i++) {
				// Konkateniraju se informacije u string koji ce se apendovati na tabelu (vidi dole)
				var row = "<tr>";
				row += "<td>" + data[i]['idBioskop'] + "</td>"; // Ne id
				row += "<td>" + data[i]['naziv'] + "</td>";
				row += "<td>" + data[i]['adresa'] + "</td>";
				row += "<td>" + data[i]['brojTelefonaCentrale'] + "</td>";
				row += "<td>" + data[i]['email'] + "</td>";
				row += "</tr>";
				$('#tabela-bioskopa-menadzer').append(row);
				// "row" je string HTML koda koji se dodaje na tabelu koja ima ovaj id 
			}
		},
		error: function (data) {
			console.log("ERROR : ", data);
		}
	});

});





































$(document).ready(function () {
	$('iframe').hide();
	$('#easteregg').click(function () {
		$("h1").hide();
		$("a").hide();
		$("h3").hide();
		$('#ftn-web-cinema-header').hide();
		$(".row").hide();
		$('body').attr('style', 'background-color: black !important');
		$('iframe').show();
		$('iframe').attr('style', 'border-width: 0px');
		$('iframe').attr('src', 'https://rickrolled.fr/rickroll.mp4');
	});
});