function fokusiraj() {
	document.getElementsByTagName("input")[0].focus();
}

$(document).on("submit", "form", function (event) {

	// var user = localStorage.getItem("user");

	event.preventDefault();
	var salaJSON = JSON.stringify({
		"kapacitet": $("#kapacitet").val(),
		"oznakaSale": $("#oznakaSale").val(),
		"nazivBioskopa": $("#nazivBioskopa").val()
	});

	$.ajax({
		type: "POST",
		url: "http://localhost:8080/menadzer/sale/dodavanje", // Gadja specificni url za metodu kontrolera
		// dataType: "json", // Povratna vrednost, ne moze sa ovim da radi ako je void metoda kontrolera
		contentType: "application/json", // Podaci koje saljemo
		data: salaJSON, // Saljemo objekat koji smo napravili, on je taj data JSON
		success: function () {
			alert("Sala '" + $("#oznakaSale").val() + "' je napravljena!");
			window.location.href = "index-menadzer.html"; // Redirect
		},
		error: function (data) {
			alert("Greska u kreiranju sale.");
			console.log("ERROR: ", data);
		}
	});
});