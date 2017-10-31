package org.app.service.entities;


import javax.persistence.Entity;

@Entity
public class DetaliiProiecte extends Proiecte {
	private Double Buget;
	private String Observatii;
	private String TimpAlocat;
	private String TipTraining;
	
	public Double getBuget() {
		return Buget;
	}
	public void setBuget(Double buget) {
		Buget = buget;
	}
	public String getObservatii() {
		return Observatii;
	}
	public void setObservatii(String observatii) {
		Observatii = observatii;
	}
	public String getTimpAlocat() {
		return TimpAlocat;
	}
	public void setTimpAlocat(String timpAlocat) {
		TimpAlocat = timpAlocat;
	}
	public String getTipTraining() {
		return TipTraining;
	}
	public void setTipTraining(String tipTraining) {
		TipTraining = tipTraining;
	}
	public DetaliiProiecte() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetaliiProiecte(Integer idProiect, String denumire, Integer perioada, String status, Clienti clienti,
			Personal personal) {
		super(idProiect, denumire, perioada, status, clienti, personal);
		// TODO Auto-generated constructor stub
	}
	

	
}
