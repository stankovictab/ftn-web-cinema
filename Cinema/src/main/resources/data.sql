/* Nema povezujucih elemenata, samo standardni atributi (Column) */
/* Ocena zato nema nista uneto u nju */

/* Spojio salu sa bioskopom jer imamo 2 bioskopa, jedan id=1 drugi 2 */

INSERT INTO KORISNIK (dtype, username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('','djordje00','teskasifra','Djordje','Stankovic','+38165808808','djordjestankovic@gmail.com','01.05.2000.','Administrator',TRUE);

INSERT INTO KORISNIK (dtype, username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('','marko99','mnogoteskasifra','Marko','Todorcevic','+3816512313','markotodorcevic@gmail.com','29.08.1999.','Gledalac',TRUE);

INSERT INTO KORISNIK (dtype, username, password, ime, prezime, telefon, email, dob, uloga, aktivan) VALUES ('','nikola99','jostezasifra','Nikola','Matijevic','+3816321312','nikolamatijevic@gmail.com','29.02.2000.','Menadzer',TRUE);

INSERT INTO BIOSKOP (naziv, adresa, broj_telefona_centrale, email) VALUES ('Bioskop Krusevac', 'Naziv Ulice BB', '037111222', 'bioskopkrusevac@gmail.com');

INSERT INTO BIOSKOP (naziv, adresa, broj_telefona_centrale, email) VALUES ('Bioskop Novi Sad', 'Centar 01', '021221122', 'bioskopns@gmail.com');

INSERT INTO FILM (naziv, opis, zanr, trajanje, srednja_ocena) VALUES ('The Irishman', 'Opis filma The Irishman', 'Drama', '2h30m', 0);

INSERT INTO FILM (naziv, opis, zanr, trajanje, srednja_ocena) VALUES ('Joker', 'Opis filma Joker', 'Drama/Akcija', '2h', 0);

INSERT INTO PROJEKCIJA (datum_projekcije, cena_projekcije) VALUES ('01.05.2020.', 250);

INSERT INTO SALA (kapacitet, oznaka_sale, bioskop_id_bioskop) VALUES (100, 'A', 1);