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
		"password": $("#password").val()
		// Ovde se ne menja aktivnost
	});

	// setItem prima key/value parametre, ako ih nema pravi ih, i ako ih ima, overwrite-uje
	localStorage.setItem("user", $("#username").val());
	console.log(localStorage); // Za debug

	$.ajax({
		type: "POST", // HTTP metoda je POST jer saljemo informacije serveru
		url: "http://localhost:8080/prijava", // Gadja specificni url za metodu kontrolera
		dataType: "text", // Tip povratne vrednosti iz kontrolera, promenio sa json na text jer vracam string zbog redirekcije
		contentType: "application/json", // Podaci koje saljemo
		data: prijavaJSON, // Saljemo objekat koji smo napravili, on je taj data JSON (ovo nije ono sto dobijamo od kontrolera, tako pise i u dokumentaciji ajax-a)
		success: function (data) {
			// Ovo data je povratna vrednost iz metode kontrolera (iako je ona rekla da je to objekat koji saljemo?)
			// pa odatle mozemo da kazemo gde hocemo da redirectujemo
			console.log("data iz success je ", data);
			alert("Prijavljeni ste!");
			window.location.href = "index-" + data + ".html"; // Redirect
		},
		error: function (data) {
			console.log("data iz error je ", data);
			alert("Korisnik sa tim podacima ne postoji.");
			// Ne radi redirect nego ostaje na stranici dok se ne unesu dobri podaci
		}
	});
});