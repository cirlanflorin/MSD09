package org.app.service.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;

@XmlRootElement(name="detaliiproiecte") 
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name="DetaliiProiecte")
@DiscriminatorValue("DetaliiProiect")
public class DetaliiProiecte extends Proiecte implements Serializable{
	
	private Double Buget;
	private String Observatii;
	private String TimpAlocat;
	private String TipTraining;
	
	@XmlElement
	public Double getBuget() {
		return Buget;
	}
	public void setBuget(Double buget) {
		Buget = buget;
	}
	@XmlElement
	public String getObservatii() {
		return Observatii;
	}
	public void setObservatii(String observatii) {
		Observatii = observatii;
	}
	@XmlElement
	public String getTimpAlocat() {
		return TimpAlocat;
	}
	public void setTimpAlocat(String timpAlocat) {
		TimpAlocat = timpAlocat;
	}
	@XmlElement
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
	
	public static String BASE_URL = "http://localhost:8080/SPM5/data/detaliiproiectedp/";
	@XmlElement(name = "link")
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getIdProiect();
        return new AtomLink(restUrl, "get-detaliiproiect");
    }	
	
	public void setLink(AtomLink link){
		
	}
	
	public DetaliiProiecte(Integer idProiect, String denumire, Integer perioada, String status, Clienti clienti,
			Personal personal) {
		super(idProiect, denumire, perioada, status, clienti, personal);
		// TODO Auto-generated constructor stub
	}
	public DetaliiProiecte(Integer idProiect, String denumire, Integer perioada, String status, Clienti clienti,
			Personal personal, Double buget, String observatii, String timpAlocat, String tipTraining) {
		super(idProiect, denumire, perioada, status, clienti, personal);
		Buget = buget;
		Observatii = observatii;
		TimpAlocat = timpAlocat;
		TipTraining = tipTraining;
	}
	
	

	
}
