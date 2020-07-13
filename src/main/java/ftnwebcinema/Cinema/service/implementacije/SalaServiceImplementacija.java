package ftnwebcinema.Cinema.service.implementacije;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ftnwebcinema.Cinema.entity.Sala;
import ftnwebcinema.Cinema.repository.SalaRepo;
import ftnwebcinema.Cinema.service.SalaService;

@Service
public class SalaServiceImplementacija implements SalaService{

	@Autowired 
	private SalaRepo salaRepo;
	
	@Override
	public void delete(Long idBioskop) {
		// Brisanje se vrsi po id-u bioskopa
		// Uzimamo sve sale gde je bioskop id taj, pa njih brisemo po njihovom id-u
		List<Sala> lista = this.salaRepo.findAll(); 
		for(Sala s : lista) {
			if(s.getBioskop().getIdBioskop() == idBioskop) {
				this.salaRepo.deleteById(s.getIdSala());
			}
		}
	}
}
