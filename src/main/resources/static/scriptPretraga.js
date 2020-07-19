$("#pretraga").on('click', '#pretrazi', function () {
	var keyword = $("#keyword").val(); // String koji trazimo
	var pretraga = "0"; // Broj po kojem ce kriterijum biti 
	if ($("#pretraga-naziv").is(":checked")) {
		pretraga = "1";
	}
	else if ($("#pretraga-zanr").is(":checked")) {
		pretraga = "2";
	}
	else if ($("#pretraga-opis").is(":checked")) {
		pretraga = "3";
	}
	else if ($("#pretraga-ocena").is(":checked")) {
		pretraga = "4";
	}
	else if ($("#pretraga-vremeprojekcije").is(":checked")) {
		pretraga = "5";
	}
	else if ($("#pretraga-cenakarte").is(":checked")) {
		pretraga = "6";
	}

	var kriterijumi = JSON.stringify({
		"keyword": keyword,
		"pretraga": pretraga
	});

	$.ajax({
		type: "POST",
		url: "http://localhost:8080/film/pretraga",
		contentType: "application/json",
		data: kriterijumi,
		dataType: "json",
		success: function (data) {
			console.log("PRETRAGA SUCCESS : ", data);
			$("#divPocetnaTabela").hide();
			$("#divRezultatPretrage").removeAttr("hidden"); // Nece .show() iz nekog razloga

			for (i = 0; i < data.length; i++) {
				var row = "<tr>";
				row += "<td>" + data[i]['idFilm'] + "</td>";
				row += "<td>" + data[i]['naziv'] + "</td>";
				row += "<td>" + data[i]['zanr'] + "</td>";
				row += "</tr>";
				$('#tabela-filmova-2').append(row);
			}
		},
		error: function (data) {
			console.log("PRETRAGA ERROR : ", data);
		}
	});
});