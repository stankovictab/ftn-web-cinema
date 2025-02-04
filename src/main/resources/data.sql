/* Imena kolona citaj iz H2 */

INSERT INTO ADMINISTRATOR (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('djordje00','teskasifra','Djordje','Stankovic','+38165808808','djordjestankovic@gmail.com','2000-05-01','administrator', TRUE);

INSERT INTO GLEDALAC (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('marko_','mnogoteskasifra','Marko','Markovic','+3816512313','markom@gmail.com','1999-08-29','gledalac', TRUE);
INSERT INTO GLEDALAC (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('ficko','najtezasifra','Filip','Filipovic','+38113452313','filip@gmail.com','1998-02-13','gledalac', TRUE);
INSERT INTO GLEDALAC (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('dusan123','sifrasrednjetezine','Dusan','Dusanovic','+38213232334','dusan123@gmail.com','1999-09-09','gledalac', TRUE);

INSERT INTO MENADZER (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('bobo','malotezasifra','Boban','Gavrilovic','+3816321312','bobang@gmail.com','2000-02-29','menadzer', TRUE);
INSERT INTO MENADZER (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('gogo','jostezasifra','Gogan','Bavrilovic','+3811322222','goganb@gmail.com','1059-01-01','menadzer', FALSE);
INSERT INTO MENADZER (username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('jovanovic77','12345','Stefan','Jovanovic','+381009900990','jovanovic77@gmail.com','1977-02-03','menadzer', FALSE);

INSERT INTO BIOSKOP (naziv, adresa, broj_telefona_centrale, email, menadzer_bioskopa_id_menadzer) VALUES ('Bioskop Krusevac', 'Kajmakcalanska 01, Krusevac', '037 111-222', 'bioskopkrusevac@gmail.com', 1);
INSERT INTO BIOSKOP (naziv, adresa, broj_telefona_centrale, email, menadzer_bioskopa_id_menadzer) VALUES ('Bioskop Novi Sad', 'Centar 01, Novi Sad', '021 787-878', 'bioskopns@gmail.com', 2);

INSERT INTO FILM (naziv, opis, zanr, trajanje, srednja_ocena) VALUES ('The Irishman', 'An old man recalls his time painting houses for his friend, Jimmy Hoffa, through the 1950-70s', 'Drama', '2h30m', 0);
INSERT INTO FILM (naziv, opis, zanr, trajanje, srednja_ocena) VALUES ('Joker', 'In Gotham City, mentally troubled comedian Arthur Fleck is disregarded and mistreated by society. He then embarks on a downward spiral of revolution and bloody crime. This path brings him face-to-face with his alter-ego: the Joker.', 'Drama/Akcija', '2h', 0);
INSERT INTO FILM (naziv, opis, zanr, trajanje, srednja_ocena) VALUES ('El Camino', 'El Camino: A Breaking Bad Movie follows fugitive Jesse Pinkman as he runs from his captors, the law and his past.', 'Drama/Akcija', '1h45m', 0);

/* Ocena mora da se popunjava posle filma */
INSERT INTO OCENA (ocena, gledalac_id_gledalac, film_id_film) VALUES (100, 2, 3);
INSERT INTO OCENA (ocena, gledalac_id_gledalac, film_id_film) VALUES (75, 1, 2);

INSERT INTO PROJEKCIJA (film_id_film, datum_projekcije, cena_projekcije, rezervator_id_gledalac) VALUES (1, '2020-05-01', 250, 1); /* Projekcija 1 */ 
INSERT INTO PROJEKCIJA (film_id_film, datum_projekcije, cena_projekcije) VALUES (2, '2020-05-05', 350); /* Projekcija 2 */ 
INSERT INTO PROJEKCIJA (film_id_film, datum_projekcije, cena_projekcije) VALUES (1, '2020-05-06', 250); /* Projekcija 3 */ 
INSERT INTO PROJEKCIJA (film_id_film, datum_projekcije, cena_projekcije, rezervator_id_gledalac) VALUES (3, '2020-05-09', 350, 2); /* Projekcija 4 */ 

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