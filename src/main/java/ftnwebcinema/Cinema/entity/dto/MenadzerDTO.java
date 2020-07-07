package ftnwebcinema.Cinema.entity.dto;

public class MenadzerDTO {
	private Long idMenadzer;
	private String ime;
	private String prezime;
	private String uloga;
	private Boolean aktivan; // Mora zbog scriptAktivacija
	public Long getIdMenadzer() {
		return idMenadzer;
	}
	public void setIdMenadzer(Long idMenadzer) {
		this.idMenadzer = idMenadzer;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getUloga() {
		return uloga;
	}
	public void setUloga(String uloga) {
		this.uloga = uloga;
	}
	public Boolean getAktivan() {
		return aktivan;
	}
	public void setAktivan(Boolean aktivan) {
		this.aktivan = aktivan;
	}
	public MenadzerDTO() {}
	public MenadzerDTO(Long id, String ime, String prezime, String uloga, Boolean aktivan) {
		this.idMenadzer = id;
		this.ime = ime;
		this.prezime = prezime;
		this.uloga = uloga;
		this.aktivan = aktivan;
	}
	
}
