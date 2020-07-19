function fokusiraj() {
	document.getElementsByTagName("input")[0].focus();
}

$(document).on("submit", "form", function (event) {

	var user = localStorage.getItem("user");

	event.preventDefault();
	var projekcijaJSON = JSON.stringify({
		"stariDatumProjekcije": $("#stariDatumProjekcije").val(),
		"datumProjekcije": $("#datumProjekcije").val()
	});

	$.ajax({
		type: "POST",
		url: "http://localhost:8080/projekcija/izmena/" + user, // Gadja specificni url za metodu kontrolera
		// dataType: "json", // Povratna vrednost, ne moze sa ovim da radi ako je void metoda kontrolera
		contentType: "application/json", // Podaci koje saljemo
		data: projekcijaJSON, // Saljemo objekat koji smo napravili, on je taj data JSON
		success: function () {
			alert("Projekcija je izmenjena!");
			window.location.href = "index-menadzer.html"; // Redirect
		},
		error: function (data) {
			alert("Greska u izmeni projekcije.");
			console.log("ERROR: ", data);
		}
	});
});