package org.app.service.entities;



import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;


@XmlRootElement(name="suport") 
@XmlAccessorType(XmlAccessType.NONE)
@Entity
public class Suport implements Serializable{
	@Id @GeneratedValue
	private Integer IdSuport;
    private String TipInterventie;
	
	@Temporal(TemporalType.DATE)
	private Date Data;
	private Double Cost;
	private String Observatii;
	private Integer Durata;
	
	@ManyToOne
	@JoinColumn(name="clienti")
	private Clienti clienti;
	
	@ManyToOne
	@JoinColumn(name="personal")
	private Personal personal;

	@XmlElement
	public Integer getIdSuport() {
		return IdSuport;
	}

	public void setIdSuport(Integer idSuport) {
		IdSuport = idSuport;
	}

	@XmlElement
	public String getTipInterventie() {
		return TipInterventie;
	}

	public void setTipInterventie(String tipInterventie) {
		TipInterventie = tipInterventie;
	}

	@XmlElement
	public Date getData() {
		return Data;
	}

	public void setData(Date data) {
		Data = data;
	}

	@XmlElement
	public Double getCost() {
		return Cost;
	}

	public void setCost(Double cost) {
		Cost = cost;
	}

	@XmlElement
	public String getObservatii() {
		return Observatii;
	}

	public void setObservatii(String observatii) {
		Observatii = observatii;
	}

	@XmlElement
	public Integer getDurata() {
		return Durata;
	}

	public void setDurata(Integer durata) {
		Durata = durata;
	}

	public Clienti getClienti() {
		return clienti;
	}

	public void setClienti(Clienti clienti) {
		this.clienti = clienti;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}
/*
	public static String BASE_URL = Clienti.BASE_URL;
	@XmlElement(name = "link")
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL 
				+ ((this.getClienti() != null) ? this.getClienti().getIdClient() : "")
				+ "suport/" 
				+ this.getIdSuport();
        return new AtomLink(restUrl, "get-suport");
    }*/
	
	public static String BASE_URL = "http://localhost:8080/SPM5/data/suports/";
	@XmlElement(name = "link")
    public AtomLink getLink() throws Exception {
		String restUrl = BASE_URL + this.getIdSuport();
        return new AtomLink(restUrl, "get-suport");
    }	
	
	public void setLink(AtomLink link){
		
	}
	
	public Suport(Integer idSuport, String tipInterventie, Date data, Double cost, String observatii, Integer durata,
			Clienti clienti, Personal personal) {
		IdSuport = idSuport;
		TipInterventie = tipInterventie;
		Data = data;
		Cost = cost;
		Observatii = observatii;
		Durata = durata;
		this.clienti = clienti;
		this.personal = personal;
	}

	
	public Suport(Integer idSuport, String tipInterventie) {
		super();
		IdSuport = idSuport;
		TipInterventie = tipInterventie;
	}

	public Suport() {
		super();
	}

	
}
