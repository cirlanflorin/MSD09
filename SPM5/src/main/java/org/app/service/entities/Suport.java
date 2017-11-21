package org.app.service.entities;



import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;



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

	public Integer getIdSuport() {
		return IdSuport;
	}

	public void setIdSuport(Integer idSuport) {
		IdSuport = idSuport;
	}

	public String getTipInterventie() {
		return TipInterventie;
	}

	public void setTipInterventie(String tipInterventie) {
		TipInterventie = tipInterventie;
	}

	public Date getData() {
		return Data;
	}

	public void setData(Date data) {
		Data = data;
	}

	public Double getCost() {
		return Cost;
	}

	public void setCost(Double cost) {
		Cost = cost;
	}

	public String getObservatii() {
		return Observatii;
	}

	public void setObservatii(String observatii) {
		Observatii = observatii;
	}

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

	public Suport() {
		super();
	}

	
}
