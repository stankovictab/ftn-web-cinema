function fokusiraj() {
	document.getElementsByTagName("input")[0].focus();
}

$(document).on("submit", "form", function (event) {

	event.preventDefault();
	var salaJSON = JSON.stringify({
		"oznakaSale": $("#oznakaSale").val()
	});

	$.ajax({
		type: "POST",
		url: "http://localhost:8080/menadzer/sale/brisanje", // Gadja specificni url za metodu kontrolera
		// dataType: "json", // Povratna vrednost, ne moze sa ovim da radi ako je void metoda kontrolera
		contentType: "application/json", // Podaci koje saljemo
		data: salaJSON, // Saljemo objekat koji smo napravili, on je taj data JSON
		success: function () {
			alert("Sala '" + $("#oznakaSale").val() + "' je obrisana!");
			window.location.href = "index-menadzer.html"; // Redirect
		},
		error: function (data) {
			alert("Greska u brisanju sale.\nProveri unos oznake sale.");
			console.log("ERROR: ", data);
		}
	});
});