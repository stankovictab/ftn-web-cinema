// Fokus na prvo input polje, radi na load stranice, pa se poziva u body na onload atribut (moze i AJAX)
function fokusiraj() {
	document.getElementsByTagName("input")[0].focus();
}

// Dodavanje korisnika, submit forme
// Ne moze i neka druga forma jer nije isti JSON, nisu ista polja
// Tako da mora ovako, ne moze sa #("idForme").on("submit") ili .submit() (barem meni nije htelo)
$(document).on("submit", "form", function (event) {
	// Sprecava da se forma sama submit-uje, nego da radi ovo sto joj mi kazemo 
	event.preventDefault();
	// Umesto one funkcije formToJSON pravim odmah ovde objekat koji ce se slati na server dole
	// Svaki parametar objekta ce biti vrednost input polja - dobija se sa .val()
	// Ne znam da li je stringify neophodan
	var mojJSON = JSON.stringify({
		"username": $("#username").val(),
		"password": $("#password").val(),
		"ime": $("#ime").val(),
		"prezime": $("#prezime").val(),
		"telefon": $("#telefon").val(),
		"email": $("#email").val(),
		"dob": $("#dob").val(),
		"uloga": "gledalac", // Bilo je "uloga": $("#uloga").val(),
		"aktivan": true // Korisnik je aktivan kada napravi nalog, ne mora posle toga da se prijavi da bi taj bool promenio
		// Ovo aktivan ce se menjati
	});

	$.ajax({
		type: "POST", // HTTP metoda je POST jer saljemo informacije serveru
		url: "http://localhost:8080/gledalac", // Gadjamo na korektan url, bilo je + $("#uloga").val()
		dataType: "json", // Povratna vrednost
		contentType: "application/json", // Podaci koje saljemo
		data: mojJSON, // Saljemo objekat koji smo napravili, on je taj data JSON
		success: function () {
			alert($("#ime").val() + " " + $("#prezime").val() + " je kreiran.");
			window.location.href = "index.html"; // Redirect
		},
		error: function (data) {
			alert("Greška!");
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