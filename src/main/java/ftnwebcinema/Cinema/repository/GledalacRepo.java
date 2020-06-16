package ftnwebcinema.Cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ftnwebcinema.Cinema.entity.Gledalac;

public interface GledalacRepo extends JpaRepository<Gledalac, Long>{
	
	Gledalac findByUsername(String username);
	
	Gledalac getOne(Long id);
}
