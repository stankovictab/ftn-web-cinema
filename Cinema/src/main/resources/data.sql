/* ManyToOne veza iz sale koristi bioskop_id_bioskop, kao sto pise u H2 */

INSERT INTO ADMINISTRATOR (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('djordje00','teskasifra','Djordje','Stankovic','+38165808808','djordjestankovic@gmail.com','01.05.2000.','Administrator',TRUE);
INSERT INTO GLEDALAC (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('marko_','mnogoteskasifra','Marko','Markovic','+3816512313','markom@gmail.com','29.08.1999.','Gledalac',TRUE);
INSERT INTO GLEDALAC (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('ficko','najtezaSifra','Filip','Filipovic','+38113452313','filip@gmail.com','29.08.1998.','Gledalac',TRUE);
INSERT INTO MENADZER (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('bobo','jostezasifra','Boban','Gavrilovic','+3816321312','bobang@gmail.com','29.02.2000.','Menadzer',TRUE);
INSERT INTO MENADZER (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('gogo','jostezasifra2','Gogan','Bavrilovic','+3811322222','goganb@gmail.com','13.02.1059.','Menadzer',TRUE);

INSERT INTO BIOSKOP (naziv, adresa, broj_telefona_centrale, email, menadzer_bioskopa_id_menadzer) VALUES ('Bioskop Krusevac', 'Naziv Ulice BB', '037111222', 'bioskopkrusevac@gmail.com', 1);
INSERT INTO BIOSKOP (naziv, adresa, broj_telefona_centrale, email, menadzer_bioskopa_id_menadzer) VALUES ('Bioskop Novi Sad', 'Centar 01', '021221122', 'bioskopns@gmail.com', 2);

INSERT INTO FILM (naziv, opis, zanr, trajanje, srednja_ocena) VALUES ('The Irishman', 'Opis filma The Irishman', 'Drama', '2h30m', 0);
INSERT INTO FILM (naziv, opis, zanr, trajanje, srednja_ocena) VALUES ('Joker', 'Opis filma Joker', 'Drama/Akcija', '2h', 0);
INSERT INTO FILM (naziv, opis, zanr, trajanje, srednja_ocena) VALUES ('El Camino', 'Opis filma El Camino', 'Drama/Akcija', '1h45m', 0);

/* Dodao rezervator_id_gledalac za projekciju 1 kao prvog gledaoca */
/* Ficko gleda brbu */

INSERT INTO PROJEKCIJA (film_id_film, datum_projekcije, cena_projekcije, rezervator_id_gledalac) VALUES (1, '01.05.2020.', 250, 1); /* Projekcija 1 */ 
INSERT INTO PROJEKCIJA (film_id_film, datum_projekcije, cena_projekcije) VALUES (2, '05.05.2020.', 350); /* Projekcija 2 */ 
INSERT INTO PROJEKCIJA (film_id_film, datum_projekcije, cena_projekcije) VALUES (1, '06.05.2020.', 250); /* Projekcija 3 */ 
INSERT INTO PROJEKCIJA (film_id_film, datum_projekcije, cena_projekcije, rezervator_id_gledalac) VALUES (3, '09.05.2020.', 350, 2); /* Projekcija 4 */ 

INSERT INTO SALA (kapacitet, oznaka_sale, bioskop_id_bioskop) VALUES (100, 'A', 1); /* Sala 1 */ 
INSERT INTO SALA (kapacitet, oznaka_sale, bioskop_id_bioskop) VALUES (50, 'B', 1); /* Sala 2 */ 
INSERT INTO SALA (kapacitet, oznaka_sale, bioskop_id_bioskop) VALUES (90, 'Sala 1', 2); /* Sala 3 */ 

/* Posto postoji ManyToMany iz Sala u Projekcija moramo u tu tabelu da ubacamo */
/* Ovo je ustvari idSale i idProjekcije zajedno - jedan red je jedna veza */
/* Veza prve sale i prve projekcije */
INSERT INTO SALA_TERMINSKA_LISTA_PROJEKCIJA (lista_sala_id_sala, terminska_lista_projekcija_id_projekcija) VALUES (1,1);
INSERT INTO SALA_TERMINSKA_LISTA_PROJEKCIJA (lista_sala_id_sala, terminska_lista_projekcija_id_projekcija) VALUES (2,2);
INSERT INTO SALA_TERMINSKA_LISTA_PROJEKCIJA (lista_sala_id_sala, terminska_lista_projekcija_id_projekcija) VALUES (1,3);
INSERT INTO SALA_TERMINSKA_LISTA_PROJEKCIJA (lista_sala_id_sala, terminska_lista_projekcija_id_projekcija) VALUES (2,4);

/* U prvi bioskop ide prva projekcija */
INSERT INTO BIOSKOP_RASPORED_ODRZAVANJA_FILMOVA(bioskop_id_bioskop, raspored_odrzavanja_filmova_id_projekcija) VALUES (1,1);
INSERT INTO BIOSKOP_RASPORED_ODRZAVANJA_FILMOVA(bioskop_id_bioskop, raspored_odrzavanja_filmova_id_projekcija) VALUES (2,3);

INSERT INTO OCENA (ocena, gledalac_id_gledalac, film_id_film) VALUES (100, 2, 3);
INSERT INTO OCENA (ocena, gledalac_id_gledalac, film_id_film) VALUES (75, 1, 2);

INSERT INTO PROJEKCIJA_LISTA_BIOSKOPOVAUKOJIMA_SE_PRIKAZUJE (PROJEKCIJA_ID_PROJEKCIJA, LISTA_BIOSKOPOVAUKOJIMA_SE_PRIKAZUJE_ID_BIOSKOP) VALUES (1, 1);