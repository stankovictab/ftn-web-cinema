package ftnwebcinema.Cinema.service.implementacije;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftnwebcinema.Cinema.entity.Menadzer;
import ftnwebcinema.Cinema.repository.MenadzerRepo;
import ftnwebcinema.Cinema.service.MenadzerService;

@Service
public class MenadzerServiceImplementacija implements MenadzerService {
	
		// Veza ka repozitorijumu
		@Autowired 
		private MenadzerRepo menadzerRepo;
		
		@Override
		public Menadzer napravi(Menadzer menadzer) throws Exception {
			if (menadzer.getIdMenadzer() != null) {
	            throw new Exception("ID Menadzera koji se pravi mora da bude null."); // Jer mu se id dodaje posle
	        }
			Menadzer novi = this.menadzerRepo.save(menadzer); 
			// Objekat novi uzima povratnu vrednost od predefinisane funkcije save iz repozitorijuma (iz JpaRepository koju implementira) koji je taj kreiran objekat
	        return novi;
		}
		
		@Override
		public Menadzer findOne(Long id) {
			Menadzer nadjeni = this.menadzerRepo.getOne(id); 
			// Povratna vrednost od getOne (nije findOne) iz JpaRepository je taj objekat sa tim id-em
	        return nadjeni;
		}
		
		@Override
		public List<Menadzer> findAll() {
			List<Menadzer> lista = this.menadzerRepo.findAll(); 
			// findAll je predefinisana funkcija iz JpaRepository koja vraca listu iz baze
	        return lista;
		}
		
		// Ova metoda menja samo ime korisnika
		@Override
		public Menadzer update(Menadzer menadzer) throws Exception {
			Menadzer neUpdatovan = this.menadzerRepo.getOne(menadzer.getIdMenadzer());
	        if (neUpdatovan == null) {
	            throw new Exception("Gledalac ne moze da se update-uje jer ne postoji.");
	        }

	        // Postavljanje novog imena
	        neUpdatovan.setIme(menadzer.getIme());

	        // Cuvanje u bazi
	        Menadzer updatovan = this.menadzerRepo.save(neUpdatovan);
	        return updatovan;
		}
		
		@Override
		public void delete(Long id) {
			this.menadzerRepo.deleteById(id); // Samo brise po id, predefinisana funkcija
		}
		
}
