package ftnwebcinema.Cinema.service;

import java.util.List;
import ftnwebcinema.Cinema.entity.Sala;

public interface SalaService {
	
	void delete(Long idBioskop);
	
	boolean napraviSalu(Sala sala);
	
	public List<Sala> findAll();
	
	public void delete(Sala sala);
	
	public Sala findByOznaka(String oznaka);
	
	public void deleteById(Long id);
	
}
