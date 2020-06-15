/* Imena kolona citaj iz H2 */

INSERT INTO ADMINISTRATOR (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('djordje00','teskasifra','Djordje','Stankovic','+38165808808','djordjestankovic@gmail.com','01.05.2000.','Administrator', FALSE);
INSERT INTO GLEDALAC (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('marko_','mnogoteskasifra','Marko','Markovic','+3816512313','markom@gmail.com','29.08.1999.','Gledalac', FALSE);
INSERT INTO GLEDALAC (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('ficko','najtezasifra','Filip','Filipovic','+38113452313','filip@gmail.com','29.08.1998.','Gledalac', FALSE);
INSERT INTO MENADZER (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('bobo','jostezasifra','Boban','Gavrilovic','+3816321312','bobang@gmail.com','29.02.2000.','Menadzer', FALSE);
INSERT INTO MENADZER (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('gogo','jostezasifra2','Gogan','Bavrilovic','+3811322222','goganb@gmail.com','13.02.1059.','Menadzer', FALSE);

INSERT INTO BIOSKOP (naziv, adresa, broj_telefona_centrale, email, menadzer_bioskopa_id_menadzer) VALUES ('Bioskop Krusevac', 'Naziv Ulice BB', '037111222', 'bioskopkrusevac@gmail.com', 1);
INSERT INTO BIOSKOP (naziv, adresa, broj_telefona_centrale, email, menadzer_bioskopa_id_menadzer) VALUES ('Bioskop Novi Sad', 'Centar 01', '021221122', 'bioskopns@gmail.com', 2);

INSERT INTO FILM (naziv, opis, zanr, trajanje, srednja_ocena) VALUES ('The Irishman', 'Opis filma The Irishman', 'Drama', '2h30m', 0);
INSERT INTO FILM (naziv, opis, zanr, trajanje, srednja_ocena) VALUES ('Joker', 'Opis filma Joker', 'Drama/Akcija', '2h', 0);
INSERT INTO FILM (naziv, opis, zanr, trajanje, srednja_ocena) VALUES ('El Camino', 'Opis filma El Camino', 'Drama/Akcija', '1h45m', 0);

/* Ocena mora da se popunjava posle filma */
INSERT INTO OCENA (ocena, gledalac_id_gledalac, film_id_film) VALUES (100, 2, 3);
INSERT INTO OCENA (ocena, gledalac_id_gledalac, film_id_film) VALUES (75, 1, 2);

INSERT INTO PROJEKCIJA (film_id_film, datum_projekcije, cena_projekcije, rezervator_id_gledalac) VALUES (1, '01.05.2020.', 250, 1); /* Projekcija 1 */ 
INSERT INTO PROJEKCIJA (film_id_film, datum_projekcije, cena_projekcije) VALUES (2, '05.05.2020.', 350); /* Projekcija 2 */ 
INSERT INTO PROJEKCIJA (film_id_film, datum_projekcije, cena_projekcije) VALUES (1, '06.05.2020.', 250); /* Projekcija 3 */ 
INSERT INTO PROJEKCIJA (film_id_film, datum_projekcije, cena_projekcije, rezervator_id_gledalac) VALUES (3, '09.05.2020.', 350, 2); /* Projekcija 4 */ 

INSERT INTO SALA (kapacitet, oznaka_sale, bioskop_id_bioskop) VALUES (100, 'A', 1); /* Sala 1 u Bioskop 1 */ 
INSERT INTO SALA (kapacitet, oznaka_sale, bioskop_id_bioskop) VALUES (50, 'B', 1); /* Sala 2 u Bioskop 1 */ 
INSERT INTO SALA (kapacitet, oznaka_sale, bioskop_id_bioskop) VALUES (200, 'Sala 1', 2); /* Sala 3 u Bioskop 2 */ 

INSERT INTO SALINE_PROJEKCIJE (lista_sala_id_sala, terminska_lista_projekcija_id_projekcija) VALUES (1, 1); /* U Salu 1 ide Projekcija 1 */ 
INSERT INTO SALINE_PROJEKCIJE (lista_sala_id_sala, terminska_lista_projekcija_id_projekcija) VALUES (2, 2); /* U Salu 2 ide Projekcija 2 */ 
INSERT INTO SALINE_PROJEKCIJE (lista_sala_id_sala, terminska_lista_projekcija_id_projekcija) VALUES (3, 3); /* U Salu 3 ide Projekcija 3 */ 
INSERT INTO SALINE_PROJEKCIJE (lista_sala_id_sala, terminska_lista_projekcija_id_projekcija) VALUES (3, 4); /* U Salu 3 ide Projekcija 4 */ 

INSERT INTO BIOSKOPOVE_PROJEKCIJE(lista_bioskopa_id_bioskop, raspored_projekcija_id_projekcija) VALUES (1, 1); /* U Bioskop 1 ide Projekcija 1 */ 
INSERT INTO BIOSKOPOVE_PROJEKCIJE(lista_bioskopa_id_bioskop, raspored_projekcija_id_projekcija) VALUES (1, 2); /* U Bioskop 1 ide Projekcija 2 */ 
INSERT INTO BIOSKOPOVE_PROJEKCIJE(lista_bioskopa_id_bioskop, raspored_projekcija_id_projekcija) VALUES (2, 3); /* U Bioskop 2 ide Projekcija 3 */ 
INSERT INTO BIOSKOPOVE_PROJEKCIJE(lista_bioskopa_id_bioskop, raspored_projekcija_id_projekcija) VALUES (2, 4); /* U Bioskop 2 ide Projekcija 4 */ 

INSERT INTO ODGLEDANI_FILMOVI(lista_gledalaca_id_gledalac, lista_odgledanih_filmova_id_film) VALUES (1, 2); /* Gledalac 1 gledao Film 2 */ 
INSERT INTO ODGLEDANI_FILMOVI(lista_gledalaca_id_gledalac, lista_odgledanih_filmova_id_film) VALUES (1, 3); /* Gledalac 1 gledao Film 3 */ 
INSERT INTO ODGLEDANI_FILMOVI(lista_gledalaca_id_gledalac, lista_odgledanih_filmova_id_film) VALUES (2, 1); /* Gledalac 2 gledao Film 1 */ 