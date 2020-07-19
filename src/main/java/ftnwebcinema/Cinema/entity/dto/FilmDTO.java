package ftnwebcinema.Cinema.entity.dto;

public class FilmDTO {
	private Long idFilm;
	private String naziv;
	private String zanr;
	public Long getIdFilm() {
		return idFilm;
	}
	public void setIdFilm(Long idFilm) {
		this.idFilm = idFilm;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	public String getZanr() {
		return zanr;
	}
	public void setZanr(String zanr) {
		this.zanr = zanr;
	}
	public FilmDTO() {}
	
	public FilmDTO(Long id, String naziv, String zanr) {
		this.idFilm = id;
		this.naziv = naziv;
		this.zanr = zanr;
	}
}
