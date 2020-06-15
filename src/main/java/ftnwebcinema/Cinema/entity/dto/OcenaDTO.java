package ftnwebcinema.Cinema.entity.dto;

public class OcenaDTO {
	private Long idOcena;
	private int ocena;
	public Long getIdOcena() {
		return idOcena;
	}
	public void setIdOcena(Long idOcena) {
		this.idOcena = idOcena;
	}
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	OcenaDTO(){}
	OcenaDTO(Long id, int ocena){
		this.idOcena = id;
		this.ocena = ocena;
	}
}
