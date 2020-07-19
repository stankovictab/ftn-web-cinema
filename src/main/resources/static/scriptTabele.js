// Punjenje tabele
$("#tabela-gledalaca").ready(function () {
	$.ajax({
		type: "GET", // Dobija informacije od servera u obliku JSON-a
		url: "http://localhost:8080/gledalac", // URL Kontrolera
		dataType: "json", // Povratna vrednost
		success: function (data) { // data je JSON iz kontrolera (ResponseEntity), odnosno lista DTO-ova, pa kroz nju moze da se iterira
			console.log("TABELA SUCCESS : ", data); // Ispisuje celu listu u konzolu
			for (i = 0; i < data.length; i++) {
				// Konkateniraju se informacije u string koji ce se apendovati na tabelu (vidi dole)
				var row = "<tr>";
				row += "<td>" + data[i]['idGledalac'] + "</td>"; // Ne id
				row += "<td>" + data[i]['ime'] + "</td>";
				row += "<td>" + data[i]['prezime'] + "</td>";
				// Na pocetku nemamo dugme pa ovde pravimo, isto html kao string, id ce mu biti id korisnika (BITNO)
				var dugmeProfil = "<button class='dugmeProfil btn btn-secondary' id = " + data[i]['idGledalac'] + ">Vidi Profil</button>";
				// Sa + "zaProfil" nece lepo da gadja url u sledecoj AJAX funkciji, jer dodaje + this.id koji dobija iz ovog button-a (to je onaj /{id} u kontroleru)
				// Nazalost moraju da imaju isti id :(
				// var dugmeIzbrisi = "<button onclick='brisanjeMessage()' class='dugmeIzbrisi btn btn-danger' id = " + data[i]['idGledalac'] + ">Izbrisi</button>";
				row += "<td>" + dugmeProfil + "</td>";
				// row += "<td>" + dugmeIzbrisi + "</td>";
				row += "</tr>";
				$('#tabela-gledalaca').append(row);
				// "row" je string HTML koda koji se dodaje na tabelu koja ima ovaj id 
			}
		},
		error: function (data) {
			console.log("TABELA ERROR : ", data);
		}
	});

});

// See More za na Profil
$("#tabela-gledalaca").on('click', '.dugmeProfil', function () { // Na klik dugmeta klase dugmeProfil (u tr tabele)
	// Moze redirekcija na profil.html ali ne znam kako pa se on ne koristi
	// Moze da se sakrije cela tabela pa da se pokazu elementi profila - slika, ime, prezime, sve sto je u profil.html prekopirano - to i radimo
	$("#tabela-gledalaca").hide(); // Sakriva celu tabelu na klik
	$("#tabela-naslov").hide();
	$("#tabela-naslov-red").hide();
	$("#home-dugme").hide();

	$.ajax({
		type: "GET",
		url: "http://localhost:8080/gledalac/" + this.id,  // this.id je button-ov id, a kao button id je postavljen id zaposlenog gore, pa ne moze da se gore stavi + "zaProfil" da se razlikuje id od drugog button-a (moraju ipak imati isti id, sto nije dobra praksa, ali mora jer je ona ovako napravila)
		dataType: "json",
		success: function (data) {
			console.log("TABELA SUCCESS : ", data);
			// Menja podatke u profilu sa podacima iz JSON fajla
			$('#imeProfil').append(data['ime']);
			$('#prezimeProfil').append(data['prezime']);
			$('#usernameProfil').append(data['username']);
			$('#telefonProfil').append(data['telefon']);
			$('#emailProfil').append(data['email']);
			$('#dobProfil').append(data['dob']);
			$('#ulogaProfil').append(data['uloga']);
			// Prikazuje profil (koji je vec napravljen na istoj stranici gde je i tabela, ali je skriven na pocetku ucitavanja stranice)
			// Nece $("#profil").show(); iz nekog razloga, pa samo brisem atribut iz elementa
			$("#profil").removeAttr("hidden");
		},
		error: function (data) {
			console.log("TABELA ERROR : ", data);
		}
	});
});

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

// Punjenje tabele
$("#tabela-menadzera").ready(function () {
	$.ajax({
		type: "GET", // Dobija informacije od servera u obliku JSON-a
		url: "http://localhost:8080/menadzer", // URL Kontrolera
		dataType: "json", // Povratna vrednost
		success: function (data) { // data je JSON iz kontrolera (ResponseEntity), odnosno lista DTO-ova, pa kroz nju moze da se iterira
			console.log("TABELA SUCCESS : ", data); // Ispisuje celu listu u konzolu
			for (i = 0; i < data.length; i++) {
				// Konkateniraju se informacije u string koji ce se apendovati na tabelu (vidi dole)
				var row = "<tr>";
				row += "<td>" + data[i]['idMenadzer'] + "</td>"; // Ne id
				row += "<td>" + data[i]['ime'] + "</td>";
				row += "<td>" + data[i]['prezime'] + "</td>";
				// Na pocetku nemamo dugme pa ovde pravimo, isto html kao string, id ce mu biti id korisnika (BITNO)
				var dugmeProfil = "<button class='dugmeProfil btn btn-secondary' id = " + data[i]['idMenadzer'] + ">Vidi Profil</button>";
				// Sa + "zaProfil" nece lepo da gadja url u sledecoj AJAX funkciji, jer dodaje + this.id koji dobija iz ovog button-a (to je onaj /{id} u kontroleru)
				// Nazalost moraju da imaju isti id :(
				// var dugmeIzbrisi = "<button onclick='brisanjeMessage()' class='dugmeIzbrisi btn btn-danger' id = " + data[i]['idMenadzer'] + ">Izbrisi</button>";
				row += "<td>" + dugmeProfil + "</td>";
				// row += "<td>" + dugmeIzbrisi + "</td>";
				row += "</tr>";
				$('#tabela-menadzera').append(row);
				// "row" je string HTML koda koji se dodaje na tabelu koja ima ovaj id 
			}
		},
		error: function (data) {
			console.log("TABELA ERROR : ", data);
		}
	});

});

// See More za na Profil
$("#tabela-menadzera").on('click', '.dugmeProfil', function () { // Na klik dugmeta klase dugmeProfil (u tr tabele)
	// Moze redirekcija na profil.html ali ne znam kako pa se on ne koristi
	// Moze da se sakrije cela tabela pa da se pokazu elementi profila - slika, ime, prezime, sve sto je u profil.html prekopirano - to i radimo
	$("#tabela-menadzera").hide(); // Sakriva celu tabelu na klik
	$("#tabela-naslov").hide();
	$("#tabela-naslov-red").hide();
	$("#home-dugme").hide();

	$.ajax({
		type: "GET",
		url: "http://localhost:8080/menadzer/" + this.id,  // this.id je button-ov id, a kao button id je postavljen id zaposlenog gore, pa ne moze da se gore stavi + "zaProfil" da se razlikuje id od drugog button-a (moraju ipak imati isti id, sto nije dobra praksa, ali mora jer je ona ovako napravila)
		dataType: "json",
		success: function (data) {
			console.log("TABELA SUCCESS : ", data);
			// Menja podatke u profilu sa podacima iz JSON fajla
			$('#imeProfil').append(data['ime']);
			$('#prezimeProfil').append(data['prezime']);
			$('#usernameProfil').append(data['username']);
			$('#telefonProfil').append(data['telefon']);
			$('#emailProfil').append(data['email']);
			$('#dobProfil').append(data['dob']);
			$('#ulogaProfil').append(data['uloga']);
			// Prikazuje profil (koji je vec napravljen na istoj stranici gde je i tabela, ali je skriven na pocetku ucitavanja stranice)
			// Nece $("#profil").show(); iz nekog razloga, pa samo brisem atribut iz elementa
			$("#profil").removeAttr("hidden");
		},
		error: function (data) {
			console.log("TABELA ERROR : ", data);
		}
	});
});

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

// Punjenje tabele
$("#tabela-administratora").ready(function () {
	$.ajax({
		type: "GET", // Dobija informacije od servera u obliku JSON-a
		url: "http://localhost:8080/administrator", // URL Kontrolera
		dataType: "json", // Povratna vrednost
		success: function (data) { // data je JSON iz kontrolera (ResponseEntity), odnosno lista DTO-ova, pa kroz nju moze da se iterira
			console.log("TABELA SUCCESS : ", data); // Ispisuje celu listu u konzolu
			for (i = 0; i < data.length; i++) {
				// Konkateniraju se informacije u string koji ce se apendovati na tabelu (vidi dole)
				var row = "<tr>";
				row += "<td>" + data[i]['idAdministrator'] + "</td>"; // Ne id
				row += "<td>" + data[i]['ime'] + "</td>";
				row += "<td>" + data[i]['prezime'] + "</td>";
				// Na pocetku nemamo dugme pa ovde pravimo, isto html kao string, id ce mu biti id korisnika (BITNO)
				var dugmeProfil = "<button class='dugmeProfil btn btn-secondary' id = " + data[i]['idAdministrator'] + ">Vidi Profil</button>";
				// Sa + "zaProfil" nece lepo da gadja url u sledecoj AJAX funkciji, jer dodaje + this.id koji dobija iz ovog button-a (to je onaj /{id} u kontroleru)
				// Nazalost moraju da imaju isti id :(
				// var dugmeIzbrisi = "<button onclick='brisanjeMessage()' class='dugmeIzbrisi btn btn-danger' id = " + data[i]['idAdministrator'] + ">Izbrisi</button>";
				row += "<td>" + dugmeProfil + "</td>";
				// row += "<td>" + dugmeIzbrisi + "</td>";
				row += "</tr>";
				$('#tabela-administratora').append(row);
				// "row" je string HTML koda koji se dodaje na tabelu koja ima ovaj id 
			}
		},
		error: function (data) {
			console.log("TABELA ERROR : ", data);
		}
	});

});

// See More za na Profil
$("#tabela-administratora").on('click', '.dugmeProfil', function () { // Na klik dugmeta klase dugmeProfil (u tr tabele)
	// Moze redirekcija na profil.html ali ne znam kako pa se on ne koristi
	// Moze da se sakrije cela tabela pa da se pokazu elementi profila - slika, ime, prezime, sve sto je u profil.html prekopirano - to i radimo
	$("#tabela-administratora").hide(); // Sakriva celu tabelu na klik
	$("#tabela-naslov").hide();
	$("#tabela-naslov-red").hide();
	$("#home-dugme").hide();

	$.ajax({
		type: "GET",
		url: "http://localhost:8080/administrator/" + this.id,  // this.id je button-ov id, a kao button id je postavljen id zaposlenog gore, pa ne moze da se gore stavi + "zaProfil" da se razlikuje id od drugog button-a (moraju ipak imati isti id, sto nije dobra praksa, ali mora jer je ona ovako napravila)
		dataType: "json",
		success: function (data) {
			console.log("TABELA SUCCESS : ", data);
			// Menja podatke u profilu sa podacima iz JSON fajla
			$('#imeProfil').append(data['ime']);
			$('#prezimeProfil').append(data['prezime']);
			$('#usernameProfil').append(data['username']);
			$('#telefonProfil').append(data['telefon']);
			$('#emailProfil').append(data['email']);
			$('#dobProfil').append(data['dob']);
			$('#ulogaProfil').append(data['uloga']);
			// Prikazuje profil (koji je vec napravljen na istoj stranici gde je i tabela, ali je skriven na pocetku ucitavanja stranice)
			// Nece $("#profil").show(); iz nekog razloga, pa samo brisem atribut iz elementa
			$("#profil").removeAttr("hidden");
		},
		error: function (data) {
			console.log("TABELA ERROR : ", data);
		}
	});
});

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

// Punjenje tabele
$("#tabela-bioskopa").ready(function () {
	$.ajax({
		type: "GET", // Dobija informacije od servera u obliku JSON-a
		url: "http://localhost:8080/bioskop", // URL Kontrolera
		dataType: "json", // Povratna vrednost
		success: function (data) { // data je JSON iz kontrolera (ResponseEntity), odnosno lista DTO-ova, pa kroz nju moze da se iterira
			console.log("TABELA SUCCESS : ", data); // Ispisuje celu listu u konzolu
			for (i = 0; i < data.length; i++) {
				// Konkateniraju se informacije u string koji ce se apendovati na tabelu (vidi dole)
				var row = "<tr>";
				row += "<td>" + data[i]['idBioskop'] + "</td>"; // Ne id
				row += "<td>" + data[i]['naziv'] + "</td>";
				row += "<td>" + data[i]['adresa'] + "</td>";
				// Na pocetku nemamo dugme pa ovde pravimo, isto html kao string, id ce mu biti id korisnika (BITNO)
				var dugmeProfil = "<button class='dugmeProfil btn btn-secondary' id = " + data[i]['idBioskop'] + ">Vidi Bioskop</button>";
				// Sa + "zaProfil" nece lepo da gadja url u sledecoj AJAX funkciji, jer dodaje + this.id koji dobija iz ovog button-a (to je onaj /{id} u kontroleru)
				// Nazalost moraju da imaju isti id :(
				// var dugmeIzbrisi = "<button onclick='brisanjeMessage()' class='dugmeIzbrisi btn btn-danger' id = " + data[i]['idBioskop'] + ">Izbrisi</button>";
				row += "<td>" + dugmeProfil + "</td>";
				// row += "<td>" + dugmeIzbrisi + "</td>";
				row += "</tr>";
				$('#tabela-bioskopa').append(row);
				// "row" je string HTML koda koji se dodaje na tabelu koja ima ovaj id 
			}
		},
		error: function (data) {
			console.log("TABELA ERROR : ", data);
		}
	});

});

// See More za na Profil
$("#tabela-bioskopa").on('click', '.dugmeProfil', function () { // Na klik dugmeta klase dugmeProfil (u tr tabele)
	// Moze redirekcija na profil.html ali ne znam kako pa se on ne koristi
	// Moze da se sakrije cela tabela pa da se pokazu elementi profila - slika, ime, prezime, sve sto je u profil.html prekopirano - to i radimo
	$("#tabela-bioskopa").hide(); // Sakriva celu tabelu na klik
	$("#tabela-naslov").hide();
	$("#tabela-naslov-red").hide();
	$("#home-dugme").hide();

	$.ajax({
		type: "GET",
		url: "http://localhost:8080/bioskop/" + this.id,  // this.id je button-ov id, a kao button id je postavljen id zaposlenog gore, pa ne moze da se gore stavi + "zaProfil" da se razlikuje id od drugog button-a (moraju ipak imati isti id, sto nije dobra praksa, ali mora jer je ona ovako napravila)
		dataType: "json",
		success: function (data) {
			console.log("TABELA SUCCESS : ", data);
			// Menja podatke u profilu sa podacima iz JSON fajla
			$('#nazivProfil').append(data['naziv']);
			$('#adresaProfil').append(data['adresa']);
			$('#btcProfil').append(data['brojTelefonaCentrale']);
			$('#emailProfil').append(data['email']);
			$("#profil").removeAttr("hidden");
		},
		error: function (data) {
			console.log("TABELA ERROR : ", data);
		}
	});
});

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

// Punjenje tabele
$("#tabela-filmova").ready(function () {
	$.ajax({
		type: "GET", // Dobija informacije od servera u obliku JSON-a
		url: "http://localhost:8080/film", // URL Kontrolera
		dataType: "json", // Povratna vrednost
		success: function (data) { // data je JSON iz kontrolera (ResponseEntity), odnosno lista DTO-ova, pa kroz nju moze da se iterira
			console.log("TABELA SUCCESS : ", data); // Ispisuje celu listu u konzolu
			for (i = 0; i < data.length; i++) {
				// Konkateniraju se informacije u string koji ce se apendovati na tabelu (vidi dole)
				var row = "<tr>";
				row += "<td>" + data[i]['idFilm'] + "</td>"; // Ne id
				row += "<td>" + data[i]['naziv'] + "</td>";
				row += "<td>" + data[i]['zanr'] + "</td>";
				// Na pocetku nemamo dugme pa ovde pravimo, isto html kao string, id ce mu biti id korisnika (BITNO)
				var dugmeProfil = "<button class='dugmeProfil btn btn-secondary' id = " + data[i]['idFilm'] + ">Vidi Film</button>";
				// Sa + "zaProfil" nece lepo da gadja url u sledecoj AJAX funkciji, jer dodaje + this.id koji dobija iz ovog button-a (to je onaj /{id} u kontroleru)
				// Nazalost moraju da imaju isti id :(
				// var dugmeIzbrisi = "<button onclick='brisanjeMessage()' class='dugmeIzbrisi btn btn-danger' id = " + data[i]['idFilm'] + ">Izbrisi</button>";
				row += "<td>" + dugmeProfil + "</td>";
				// row += "<td>" + dugmeIzbrisi + "</td>";
				row += "</tr>";
				$('#tabela-filmova').append(row);
				// "row" je string HTML koda koji se dodaje na tabelu koja ima ovaj id 
			}
		},
		error: function (data) {
			console.log("TABELA ERROR : ", data);
		}
	});

});

// See More za na Profil
$("#tabela-filmova").on('click', '.dugmeProfil', function () { // Na klik dugmeta klase dugmeProfil (u tr tabele)
	// Moze redirekcija na profil.html ali ne znam kako pa se on ne koristi
	// Moze da se sakrije cela tabela pa da se pokazu elementi profila - slika, ime, prezime, sve sto je u profil.html prekopirano - to i radimo
	$("#tabela-filmova").hide(); // Sakriva celu tabelu na klik
	$("#tabela-naslov").hide();
	$("#tabela-naslov-red").hide();
	$("#home-dugme").hide();

	$.ajax({
		type: "GET",
		url: "http://localhost:8080/film/" + this.id,  // this.id je button-ov id, a kao button id je postavljen id zaposlenog gore, pa ne moze da se gore stavi + "zaProfil" da se razlikuje id od drugog button-a (moraju ipak imati isti id, sto nije dobra praksa, ali mora jer je ona ovako napravila)
		dataType: "json",
		success: function (data) {
			console.log("TABELA SUCCESS : ", data);
			// Menja podatke u profilu sa podacima iz JSON fajla
			$('#nazivProfil').append(data['naziv']);
			$('#opisProfil').append(data['opis']);
			$('#zanrProfil').append(data['zanr']);
			$('#trajanjeProfil').append(data['trajanje']);
			$('#srednjaOcenaProfil').append(data['srednjaOcena']);
			$("#profil").removeAttr("hidden");
		},
		error: function (data) {
			console.log("TABELA ERROR : ", data);
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