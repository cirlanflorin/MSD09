package org.app.service.entities;

import javax.persistence.Id;
import javax.persistence.OneToMany;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;


@Entity
public class Personal {
	@Id @GeneratedValue
	private Integer IdTrainer;
	private String Nume;
	private String Prenume;
	private Integer Experienta;
	private String Specializare;
	private String Telefon;
	private String Email;
	
	@OneToMany
	private List<Proiecte> proiecte = new ArrayList<>();
	
	@OneToMany
	private List<Suport> suport = new ArrayList<>();

	public Integer getIdTrainer() {
		return IdTrainer;
	}

	public void setIdTrainer(Integer idTrainer) {
		this.IdTrainer = idTrainer;
	}

	public String getNume() {
		return Nume;
	}

	public void setNume(String nume) {
		this.Nume = nume;
	}

	public String getPrenume() {
		return Prenume;
	}

	public void setPrenume(String prenume) {
		this.Prenume = prenume;
	}

	public Integer getExperienta() {
		return Experienta;
	}

	public void setExperienta(Integer experienta) {
		this.Experienta = experienta;
	}

	public String getSpecializare() {
		return Specializare;
	}

	public void setSpecializare(String specializare) {
		this.Specializare = specializare;
	}

	public String getTelefon() {
		return Telefon;
	}

	public void setTelefon(String telefon) {
		this.Telefon = telefon;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public List<Proiecte> getProiecte() {
		return proiecte;
	}

	public void setProiecte(List<Proiecte> proiecte) {
		this.proiecte = proiecte;
	}

	public List<Suport> getSuport() {
		return suport;
	}

	public void setSuport(List<Suport> suport) {
		this.suport = suport;
	}

	public Personal(Integer idTrainer, String nume, String prenume, Integer experienta, String specializare,
			String telefon, String email, List<Proiecte> proiecte, List<Suport> suport) {
		this.IdTrainer = idTrainer;
		this.Nume = nume;
		this.Prenume = prenume;
		this.Experienta = experienta;
		this.Specializare = specializare;
		this.Telefon = telefon;
		this.Email = email;
		this.proiecte = proiecte;
		this.suport = suport;
	}

	public Personal() {
		super();
	}


	
}
