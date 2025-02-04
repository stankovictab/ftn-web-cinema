// Fokus na prvo input polje, radi na load stranice, pa se poziva u body na onload atribut (moze i AJAX)
function fokusiraj() {
	document.getElementsByTagName("input")[0].focus();
}

$(document).on("submit", "form", function (event) {
	event.preventDefault();
	var izmenaJSON = JSON.stringify({
		"stariNaziv": $("#starinaziv").val(),
		"naziv": $("#novinaziv").val() // Mora lowercase
	});

	$.ajax({
		type: "POST", // HTTP metoda je POST jer saljemo informacije serveru, aktiviramo metodu napraviBioskop koja zahteva POST
		url: "http://localhost:8080/bioskop/update", // Gadja specificni url za metodu kontrolera
		dataType: "text", // Povratna vrednost
		contentType: "application/json", // Podaci koje saljemo
		data: izmenaJSON, // Saljemo objekat koji smo napravili, on je taj data JSON
		success: function (data) {
			alert("Ime bioskopa '" + $("#starinaziv").val() + "' promenjeno u '" + $("#novinaziv").val() + "'.");
			window.location.href = "index-administrator.html"; // Redirect
		},
		error: function (data) {
			alert("Bioskop sa tim nazivom ne postoji!");
			console.log("ERROR: ", data);
		}
	});
});