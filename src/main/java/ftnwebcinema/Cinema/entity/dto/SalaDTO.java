package ftnwebcinema.Cinema.entity.dto;

public class SalaDTO {
	private Long idSala;
	private int kapacitet;
	public Long getIdSala() {
		return idSala;
	}
	public void setIdSala(Long idSala) {
		this.idSala = idSala;
	}
	public int getKapacitet() {
		return kapacitet;
	}
	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}
	SalaDTO(){}
	SalaDTO(Long id, int kapacitet){
		this.idSala = id;
		this.kapacitet = kapacitet;
	}
}
