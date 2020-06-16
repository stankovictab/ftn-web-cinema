// Fokus na prvo input polje, radi na load stranice, pa se poziva u body na onload atribut (moze i AJAX)
function fokusiraj() {
	document.getElementsByTagName("input")[0].focus();
}

// Pravljenje bioskopa za u bazu
$(document).on("submit", "form", function (event) {
	event.preventDefault();
	var bioskopJSON = JSON.stringify({
		"naziv": $("#naziv").val(),
		"adresa": $("#adresa").val(),
		"brojTelefonaCentrale": $("#brojTelefonaCentrale").val(),
		"email": $("#email").val(),
		"brojsala": $("#brojsala").val(),
		"menadzer-dropdown": $("#menadzer-dropdown").val()
	});

	$.ajax({
		type: "POST", // HTTP metoda je POST jer saljemo informacije serveru
		url: "http://localhost:8080/bioskop", // Gadja specificni url za metodu kontrolera
		dataType: "json", // Povratna vrednost
		contentType: "application/json", // Podaci koje saljemo
		data: bioskopJSON, // Saljemo objekat koji smo napravili, on je taj data JSON
		success: function () {
			alert("Bioskop '" + $("#naziv").val() + "' je napravljen!");
			window.location.href = "index.html"; // Redirect
		},
		error: function (data) {
			alert("Greska u pravljenju bioskopa.");
		}
	});
});