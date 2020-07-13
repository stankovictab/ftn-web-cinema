// Fokus na prvo input polje, radi na load stranice, pa se poziva u body na onload atribut (moze i AJAX)
function fokusiraj() {
	document.getElementsByTagName("input")[0].focus();
}

$(document).on("submit", "form", function (event) {
	event.preventDefault();
	var brisanjeJSON = JSON.stringify({
		"naziv": $("#naziv").val() // Mora lowercase
	});

	$.ajax({
		type: "POST", // HTTP metoda je POST jer saljemo informacije serveru, aktiviramo metodu napraviBioskop koja zahteva POST
		url: "http://localhost:8080/bioskop/brisanje", // Gadja specificni url za metodu kontrolera
		dataType: "text", // Povratna vrednost
		contentType: "application/json", // Podaci koje saljemo
		data: brisanjeJSON, // Saljemo objekat koji smo napravili, on je taj data JSON
		success: function () {
			alert("Bioskop '" + $("#naziv").val() + "' je izbrisan.");
			window.location.href = "index-administrator.html"; // Redirect
		},
		error: function (data) {
			alert("Brisanje bioskopa nije uspelo.\nProveri uneti naziv bioskopa, i sale bioskopa '" + $("#naziv").val() + "' u bazi.");
			console.log("ERROR: ", data);
		}
	});
});