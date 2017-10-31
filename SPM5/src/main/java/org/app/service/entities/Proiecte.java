package org.app.service.entities;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;



@Entity
@Access(AccessType.FIELD)
public class Proiecte {
	@Id @GeneratedValue
	private Integer IdProiect;
	private String Denumire;
	private Integer Perioada;
	private String Status;
	
	@ManyToOne
	@JoinColumn(name="clienti")
	private Clienti clienti ;
	
	@ManyToOne
	@JoinColumn(name="personal")
	private Personal personal;

	public Integer getIdProiect() {
		return IdProiect;
	}

	public void setIdProiect(Integer idProiect) {
		IdProiect = idProiect;
	}

	public String getDenumire() {
		return Denumire;
	}

	public void setDenumire(String denumire) {
		Denumire = denumire;
	}

	public Integer getPerioada() {
		return Perioada;
	}

	public void setPerioada(Integer perioada) {
		Perioada = perioada;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
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

	public Proiecte(Integer idProiect, String denumire, Integer perioada, String status, Clienti clienti,
			Personal personal) {
		IdProiect = idProiect;
		Denumire = denumire;
		Perioada = perioada;
		Status = status;
		this.clienti = clienti;
		this.personal = personal;
	}

	public Proiecte() {
		super();
	}
	
	
	
}
