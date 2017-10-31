package org.app.service.entities;

import javax.persistence.Id;
import javax.persistence.OneToMany;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;


@Entity
public class Clienti {
	@Id @GeneratedValue
	private Integer IdClient;
	private String DenSoc;
	private Integer CUI;
	private String Adresa;
	private String Telefon;
	private String Email;
	
	@OneToMany
	private List<Proiecte> proiecte = new ArrayList<>();
	
	@OneToMany
	private List<Suport> suport = new ArrayList<>();

	public Integer getIdClient() {
		return IdClient;
	}

	public void setIdClient(Integer idClient) {
		this.IdClient = idClient;
	}

	public String getDenSoc() {
		return DenSoc;
	}

	public void setDenSoc(String denSoc) {
		this.DenSoc = denSoc;
	}

	public Integer getCUI() {
		return CUI;
	}

	public void setCUI(Integer cUI) {
		this.CUI = cUI;
	}

	public String getAdresa() {
		return Adresa;
	}

	public void setAdresa(String adresa) {
		this.Adresa = adresa;
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

	public Clienti(Integer idClient, String denSoc, Integer cUI, String adresa, String telefon, String email,
			List<Proiecte> proiecte, List<Suport> suport) {
		this.IdClient = idClient;
		this.DenSoc = denSoc;
		this.CUI = cUI;
		this.Adresa = adresa;
		this.Telefon = telefon;
		this.Email = email;
		this.proiecte = proiecte;
		this.suport = suport;
	}

	public Clienti() {
		super();
	}

	
}
