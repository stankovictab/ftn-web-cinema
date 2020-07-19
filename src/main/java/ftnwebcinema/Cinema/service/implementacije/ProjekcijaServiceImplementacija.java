package ftnwebcinema.Cinema.service.implementacije;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftnwebcinema.Cinema.entity.Projekcija;
import ftnwebcinema.Cinema.repository.ProjekcijaRepo;
import ftnwebcinema.Cinema.service.ProjekcijaService;

@Service
public class ProjekcijaServiceImplementacija implements ProjekcijaService {

	@Autowired
	private ProjekcijaRepo projekcijaRepo;
	
	@Override
	public Projekcija napravi(Projekcija p) {
		return this.projekcijaRepo.save(p);
	}

	@Override
	public Projekcija findByDatumProjekcije(String datum) {
		return this.projekcijaRepo.findByDatumProjekcije(datum);
	}

	@Override
	public boolean updateProjekcije(Projekcija nadjena, String datumProjekcije) {
		// System.out.println("Dosao do update-a projekcije"); // Debug
		Projekcija p = this.projekcijaRepo.findByDatumProjekcije(nadjena.getDatumProjekcije());
		if(p!=null) {
			p.setDatumProjekcije(datumProjekcije);
			if(this.projekcijaRepo.save(p)!=null) {
				System.out.println("Uspena izmena projekcije.");
				return true;
			}
		}
		return false;
	}
	
}
