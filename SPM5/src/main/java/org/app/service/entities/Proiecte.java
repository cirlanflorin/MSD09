package org.app.service.entities;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.InheritanceType.TABLE_PER_CLASS;
import static javax.persistence.InheritanceType.JOINED;



@Entity
@Table(name="Proiecte")
@Inheritance(strategy = JOINED)
@DiscriminatorColumn(name="TYPE",discriminatorType=DiscriminatorType.STRING,length=20)
@DiscriminatorValue("Proiecte")
public abstract class Proiecte  {
	@Id @GeneratedValue
	private Integer IdProiect;
	private String Denumire;
	private Integer Perioada;
	private String Status;
	
	@ManyToOne
	private Clienti clienti ;
	

	@ManyToOne
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
