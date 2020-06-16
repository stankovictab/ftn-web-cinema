// Fokus na prvo input polje, radi na load stranice, pa se poziva u body na onload atribut (moze i AJAX)
function fokusiraj() {
	document.getElementsByTagName("input")[0].focus();
}

// Prijava, mora u drugom fajlu da ne bi u isto vreme radio obe AJAX funkcije, i za registraciju i za prijavu
// Nece sa $("imeForme").submit() ili $("imeForme").on("submit", "form")
$(document).on("submit", "form", function (event) {
	event.preventDefault();
	var prijavaJSON = JSON.stringify({
		"username": $("#username").val(),
		"password": $("#password").val(),
		"aktivan": true // Kada se prijavi, korisnik je aktivan
	});

	$.ajax({
		type: "POST", // HTTP metoda je POST jer saljemo informacije serveru
		url: "http://localhost:8080/gledalac/prijava", // Gadja specificni url za metodu kontrolera
		dataType: "json", // Povratna vrednost
		contentType: "application/json", // Podaci koje saljemo
		data: prijavaJSON, // Saljemo objekat koji smo napravili, on je taj data JSON
		success: function () {
			alert("Prijavljeni ste!");
			window.location.href = "index.html"; // Redirect
		},
		error: function (data) {
			alert("Korisnik sa tim podacima ne postoji.");
		}
	});
});