// Fokus na prvo input polje, radi na load stranice, pa se poziva u body na onload atribut (moze i AJAX)
function fokusiraj() {
	document.getElementsByTagName("input")[0].focus();
}

// Pravljenje bioskopa za u bazu
$(document).on("submit", "form", function (event) {
	event.preventDefault();
	var izmenaJSON = JSON.stringify({
		"starinaziv": $("#starinaziv").val(),
		"novinaziv": $("#novinaziv").val()
	});

	$.ajax({
		type: "POST", // HTTP metoda je POST jer saljemo informacije serveru, aktiviramo metodu napraviBioskop koja zahteva POST
		url: "http://localhost:8080/bioskop/update", // Gadja specificni url za metodu kontrolera
		dataType: "text", // Povratna vrednost
		contentType: "application/json", // Podaci koje saljemo
		data: izmenaJSON, // Saljemo objekat koji smo napravili, on je taj data JSON
		success: function () {
			alert("???");
			window.location.href = "index-administrator.html"; // Redirect

			// if true uradio je ako nije ne, javi iz kontrolera
		},
		error: function (data) {
			alert("Greska u pravljenju bioskopa.");
			console.log("ERROR: ", data);
		}
	});
});