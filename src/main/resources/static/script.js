// Postoji i bolji nacin da se ove funkcije urade

function prikaziPorukuBioskop() {
	alert("Bioskop ce biti dodat u repozitorijum u trecoj kontrolnoj tacki.");
}

function fokusiraj() {
	document.getElementsByTagName("input")[0].focus();
}

// Punjenje tabele
$(document).ready(function () {
	$.ajax({
		type: "GET", // Dobija informacije od servera u obliku JSON-a
		url: "http://localhost:8080/api/gledaoci/", // URL Kontrolera
		dataType: "json", // Povratna vrednost
		success: function (data) { // data je JSON iz kontrolera (ResponseEntity), odnosno lista DTO gledalaca, pa kroz nju moze da se iterira
			console.log("SUCCESS : ", data); // Ispisuje celu listu u konzolu
			for (i = 0; i < data.length; i++) {
				// Konkateniraju se informacije u string koji ce se apendovati na tabelu (vidi dole)
				var row = "<tr>";
				row += "<td>" + data[i]['idGledalac'] + "</td>"; // Ne id
				row += "<td>" + data[i]['ime'] + "</td>";
				row += "<td>" + data[i]['prezime'] + "</td>";
				// Na pocetku nemamo dugme pa ovde pravimo, isto html kao string, id ce mu biti id korisnika (BITNO)
				var dugmeProfil = "<button class='dugmeProfil' id = " + data[i]['idGledalac'] + ">Vidi Profil</button>";
				// Sa + "zaProfil" nece lepo da gadja url u sledecoj ajax funkciji, jer dodaje + this.id koji dobija iz ovog button-a (to je onaj /{id} u kontroleru)
				// Nazalost moraju da imaju isti id :(
				var dugmeIzbrisi = "<button class='dugmeIzbrisi' id = " + data[i]['idGledalac'] + ">Izbrisi</button>";
				row += "<td>" + dugmeProfil + "</td>";
				row += "<td>" + dugmeIzbrisi + "</td>";
				row += "</tr>";
				$('#tabela-gledalaca').append(row);
				// "row" je string HTML koda koji se dodaje na tabelu koja ima id tabela-gledalaca
			}
		},
		error: function (data) {
			console.log("ERROR : ", data);
		}
	});

});

// See More za na Profil
$(document).on('click', '.dugmeProfil', function () { // Na klik dugmeta klase dugmeProfil (u tr tabele)
	// Moze redirekcija na profil.html ali ne znam kako pa se on ne koristi
	// Moze da se sakrije cela tabela pa da se pokazu elementi profila - slika, ime, prezime, sve sto je u profil.html prekopirano - to i radimo
	$("#tabela-gledalaca").hide(); // Sakriva celu tabelu na klik
	$("#tabela-naslov").hide();
	$("#tabela-naslov-red").hide();
	$("#home-dugme").hide();

	$.ajax({
		type: "GET",
		url: "http://localhost:8080/api/gledaoci/" + this.id,  // this.id je button-ov id, a kao button id je postavljen id zaposlenog gore, pa ne moze da se gore stavi + "zaProfil" da se razlikuje id od drugog button-a (moraju ipak imati isti id, sto nije dobra praksa, ali mora jer je ona ovako napravila)
		dataType: "json",
		success: function (data) {
			console.log("SUCCESS : ", data);
			// Menja podatke u profilu sa podacima iz JSON fajla
			$('#imeProfil').append(data['ime']);
			$('#prezimeProfil').append(data['prezime']);
			$('#usernameProfil').append(data['username']);
			$('#telefonProfil').append(data['telefon']);
			$('#emailProfil').append(data['email']);
			$('#dobProfil').append(data['dob']);
			$('#ulogaProfil').append(data['uloga']);

			$("#profil").removeAttr("hidden");
			// Prikazuje profil (koji je vec napravljen na istoj stranici gde je i tabela, ali je skriven na pocetku ucitavanja stranice)
			// Nece $("#profil").show(); iz nekog razloga, pa samo brisem atribut iz elementa
		},
		error: function (data) {
			console.log("ERROR : ", data);
		}
	});
});

// Na submit-ovanje forme dodavanje korisnika, predefinisano u AJAX-u
// Ne moze i neka druga forma jer nije isti JSON, nisu ista polja
// Bilo je $(document).on("submit", "form", function(event){...}
// Bolje je ovako jer mozemo lakse da diferenciramo forme
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
		"uloga": $("#uloga").val(),
		"aktivan": true // Korisnik je aktivan kada napravi nalog, ne mora posle toga da se prijavi da bi taj bool promenio
	});

	$.ajax({
		type: "POST", // HTTP metoda je POST jer saljemo informacije serveru
		url: "http://localhost:8080/api/gledaoci", // Gadjamo isti url kontrolera
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

// Kako da se razlikuje a da se ne pravi razliciti fajl?
$(document).on("submit", "form", function (event) {
	event.preventDefault();
	var mojJSON = JSON.stringify({
		"username": $("#username").val(),
		"password": $("#password").val(),
		"aktivan": true // Kada se prijavi, korisnik je aktivan
	});

	$.ajax({
		type: "POST", // HTTP metoda je POST jer saljemo informacije serveru
		url: "http://localhost:8080/api/gledaoci/prijava", // Gadja specificni url za metodu kontrolera
		dataType: "json", // Povratna vrednost
		contentType: "application/json", // Podaci koje saljemo
		data: mojJSON, // Saljemo objekat koji smo napravili, on je taj data JSON
		success: function () {
			alert("Prijavljeni ste!");
			window.location.href = "index.html"; // Redirect
		},
		error: function (data) {
			alert("Korisnik sa tim podacima ne postoji.");
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