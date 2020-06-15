package ftnwebcinema.Cinema.entity.dto;

public class ProjekcijaDTO {
	private Long idProjekcija;

	public Long getIdProjekcija() {
		return idProjekcija;
	}

	public void setIdProjekcija(Long idProjekcija) {
		this.idProjekcija = idProjekcija;
	}
	
	ProjekcijaDTO(){}
	ProjekcijaDTO(Long id){
		this.idProjekcija = id;
	}
}
