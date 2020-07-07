function aktiviraj() {
	// TODO:

	// Mora reload posle aktivacije da prikaze aktivan status u tabeli
}

function izbrisi() {
	// TODO:

	// Mora reload posle brisanja
}

// Punjenje tabele
$("#tabela-menadzera").ready(function () {
	$.ajax({
		type: "GET", // Dobija informacije od servera u obliku JSON-a
		url: "http://localhost:8080/menadzer", // URL Kontrolera
		dataType: "json", // Povratna vrednost
		success: function (data) { // data je JSON iz kontrolera (ResponseEntity), odnosno lista DTO-ova, pa kroz nju moze da se iterira
			console.log("SUCCESS : ", data); // Ispisuje celu listu u konzolu
			for (i = 0; i < data.length; i++) {
				// Konkateniraju se informacije u string koji ce se apendovati na tabelu (vidi dole)
				var row = "<tr>";
				row += "<td>" + data[i]['idMenadzer'] + "</td>"; // Ne id
				row += "<td>" + data[i]['ime'] + "</td>";
				row += "<td>" + data[i]['prezime'] + "</td>";
				let aktivnost = data[i]['aktivan'];
				if (aktivnost == true) // Jer izgleda da nije string vrednost
					row += "<td style=\"color: green; font-weight: 800;\">" + "AKTIVAN" + "</td>";
				else
					row += "<td style=\"color: red; font-weight: 800;\">" + "NEAKTIVAN" + "</td>";
				// " se escape-uje sa \

				var dugmeAktiviraj = "<button onclick='aktiviraj()' class='dugmeAktiviraj btn btn-info' id = " + data[i]['idMenadzer'] + ">Aktiviraj</button>";
				if (aktivnost == true)
					row += "<td>" + "✔️" + "</td>";
				else
					row += "<td>" + dugmeAktiviraj + "</td>";
				// Moraju isti id-evi da bi bilo za istog menadzera
				var dugmeIzbrisi = "<button onclick='izbrisi()' class='dugmeIzbrisi btn btn-danger' id = " + data[i]['idMenadzer'] + ">Izbrisi</button>";
				row += "<td>" + dugmeIzbrisi + "</td>";

				row += "</tr>";
				$('#tabela-menadzera').append(row);
			}
		},
		error: function (data) {
			console.log("ERROR : ", data);
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